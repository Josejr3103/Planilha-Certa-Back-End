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

    public ContratoPriorAlta cadastrarContratoPriorAlta(ContratoPriorAlta contrato) {
        long newId = idCounter.incrementAndGet();
        contrato.setIdContrato(newId);

        contrato.setPrioridadeAtendimento("alta");

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

    public ContratoPriorBaixa cadastrarContratoPriorBaixa(ContratoPriorBaixa contrato) {
        long newId = idCounter.incrementAndGet();
        contrato.setIdContrato(newId);

        contrato.setPrioridadeAtendimento("baixa");

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

    public List<Contrato> listarContratos() {
        List<Contrato> listarContratos = new ArrayList<>();
        try {
            CollectionReference contratos = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> future = contratos.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Contrato Contrato = document.toObject(Contrato.class);
                listarContratos.add(Contrato);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listarContratos;
    }

    public boolean editarContrato(Contrato Contrato) {
        // Atualiza o contrato existente
        CollectionReference contrato = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = contrato.document(String.valueOf(Contrato.getIdContrato())).set(Contrato);

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirContrato(long idContrato) {
        // Exclui o contrato pelo ID
        CollectionReference contratos = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = contratos.document(String.valueOf(idContrato)).delete();

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

}