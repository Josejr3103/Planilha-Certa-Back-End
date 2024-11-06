package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.Cliente;
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

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientesDatabase.values());
    }
}
