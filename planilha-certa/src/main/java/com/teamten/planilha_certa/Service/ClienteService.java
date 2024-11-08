package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.Cliente;
import com.teamten.planilha_certa.ClientePadrao;
import com.teamten.planilha_certa.ClienteVip;
import com.teamten.planilha_certa.Consultor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClienteService {

    private final Map<Long, Cliente> clientesDatabase = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Cliente cadastrarCliente(Cliente cliente) {
        long id = idCounter.incrementAndGet();
        cliente.setIdCliente(id);
        clientesDatabase.put(id, cliente);
        return cliente;
    }

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
}
