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

        cliente.setCategoriaCliente("Padrão");

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

        cliente.setCategoriaCliente("Vip");

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
}






/*


    private final Map<Long, Cliente> clientesDatabase = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();



    public ClientePadrao cadastrarClientePadrao(ClientePadrao cliente) {
        long id = idCounter.incrementAndGet();
        cliente.setIdCliente(id);
        clientesDatabase.put(id, cliente);
        return cliente;
    }

    public ClienteVip cadastrarClienteVip(ClienteVip cliente) {
        long id = idCounter.incrementAndGet();
        cliente.setIdCliente(id);
        clientesDatabase.put(id, cliente);
        return cliente;
    }

    public Cliente editarCliente(long id, Cliente clienteAtualizado) {
        if (clientesDatabase.containsKey(id)) {
            clienteAtualizado.setIdCliente(id);
            clientesDatabase.put(id, clienteAtualizado);
            return clienteAtualizado;
        }
        return null;
    }

    public boolean excluirCliente(long id) {
        return clientesDatabase.remove(id) != null;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientesDatabase.values());
    }
*/

