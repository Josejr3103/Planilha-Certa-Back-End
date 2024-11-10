package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.ClassTB1Cliente.Cliente;
import com.teamten.planilha_certa.ClassTB1Cliente.ClientePadrao;
import com.teamten.planilha_certa.ClassTB1Cliente.ClienteVip;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private Firestore firestore;

    private final String COLLECTION_NAME = "clientes";

    public Cliente cadastrarCliente(Cliente cliente) {
        CollectionReference clientes = firestore.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = clientes.document(String.valueOf(cliente.getIdCliente())).set(cliente);

        try {
            future.get();  // Aguarda o Firestore concluir a operação
            return cliente;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cliente> listarClientes() {
        try {
            CollectionReference clientes = firestore.collection(COLLECTION_NAME);
            List<Cliente> listaClientes = new ArrayList<>();
            clientes.get().get().forEach(document -> {
                Cliente cliente = document.toObject(Cliente.class);
                listaClientes.add(cliente);
            });
            return listaClientes;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
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
}
