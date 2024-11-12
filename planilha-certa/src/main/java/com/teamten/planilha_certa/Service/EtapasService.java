package com.teamten.planilha_certa.Service;

import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB5Etapas.Etapas;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasAnaliseInicial;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasImplementacao;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasRevisaoFinal;
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

    public EtapasAnaliseInicial cadastrarEtapasAnaliseInicial(EtapasAnaliseInicial etapa) {
        long newId = idCounter.incrementAndGet();
        etapa.setIdEtapa(newId);

        CollectionReference etapas = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = etapas.document(String.valueOf(newId)).set(etapa);

        try {
            future.get();
            return etapa;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EtapasImplementacao cadastrarEtapasImplementacao(EtapasImplementacao etapa) {
        long newId = idCounter.incrementAndGet();
        etapa.setIdEtapa(newId);

        CollectionReference etapas = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = etapas.document(String.valueOf(newId)).set(etapa);

        try {
            future.get();
            return etapa;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EtapasRevisaoFinal cadastrarEtapasRevisaoFinal(EtapasRevisaoFinal etapa) {
        long newId = idCounter.incrementAndGet();
        etapa.setIdEtapa(newId);

        CollectionReference etapas = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = etapas.document(String.valueOf(newId)).set(etapa);

        try {
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

    public boolean editarEtapa(Etapas Etapas) {
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
