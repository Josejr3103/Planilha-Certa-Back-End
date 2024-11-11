package com.teamten.planilha_certa.Service;

import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB4Projetos.Projetos;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ProjetosService {@Autowired
private Firestore firestore;


    private static final String COLLECTION_NAME = "projetos";
    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void init() {
        initializeIdCounter();
    }

    private void initializeIdCounter() {
        try {
            CollectionReference clientes = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> query = clientes.get();
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

    public Projetos cadastrarProjeto(Projetos projetos) {
        long newId = idCounter.incrementAndGet();
        projetos.setIdProjeto(newId);

        CollectionReference projeto = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = projeto.document(String.valueOf(newId)).set(projetos);

        try {
            future.get();
            return projetos;
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
