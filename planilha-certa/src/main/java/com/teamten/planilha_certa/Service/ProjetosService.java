package com.teamten.planilha_certa.Service;

import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB2Consultor.Consultor;
import com.teamten.planilha_certa.ClassTB3Contrato.Contrato;
import com.teamten.planilha_certa.ClassTB4Projetos.Projetos;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ProjetosService {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME_PROJETOS = "projetos";
    private static final String COLLECTION_NAME_CONSULTORES = "consultores";

    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void init() {
        initializeIdCounter();
    }

    private void initializeIdCounter() {
        try {
            CollectionReference projeto = firestore.collection(COLLECTION_NAME_PROJETOS);
            ApiFuture<QuerySnapshot> query = projeto.get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            long maxId = 0;
            for (QueryDocumentSnapshot document : documents) {
                Projetos projetos = document.toObject(Projetos.class);
                maxId = Math.max(maxId, projetos.getIdProjeto());
            }

            idCounter.set(maxId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ID counter", e);
        }
    }

    public Projetos cadastrarProjetoFinanceiro(Projetos projeto) {
        long newId = idCounter.incrementAndGet();
        projeto.setIdProjeto(newId);

        try {
            // Procurar um contrato com o campo 'cadastro' como falso
            CollectionReference contratos = firestore.collection("contratos");
            Query contratoQuery = contratos.whereEqualTo("cadastro", false);
            ApiFuture<QuerySnapshot> contratoSnapshot = contratoQuery.get();
            List<QueryDocumentSnapshot> contratoDocuments = contratoSnapshot.get().getDocuments();

            // Verificar se há exatamente um contrato com 'cadastro' como falso
            if (contratoDocuments.size() != 1) {
                throw new RuntimeException("Erro: deve haver exatamente um contrato com cadastro falso.");
            }

            // Atualizar dados do projeto com o contrato encontrado
            DocumentSnapshot contratoDoc = contratoDocuments.get(0);
            Contrato contrato = contratoDoc.toObject(Contrato.class);
            projeto.setIdContrato(contrato.getIdContrato());
            projeto.setServico("Financeiro");  // Definir o serviço como 'Financeiro'

            // Marcar o contrato como cadastro verdadeiro
            contrato.setCadastro(true);
            ApiFuture<WriteResult> futureContratoUpdate = contratoDoc.getReference().set(contrato);
            futureContratoUpdate.get();

            // Marcar o projeto como cadastro falso
            projeto.setCadastro(false);
            // Encontrar um consultor não alocado com a especialização correspondente
            CollectionReference consultores = firestore.collection(COLLECTION_NAME_CONSULTORES);
            Query query = consultores.whereEqualTo("alocado", false)
                    .whereEqualTo("especializacao", projeto.getServico());
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> consultorDocuments = querySnapshot.get().getDocuments();

            if (!consultorDocuments.isEmpty()) {
                DocumentSnapshot consultorDoc = consultorDocuments.get(0);
                Consultor consultor = consultorDoc.toObject(Consultor.class);

                // Atualizar dados do projeto com o consultor encontrado
                projeto.setIdConsultor(consultor.getIdConsultor());
                projeto.setNomeConsultor(consultor.getNomeConsultor());

                // Marcar o consultor como alocado
                consultor.setAlocado(true);
                ApiFuture<WriteResult> futureConsultorUpdate = consultorDoc.getReference().set(consultor);
                futureConsultorUpdate.get();

                // Salvar o projeto
                CollectionReference projetos = firestore.collection(COLLECTION_NAME_PROJETOS);
                ApiFuture<WriteResult> futureProjeto = projetos.document(String.valueOf(newId)).set(projeto);
                futureProjeto.get();

                return projeto;
            } else {
                throw new RuntimeException("Nenhum consultor disponível com a especialização necessária.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Projetos cadastrarProjetoGestao(Projetos projeto) {
        long newId = idCounter.incrementAndGet();
        projeto.setIdProjeto(newId);

        try {
            // Procurar um contrato com o campo 'cadastro' como falso
            CollectionReference contratos = firestore.collection("contratos");
            Query contratoQuery = contratos.whereEqualTo("cadastro", false);
            ApiFuture<QuerySnapshot> contratoSnapshot = contratoQuery.get();
            List<QueryDocumentSnapshot> contratoDocuments = contratoSnapshot.get().getDocuments();

            // Verificar se há exatamente um contrato com 'cadastro' como falso
            if (contratoDocuments.size() != 1) {
                throw new RuntimeException("Erro: deve haver exatamente um contrato com cadastro falso.");
            }

            // Atualizar dados do projeto com o contrato encontrado
            DocumentSnapshot contratoDoc = contratoDocuments.get(0);
            Contrato contrato = contratoDoc.toObject(Contrato.class);
            projeto.setIdContrato(contrato.getIdContrato());
            projeto.setServico("Gestão");  // Definir o serviço como 'Financeiro'

            // Marcar o contrato como cadastro verdadeiro
            contrato.setCadastro(true);
            ApiFuture<WriteResult> futureContratoUpdate = contratoDoc.getReference().set(contrato);
            futureContratoUpdate.get();

            // Encontrar um consultor não alocado com a especialização correspondente
            CollectionReference consultores = firestore.collection(COLLECTION_NAME_CONSULTORES);
            Query query = consultores.whereEqualTo("alocado", false)
                    .whereEqualTo("especializacao", projeto.getServico());
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> consultorDocuments = querySnapshot.get().getDocuments();

            if (!consultorDocuments.isEmpty()) {
                DocumentSnapshot consultorDoc = consultorDocuments.get(0);
                Consultor consultor = consultorDoc.toObject(Consultor.class);

                // Atualizar dados do projeto com o consultor encontrado
                projeto.setIdConsultor(consultor.getIdConsultor());
                projeto.setNomeConsultor(consultor.getNomeConsultor());

                // Marcar o consultor como alocado
                consultor.setAlocado(true);
                ApiFuture<WriteResult> futureConsultorUpdate = consultorDoc.getReference().set(consultor);
                futureConsultorUpdate.get();

                // Salvar o projeto
                CollectionReference projetos = firestore.collection(COLLECTION_NAME_PROJETOS);
                ApiFuture<WriteResult> futureProjeto = projetos.document(String.valueOf(newId)).set(projeto);
                futureProjeto.get();

                return projeto;
            } else {
                throw new RuntimeException("Nenhum consultor disponível com a especialização necessária.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Projetos cadastrarProjetoTi(Projetos projeto) {
        long newId = idCounter.incrementAndGet();
        projeto.setIdProjeto(newId);

        try {
            // Procurar um contrato com o campo 'cadastro' como falso
            CollectionReference contratos = firestore.collection("contratos");
            Query contratoQuery = contratos.whereEqualTo("cadastro", false);
            ApiFuture<QuerySnapshot> contratoSnapshot = contratoQuery.get();
            List<QueryDocumentSnapshot> contratoDocuments = contratoSnapshot.get().getDocuments();

            // Verificar se há exatamente um contrato com 'cadastro' como falso
            if (contratoDocuments.size() != 1) {
                throw new RuntimeException("Erro: deve haver exatamente um contrato com cadastro falso.");
            }

            // Atualizar dados do projeto com o contrato encontrado
            DocumentSnapshot contratoDoc = contratoDocuments.get(0);
            Contrato contrato = contratoDoc.toObject(Contrato.class);
            projeto.setIdContrato(contrato.getIdContrato());
            projeto.setServico("TI");  // Definir o serviço como 'Financeiro'

            // Marcar o contrato como cadastro verdadeiro
            contrato.setCadastro(true);
            ApiFuture<WriteResult> futureContratoUpdate = contratoDoc.getReference().set(contrato);
            futureContratoUpdate.get();

            // Encontrar um consultor não alocado com a especialização correspondente
            CollectionReference consultores = firestore.collection(COLLECTION_NAME_CONSULTORES);
            Query query = consultores.whereEqualTo("alocado", false)
                    .whereEqualTo("especializacao", projeto.getServico());
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> consultorDocuments = querySnapshot.get().getDocuments();

            if (!consultorDocuments.isEmpty()) {
                DocumentSnapshot consultorDoc = consultorDocuments.get(0);
                Consultor consultor = consultorDoc.toObject(Consultor.class);

                // Atualizar dados do projeto com o consultor encontrado
                projeto.setIdConsultor(consultor.getIdConsultor());
                projeto.setNomeConsultor(consultor.getNomeConsultor());

                // Marcar o consultor como alocado
                consultor.setAlocado(true);
                ApiFuture<WriteResult> futureConsultorUpdate = consultorDoc.getReference().set(consultor);
                futureConsultorUpdate.get();

                // Salvar o projeto
                CollectionReference projetos = firestore.collection(COLLECTION_NAME_PROJETOS);
                ApiFuture<WriteResult> futureProjeto = projetos.document(String.valueOf(newId)).set(projeto);
                futureProjeto.get();

                return projeto;
            } else {
                throw new RuntimeException("Nenhum consultor disponível com a especialização necessária.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Projetos> listarProjetos() {
        List<Projetos> listaProjetos = new ArrayList<>();
        try {
            CollectionReference projetos = firestore.collection(COLLECTION_NAME_PROJETOS);
            ApiFuture<QuerySnapshot> future = projetos.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Projetos projeto = document.toObject(Projetos.class);
                listaProjetos.add(projeto);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listaProjetos;
    }

    public boolean editarProjeto(Projetos projeto) {
        // Atualiza o projeto existente
        CollectionReference projetos = firestore.collection(COLLECTION_NAME_PROJETOS);
        ApiFuture<WriteResult> future = projetos.document(String.valueOf(projeto.getIdProjeto())).set(projeto);

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirProjeto(long idProjeto) {
        // Exclui o projeto pelo ID
        CollectionReference projetos = firestore.collection(COLLECTION_NAME_PROJETOS);
        ApiFuture<WriteResult> future = projetos.document(String.valueOf(idProjeto)).delete();

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}