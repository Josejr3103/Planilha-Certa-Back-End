package com.teamten.planilha_certa.Service;

import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB2Consultor.Consultor;
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

    private static final String COLLECTION_NAME = "projetos";
    private static final String COLLECTION_NAME_CONSULTORES = "consultores";

    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void init() {
        initializeIdCounter();
    }

    private void initializeIdCounter() {
        try {
            CollectionReference projeto = firestore.collection(COLLECTION_NAME);
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


    public Projetos cadastrarProjeto(Projetos projeto) {
        long newId = idCounter.incrementAndGet();
        projeto.setIdProjeto(newId);

        try {
            // Encontrar um consultor não alocado com a especialização correspondente
            CollectionReference consultores = firestore.collection(COLLECTION_NAME_CONSULTORES);
            Query query = consultores.whereEqualTo("alocado", false)
                    .whereEqualTo("especializacao", projeto.getServico().toString());
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> consultorDocuments = querySnapshot.get().getDocuments();

            if (!consultorDocuments.isEmpty()) {
                DocumentSnapshot consultorDoc = consultorDocuments.get(0);
                Consultor consultor = consultorDoc.toObject(Consultor.class);

                // Atualizar os dados do projeto com o consultor encontrado
                projeto.setIdConsultor(consultor.getIdConsultor());
                projeto.setNomeConsultor(consultor.getNomeConsultor());

                // Marcar o consultor como alocado
                consultor.setAlocado(true);
                ApiFuture<WriteResult> futureConsultorUpdate = consultorDoc.getReference().set(consultor);
                futureConsultorUpdate.get();

                // Salvar o projeto
                CollectionReference projetos = firestore.collection(COLLECTION_NAME);
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
            CollectionReference projetos = firestore.collection(COLLECTION_NAME);
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
        CollectionReference projetos = firestore.collection(COLLECTION_NAME);
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
        CollectionReference projetos = firestore.collection(COLLECTION_NAME);
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