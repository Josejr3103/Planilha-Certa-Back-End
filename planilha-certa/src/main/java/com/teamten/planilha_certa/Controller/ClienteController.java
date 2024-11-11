package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.ClassTB1Cliente.Cliente;
import com.teamten.planilha_certa.ClassTB1Cliente.ClientePadrao;
import com.teamten.planilha_certa.ClassTB1Cliente.ClienteVip;
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
        if (novoCliente != null) {
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/vip")
    public ResponseEntity<ClienteVip> cadastrarClienteVip(@RequestBody ClienteVip cliente) {
        ClienteVip novoCliente = clienteService.cadastrarClienteVip(cliente);
        if (novoCliente != null) {
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        } else {
            return ResponseEntity.ok(clientes); // Retorna 200 OK com a lista de clientes
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarCliente(@PathVariable("id") long idCliente, @RequestBody Cliente cliente) {
        cliente.setIdCliente(idCliente);
        boolean atualizado = clienteService.editarCliente(cliente);
        if (atualizado) {
            return ResponseEntity.ok("Cliente atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar cliente.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCliente(@PathVariable("id") long idCliente) {
        boolean excluido = clienteService.excluirCliente(idCliente);
        if (excluido) {
            return ResponseEntity.ok("Cliente excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir cliente.");
        }
    }
}