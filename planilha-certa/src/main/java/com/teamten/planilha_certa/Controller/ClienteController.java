package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.Cliente;
import com.teamten.planilha_certa.ClientePadrao;
import com.teamten.planilha_certa.ClienteVip;
import com.teamten.planilha_certa.Consultor;
import com.teamten.planilha_certa.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/padrao")
    public ResponseEntity<ClientePadrao> cadastrarClientePadrao(@RequestBody ClientePadrao cliente) {
        ClientePadrao novoCliente = clienteService.cadastrarClientePadrao(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PostMapping("/vip")
    public ResponseEntity<ClienteVip> cadastrarClienteVip(@RequestBody ClienteVip cliente) {
        ClienteVip novoCliente = clienteService.cadastrarClienteVip(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable long id, @RequestBody Cliente clienteAtualizado) {
        Cliente clienteEditado = clienteService.editarCliente(id, clienteAtualizado);
        if (clienteEditado != null) {
            return ResponseEntity.ok(clienteEditado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable long id) {
        if (clienteService.excluirCliente(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }
}
