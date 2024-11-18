package com.teamten.planilha_certa.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB1Cliente.Cliente;
import com.teamten.planilha_certa.ClassTB3Contrato.Contrato;
import com.teamten.planilha_certa.ClassTB4Projetos.Projetos;
import com.teamten.planilha_certa.ClassTB2Consultor.Consultor;
import com.teamten.planilha_certa.ClassTB5Etapas.Etapas;
import com.teamten.planilha_certa.ClassTB6Relatorios.Relatorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RelatoriosService {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME_RELATORIOS = "relatorios";
    private static final String COLLECTION_NAME_CLIENTES = "clientes";
    private static final String COLLECTION_NAME_CONTRATOS = "contratos";
    private static final String COLLECTION_NAME_PROJETOS = "projetos";
    private static final String COLLECTION_NAME_CONSULTORES = "consultores";
    private static final String COLLECTION_NAME_ETAPAS = "etapas";

    // Método para gerar relatórios por CPF
    public List<Relatorios> gerarRelatoriosPorCpf(String cpfCliente) {
        List<Relatorios> relatoriosList = new ArrayList<>();

        try {
            CollectionReference clientesCollection = firestore.collection(COLLECTION_NAME_CLIENTES);
            Query clienteQuery = clientesCollection.whereEqualTo("cpfCliente", cpfCliente);
            ApiFuture<QuerySnapshot> clienteSnapshot = clienteQuery.get();
            List<QueryDocumentSnapshot> clienteDocuments = clienteSnapshot.get().getDocuments();

            if (!clienteDocuments.isEmpty()) {
                DocumentSnapshot clienteDocument = clienteDocuments.get(0);
                Cliente cliente = clienteDocument.toObject(Cliente.class);

                CollectionReference contratosCollection = firestore.collection(COLLECTION_NAME_CONTRATOS);
                Query contratosQuery = contratosCollection.whereEqualTo("cpfCliente", cpfCliente);
                ApiFuture<QuerySnapshot> contratosSnapshot = contratosQuery.get();
                List<QueryDocumentSnapshot> contratoDocuments = contratosSnapshot.get().getDocuments();

                for (DocumentSnapshot contratoDocument : contratoDocuments) {
                    Contrato contrato = contratoDocument.toObject(Contrato.class);

                    CollectionReference projetosCollection = firestore.collection(COLLECTION_NAME_PROJETOS);
                    Query projetosQuery = projetosCollection.whereEqualTo("idContrato", contrato.getIdContrato());
                    ApiFuture<QuerySnapshot> projetosSnapshot = projetosQuery.get();
                    List<QueryDocumentSnapshot> projetoDocuments = projetosSnapshot.get().getDocuments();

                    for (DocumentSnapshot projetoDocument : projetoDocuments) {
                        Projetos projeto = projetoDocument.toObject(Projetos.class);

                        CollectionReference consultoresCollection = firestore.collection(COLLECTION_NAME_CONSULTORES);
                        DocumentSnapshot consultorDocument = consultoresCollection.document(String.valueOf(projeto.getIdConsultor())).get().get();
                        Consultor consultor = consultorDocument.toObject(Consultor.class);

                        CollectionReference etapasCollection = firestore.collection(COLLECTION_NAME_ETAPAS);
                        Query etapasQuery = etapasCollection.whereEqualTo("idProjeto", projeto.getIdProjeto());
                        ApiFuture<QuerySnapshot> etapasSnapshot = etapasQuery.get();
                        List<QueryDocumentSnapshot> etapaDocuments = etapasSnapshot.get().getDocuments();

                        for (DocumentSnapshot etapaDocument : etapaDocuments) {
                            Etapas etapa = etapaDocument.toObject(Etapas.class);

                            Relatorios relatorio = new Relatorios();
                            relatorio.setCpfCliente(cpfCliente);
                            relatorio.setNomeCliente(cliente.getNomeCliente());
                            relatorio.setCategoriaCliente(cliente.getCategoriaCliente());
                            relatorio.setIdContrato(contrato.getIdContrato());
                            relatorio.setDataInicio(contrato.getDataInicio());
                            relatorio.setValorServico(contrato.getValorServico());
                            relatorio.setDesconto(contrato.getDesconto());
                            relatorio.setValorLiquido(contrato.getValorLiquido());

                            relatorio.setIdProjeto(projeto.getIdProjeto());
                            relatorio.setDescicaoProjeto(projeto.getDescricaoProjeto());
                            relatorio.setService(projeto.getServico());

                            relatorio.setIdConsultor(consultor.getIdConsultor());
                            relatorio.setNomeConsultor(consultor.getNomeConsultor());
                            relatorio.setEspecializacao(consultor.getEspecializacao());

                            relatorio.setIdEtapa(etapa.getIdEtapa());
                            relatorio.setNomeEtapa(etapa.getNome());
                            relatorio.setAnaliseInicial(etapa.isAnaliseInicial());
                            relatorio.setStatusAnaliseInicial(etapa.getStatusAnaliseInicial());
                            relatorio.setPagamentoET1(etapa.getPagamentoET1());
                            relatorio.setDescontoET1(etapa.getDescontoET1());
                            relatorio.setLiquidoET1(etapa.getLiquidoET1());

                            relatoriosList.add(relatorio);
                        }
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return relatoriosList;
    }

    // Método para criar um novo relatório
    public Relatorios criarRelatorio(Relatorios relatorio) {
        try {
            CollectionReference relatoriosCollection = firestore.collection(COLLECTION_NAME_RELATORIOS);
            DocumentReference documentReference = relatoriosCollection.document();
            relatorio.setIdRelatorio(Long.parseLong(documentReference.getId())); // Gerar ID baseado no documento Firestore

            ApiFuture<WriteResult> writeResult = documentReference.set(relatorio);
            writeResult.get(); // Esperar a operação ser concluída

            return relatorio;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar relatório", e);
        }
    }
}
