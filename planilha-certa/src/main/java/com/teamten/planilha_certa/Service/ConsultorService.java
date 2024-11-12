package com.teamten.planilha_certa.Service;

import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB2Consultor.Consultor;
import com.teamten.planilha_certa.ClassTB2Consultor.ConsultorEspeciFinanceiro;
import com.teamten.planilha_certa.ClassTB2Consultor.ConsultorEspeciGestao;
import com.teamten.planilha_certa.ClassTB2Consultor.ConsultorEspeciTI;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ConsultorService {
    @Autowired
    private Firestore firestore;


    private static final String COLLECTION_NAME = "consultores";
    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void init() {
        initializeIdCounter();
    }

    private void initializeIdCounter() {
        try {
            CollectionReference consultores = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> query = consultores.get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            long maxId = 0;
            for (QueryDocumentSnapshot document : documents) {
                Consultor consultor = document.toObject(Consultor.class);
                maxId = Math.max(maxId, consultor.getIdConsultor());
            }

            idCounter.set(maxId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ID counter", e);
        }
    }

    public ConsultorEspeciFinanceiro cadastrarConsultorEspeciFinanceiro(ConsultorEspeciFinanceiro consultor) {
        long newId = idCounter.incrementAndGet();
        consultor.setIdConsultor(newId);

        consultor.setEspecializacao("Financeiro");

        CollectionReference consultores = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = consultores.document(String.valueOf(newId)).set(consultor);

        try {
            future.get();
            return consultor;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ConsultorEspeciGestao cadastrarConsultorEspeciGestao(ConsultorEspeciGestao consultor) {
        long newId = idCounter.incrementAndGet();
        consultor.setIdConsultor(newId);

        consultor.setEspecializacao("Gestão");

        CollectionReference consultores = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = consultores.document(String.valueOf(newId)).set(consultor);

        try {
            future.get();
            return consultor;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ConsultorEspeciTI cadastrarConsultorEspeciTI(ConsultorEspeciTI consultor) {
        long newId = idCounter.incrementAndGet();
        consultor.setIdConsultor(newId);

        consultor.setEspecializacao("TI");

        CollectionReference consultores = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = consultores.document(String.valueOf(newId)).set(consultor);

        try {
            future.get();
            return consultor;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Consultor> listarConsultores() {
        List<Consultor> listaConsultores = new ArrayList<>();
        try {
            CollectionReference consultores = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> future = consultores.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Consultor Consultor = document.toObject(Consultor.class);
                listaConsultores.add(Consultor);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listaConsultores;
    }

    public boolean editarConsultor(Consultor Consultor) {
        // Atualiza o consultor existente
        CollectionReference consultor = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = consultor.document(String.valueOf(Consultor.getIdConsultor())).set(Consultor);

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirConsultor(long idConsultor) {
        // Exclui o consultor pelo ID
        CollectionReference consultores = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = consultores.document(String.valueOf(idConsultor)).delete();

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

}