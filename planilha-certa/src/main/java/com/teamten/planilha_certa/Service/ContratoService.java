package com.teamten.planilha_certa.Service;

import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB3Contrato.Contrato;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorAlta;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorBaixa;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContratoService {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME = "contratos";
    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void init() {
        initializeIdCounter();
    }

    private void initializeIdCounter() {
        try {
            CollectionReference contratos = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> query = contratos.get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            long maxId = 0;
            for (QueryDocumentSnapshot document : documents) {
                Contrato contrato = document.toObject(Contrato.class);
                maxId = Math.max(maxId, contrato.getIdContrato());
            }

            idCounter.set(maxId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ID counter", e);
        }
    }

    public ContratoPriorAlta criarContratoPriorAlta(ContratoPriorAlta contrato) {
        long newId = idCounter.incrementAndGet();
        contrato.setIdContrato(newId);
        contrato.setPrioridadeAtendimento("Alta");

        CollectionReference contratos = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = contratos.document(String.valueOf(newId)).set(contrato);

        try {
            future.get();
            return contrato;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ContratoPriorBaixa criarContratoPriorBaixa(ContratoPriorBaixa contrato) {
        long newId = idCounter.incrementAndGet();
        contrato.setIdContrato(newId);
        contrato.setPrioridadeAtendimento("Baixa");

        CollectionReference contratos = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = contratos.document(String.valueOf(newId)).set(contrato);

        try {
            future.get();
            return contrato;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Contrato editarContrato(long id, Contrato contratoAtualizado) {
        CollectionReference contratos = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = contratos.document(String.valueOf(id)).set(contratoAtualizado);

        try {
            future.get();
            return contratoAtualizado;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean excluirContrato(long id) {
        CollectionReference contratos = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = contratos.document(String.valueOf(id)).delete();

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Contrato> listarContratos() {
        List<Contrato> listaContratos = new ArrayList<>();
        try {
            CollectionReference contratos = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> future = contratos.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Contrato contrato = document.toObject(Contrato.class);
                listaContratos.add(contrato);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listaContratos;
    }
}
