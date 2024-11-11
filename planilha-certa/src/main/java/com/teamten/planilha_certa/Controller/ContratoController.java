package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.ClassTB3Contrato.Contrato;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorAlta;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorBaixa;
import com.teamten.planilha_certa.Service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping("/alta")
    public ResponseEntity<ContratoPriorAlta> cadastrarContratoPriorAlta(@RequestBody ContratoPriorAlta contrato) {
        ContratoPriorAlta novoContrato = contratoService.cadastrarContratoPriorAlta(contrato);
        if (novoContrato != null) {
            return new ResponseEntity<>(novoContrato, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/baixa")
    public ResponseEntity<ContratoPriorBaixa> cadastrarContratoPriorBaixa(@RequestBody ContratoPriorBaixa contrato) {
        ContratoPriorBaixa novoContrato = contratoService.cadastrarContratoPriorBaixa(contrato);
        if (novoContrato != null) {
            return new ResponseEntity<>(novoContrato, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listarContratos() {
        List<Contrato> contratos = contratoService.listarContratos();
        if (contratos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        } else {
            return ResponseEntity.ok(contratos); // Retorna 200 OK com a lista de contratos
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> editarContrato(@PathVariable("id") long idContrato, @RequestBody Contrato contrato) {
        contrato.setIdContrato(idContrato);
        boolean atualizado = contratoService.editarContrato(contrato);
        if (atualizado) {
            return ResponseEntity.ok("Contrato atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Contrato.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirContrato(@PathVariable("id") long idContrato) {
        boolean excluido = contratoService.excluirContrato(idContrato);
        if (excluido) {
            return ResponseEntity.ok("Contrato excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir Contrato.");
        }
    }

}