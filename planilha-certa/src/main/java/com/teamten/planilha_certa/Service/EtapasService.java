package com.teamten.planilha_certa.Service;

import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB3Contrato.Contrato;
import com.teamten.planilha_certa.ClassTB4Projetos.Projetos;
import com.teamten.planilha_certa.ClassTB5Etapas.Etapas;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EtapasService {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME = "etapas";
    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void init() {
        initializeIdCounter();
    }

    private void initializeIdCounter() {
        try {
            CollectionReference etapas = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> query = etapas.get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            long maxId = 0;
            for (QueryDocumentSnapshot document : documents) {
                Etapas etapa = document.toObject(Etapas.class);
                maxId = Math.max(maxId, etapa.getIdEtapa());
            }

            idCounter.set(maxId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ID counter", e);
        }
    }

    public Etapas cadastrarEtapasAnaliseInicial(Etapas etapa) {
        try {
            // Buscando projetos com cadastro == false
            CollectionReference projetosCollection = firestore.collection("projetos");
            ApiFuture<QuerySnapshot> projetosQuery = projetosCollection.whereEqualTo("cadastro", false).get();
            List<QueryDocumentSnapshot> projetosDocuments = projetosQuery.get().getDocuments();

            if (projetosDocuments.size() != 1) {
                System.out.println("Erro: Deve haver exatamente um projeto com cadastro igual a false.");
                return null;
            }

            // Carregando projeto encontrado
            Projetos projeto = projetosDocuments.get(0).toObject(Projetos.class);

            // Buscando contrato associado ao projeto
            CollectionReference contratosCollection = firestore.collection("contratos");
            ApiFuture<QuerySnapshot> contratosQuery = contratosCollection.whereEqualTo("idContrato", projeto.getIdContrato()).get();
            List<QueryDocumentSnapshot> contratosDocuments = contratosQuery.get().getDocuments();
            if (contratosDocuments.isEmpty()) {
                System.out.println("Erro: Contrato associado não encontrado.");
                return null;
            }

            Contrato contrato = contratosDocuments.get(0).toObject(Contrato.class);

            // Buscando cliente para verificar a categoria
            CollectionReference clientesCollection = firestore.collection("clientes");
            ApiFuture<QuerySnapshot> clientesQuery = clientesCollection.whereEqualTo("idCliente", contrato.getIdCliente()).get();
            List<QueryDocumentSnapshot> clientesDocuments = clientesQuery.get().getDocuments();
            if (clientesDocuments.isEmpty()) {
                System.out.println("Erro: Cliente associado ao contrato não encontrado.");
                return null;
            }

            Map<String, Object> clienteData = clientesDocuments.get(0).getData();
            String categoriaCliente = (String) clienteData.get("categoriaCliente");

            // Preenchendo dados da etapa
            long newId = idCounter.incrementAndGet();
            etapa.setIdEtapa(newId);
            etapa.setIdProjeto(projeto.getIdProjeto());
            etapa.setIdContrato(projeto.getIdContrato());
            etapa.setAnaliseInicial(true);
            etapa.setNome("Analise Inicial");
            etapa.setStatusAnaliseInicial("Em Andamento");
            etapa.setStatusImplementacao("Em Aberto");
            etapa.setStatusRevisaoFinal("Em Aberto");

            // Definindo o pagamento e desconto
            float valorLiquido = contrato.getValorLiquido();
            etapa.setPagamentoET1(valorLiquido * 0.4f);
            if ("Vip".equalsIgnoreCase(categoriaCliente)) {
                etapa.setDescontoET1(etapa.getPagamentoET1() * 0.1f);
            } else {
                etapa.setDescontoET1(0);
            }

            // Atualizando cadastro do projeto
            projetosCollection.document(String.valueOf(projeto.getIdProjeto())).update("cadastro", true);

            // Salvando a nova etapa
            CollectionReference etapasCollection = firestore.collection(COLLECTION_NAME);
            ApiFuture<WriteResult> future = etapasCollection.document(String.valueOf(newId)).set(etapa);
            future.get();
            return etapa;

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Etapas> listarEtapas() {
        List<Etapas> listarEtapas = new ArrayList<>();
        try {
            CollectionReference etapas = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> future = etapas.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Etapas Etapa = document.toObject(Etapas.class);
                listarEtapas.add(Etapa);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listarEtapas;
    }

    public boolean editarEtapaImplementacao(Etapas etapas) {
        try {
            // Busca o documento existente
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(String.valueOf(etapas.getIdEtapa()));
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot snapshot = future.get();

            if (!snapshot.exists()) {
                System.out.println("Erro: Documento da etapa não encontrado.");
                return false;
            }

            // Carrega os dados existentes
            Etapas etapaAtual = snapshot.toObject(Etapas.class);
            if (etapaAtual == null || etapaAtual.getIdContrato() == 0) {
                System.out.println("Erro: Dados da etapa não foram carregados corretamente ou idContrato está inválido.");
                System.out.println("Informações da etapa atual:");
                System.out.println("ID Etapa: " + (etapaAtual != null ? etapaAtual.getIdEtapa() : "N/A"));
                System.out.println("ID Contrato: " + (etapaAtual != null ? etapaAtual.getIdContrato() : "N/A"));
                System.out.println("ID Projeto: " + (etapaAtual != null ? etapaAtual.getIdProjeto() : "N/A"));
                return false;
            }


            // Atualiza apenas os campos necessários
            etapaAtual.setImplementacao(true);
            etapaAtual.setNome("Implementação");
            etapaAtual.setStatusAnaliseInicial("Concluído");
            etapaAtual.setStatusImplementacao("Em Andamento");
            etapaAtual.setStatusRevisaoFinal("Em Aberto");

            // Busca o contrato associado
            CollectionReference contratos = firestore.collection("contratos");
            ApiFuture<QuerySnapshot> contratosQuery = contratos.whereEqualTo("idContrato", etapaAtual.getIdContrato()).get();
            List<QueryDocumentSnapshot> contratosDocs = contratosQuery.get().getDocuments();

            if (contratosDocs.isEmpty()) {
                System.out.println("Erro: Contrato "+etapaAtual.getIdContrato()+" do projeto "+etapaAtual.getIdProjeto()+" da etapa "+etapaAtual.getIdEtapa()+" associado não encontrado.");
                return false;
            }

            Contrato contrato = contratosDocs.get(0).toObject(Contrato.class);
            float valorLiquido = contrato.getValorLiquido();
            etapaAtual.setPagamentoET2(valorLiquido * 0.3f);

            // Busca o cliente associado ao contrato
            CollectionReference clientes = firestore.collection("clientes");
            ApiFuture<QuerySnapshot> clientesQuery = clientes.whereEqualTo("idCliente", contrato.getIdCliente()).get();
            List<QueryDocumentSnapshot> clientesDocs = clientesQuery.get().getDocuments();

            if (!clientesDocs.isEmpty()) {
                Map<String, Object> clienteData = clientesDocs.get(0).getData();
                String categoriaCliente = (String) clienteData.get("categoriaCliente");

                if ("Vip".equalsIgnoreCase(categoriaCliente)) {
                    etapaAtual.setDescontoET2(etapaAtual.getPagamentoET2() * 0.1f);
                } else {
                    etapaAtual.setDescontoET2(0);
                }
            }

            // Atualiza os dados no Firestore
            ApiFuture<WriteResult> writeResult = docRef.set(etapaAtual);
            writeResult.get();

            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean editarEtapaRevisaoFinal(Etapas etapas) {
        try {
            // Busca o documento existente
            DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(String.valueOf(etapas.getIdEtapa()));
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot snapshot = future.get();

            if (!snapshot.exists()) {
                System.out.println("Erro: Documento da etapa não encontrado.");
                return false;
            }

            // Carrega os dados existentes
            Etapas etapaAtual = snapshot.toObject(Etapas.class);
            if (etapaAtual == null || etapaAtual.getIdContrato() == 0) {
                System.out.println("Erro: Dados da etapa não foram carregados corretamente ou idContrato está inválido.");
                System.out.println("Informações da etapa atual:");
                System.out.println("ID Etapa: " + (etapaAtual != null ? etapaAtual.getIdEtapa() : "N/A"));
                System.out.println("ID Contrato: " + (etapaAtual != null ? etapaAtual.getIdContrato() : "N/A"));
                System.out.println("ID Projeto: " + (etapaAtual != null ? etapaAtual.getIdProjeto() : "N/A"));
                return false;
            }


            // Atualiza apenas os campos necessários
            etapaAtual.setImplementacao(true);
            etapaAtual.setNome("Revisão Final");
            etapaAtual.setStatusAnaliseInicial("Concluído");
            etapaAtual.setStatusImplementacao("Concluído");
            etapaAtual.setStatusRevisaoFinal("Em Andamento");

            // Busca o contrato associado
            CollectionReference contratos = firestore.collection("contratos");
            ApiFuture<QuerySnapshot> contratosQuery = contratos.whereEqualTo("idContrato", etapaAtual.getIdContrato()).get();
            List<QueryDocumentSnapshot> contratosDocs = contratosQuery.get().getDocuments();

            if (contratosDocs.isEmpty()) {
                System.out.println("Erro: Contrato "+etapaAtual.getIdContrato()+" do projeto "+etapaAtual.getIdProjeto()+" da etapa "+etapaAtual.getIdEtapa()+" associado não encontrado.");
                return false;
            }

            Contrato contrato = contratosDocs.get(0).toObject(Contrato.class);
            float valorLiquido = contrato.getValorLiquido();
            etapaAtual.setPagamentoET3(valorLiquido * 0.3f);

            // Busca o cliente associado ao contrato
            CollectionReference clientes = firestore.collection("clientes");
            ApiFuture<QuerySnapshot> clientesQuery = clientes.whereEqualTo("idCliente", contrato.getIdCliente()).get();
            List<QueryDocumentSnapshot> clientesDocs = clientesQuery.get().getDocuments();

            if (!clientesDocs.isEmpty()) {
                Map<String, Object> clienteData = clientesDocs.get(0).getData();
                String categoriaCliente = (String) clienteData.get("categoriaCliente");

                if ("Vip".equalsIgnoreCase(categoriaCliente)) {
                    etapaAtual.setDescontoET3(etapaAtual.getPagamentoET3() * 0.1f);
                } else {
                    etapaAtual.setDescontoET3(0);
                }
            }

            // Atualiza os dados no Firestore
            ApiFuture<WriteResult> writeResult = docRef.set(etapaAtual);
            writeResult.get();

            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarEtapaConclusao(Etapas etapas) {
        CollectionReference etapasCollection = firestore.collection(COLLECTION_NAME);
        DocumentReference docRef = etapasCollection.document(String.valueOf(etapas.getIdEtapa()));

        try {
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot snapshot = future.get();

            if (!snapshot.exists()) {
                System.out.println("Erro: Documento da etapa não encontrado.");
                return false;
            }

            // Carrega os dados existentes
            Etapas etapaAtual = snapshot.toObject(Etapas.class);
            if (etapaAtual == null || etapaAtual.getIdContrato() == 0) {
                System.out.println("Erro: Dados da etapa não foram carregados corretamente ou idContrato está inválido.");
                return false;
            }

            // Atualiza os campos necessários
            etapaAtual.setNome("Finalizado");
            etapaAtual.setStatusAnaliseInicial("Concluído");
            etapaAtual.setStatusImplementacao("Concluído");
            etapaAtual.setStatusRevisaoFinal("Concluído");
            etapaAtual.setRevisaoFinal(true);

            // Busca o projeto associado para pegar o idConsultor
            CollectionReference projetosCollection = firestore.collection("projetos");
            ApiFuture<QuerySnapshot> projetosQuery = projetosCollection.whereEqualTo("idProjeto", etapaAtual.getIdProjeto()).get();
            List<QueryDocumentSnapshot> projetosDocs = projetosQuery.get().getDocuments();

            if (projetosDocs.isEmpty()) {
                System.out.println("Erro: Projeto não encontrado.");
                return false;
            }

            // Recuperando o idConsultor do projeto
            Projetos projeto = projetosDocs.get(0).toObject(Projetos.class);
            long idConsultor = projeto.getIdConsultor();

            // Busca o consultor associado ao projeto
            CollectionReference consultoresCollection = firestore.collection("consultores");
            ApiFuture<QuerySnapshot> consultoresQuery = consultoresCollection.whereEqualTo("idConsultor", idConsultor).get();
            List<QueryDocumentSnapshot> consultoresDocs = consultoresQuery.get().getDocuments();

            if (consultoresDocs.isEmpty()) {
                System.out.println("Erro: Consultor não encontrado.");
                return false;
            }

            // Atualiza o consultor para não estar mais alocado
            DocumentReference consultorRef = consultoresCollection.document(String.valueOf(idConsultor));
            ApiFuture<WriteResult> updateConsultor = consultorRef.update("alocado", false);
            updateConsultor.get();

            // Preenche o campo faturamento
            etapaAtual.setFaturamento("Emissão da nota fiscal " + etapaAtual.getIdContrato());

            // Atualiza os valores de pagamento, desconto e líquido
            float totalPagamento = etapaAtual.getPagamentoET1() + etapaAtual.getPagamentoET2() + etapaAtual.getPagamentoET3();
            etapaAtual.setPagamento(totalPagamento);

            float totalDesconto = etapaAtual.getDescontoET1() + etapaAtual.getDescontoET2() + etapaAtual.getDescontoET3();
            etapaAtual.setDesconto(totalDesconto);

            // Calculando o valor líquido
            float totalLiquido = totalPagamento - totalDesconto;
            etapaAtual.setLiquido(totalLiquido);

            // Atualiza os dados no Firestore
            ApiFuture<WriteResult> writeResult = docRef.set(etapaAtual);
            writeResult.get();

            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean excluirEtapa(long idEtapa) {
        CollectionReference etapas = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = etapas.document(String.valueOf(idEtapa)).delete();

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }


}
