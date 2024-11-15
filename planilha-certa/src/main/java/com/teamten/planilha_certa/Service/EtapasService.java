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

    public boolean editarEtapaImplementacao(Etapas Etapas) {
        CollectionReference etapas = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = etapas.document(String.valueOf(Etapas.getIdEtapa())).set(Etapas);

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarEtapaRevisaoFinal(Etapas Etapas) {
        CollectionReference etapas = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = etapas.document(String.valueOf(Etapas.getIdEtapa())).set(Etapas);

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarEtapaConclusao(Etapas Etapas) {
        CollectionReference etapas = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = etapas.document(String.valueOf(Etapas.getIdEtapa())).set(Etapas);

        try {
            future.get();
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
