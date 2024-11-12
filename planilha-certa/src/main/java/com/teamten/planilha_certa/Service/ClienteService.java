package com.teamten.planilha_certa.Service;

import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB1Cliente.Cliente;
import com.teamten.planilha_certa.ClassTB1Cliente.ClientePadrao;
import com.teamten.planilha_certa.ClassTB1Cliente.ClienteVip;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ClienteService {@Autowired
private Firestore firestore;

    private static final String COLLECTION_NAME = "clientes";
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
                Cliente cliente = document.toObject(Cliente.class);
                maxId = Math.max(maxId, cliente.getIdCliente());
            }

            idCounter.set(maxId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ID counter", e);
        }
    }

    public ClientePadrao cadastrarClientePadrao(ClientePadrao cliente) {
        long newId = idCounter.incrementAndGet();
        cliente.setIdCliente(newId);

        CollectionReference clientes = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = clientes.document(String.valueOf(newId)).set(cliente);

        try {
            future.get();
            return cliente;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ClienteVip cadastrarClienteVip(ClienteVip cliente) {
        long newId = idCounter.incrementAndGet();
        cliente.setIdCliente(newId);

        CollectionReference clientes = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = clientes.document(String.valueOf(newId)).set(cliente);

        try {
            future.get();
            return cliente;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            CollectionReference clientes = firestore.collection(COLLECTION_NAME);
            ApiFuture<QuerySnapshot> future = clientes.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Cliente cliente = document.toObject(Cliente.class);
                listaClientes.add(cliente);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    public boolean editarCliente(Cliente cliente) {
        // Atualiza o cliente existente
        CollectionReference clientes = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = clientes.document(String.valueOf(cliente.getIdCliente())).set(cliente);

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirCliente(long idCliente) {
        // Exclui o cliente pelo ID
        CollectionReference clientes = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = clientes.document(String.valueOf(idCliente)).delete();

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

}



