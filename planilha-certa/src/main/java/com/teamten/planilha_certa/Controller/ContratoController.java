package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.Contrato;
import com.teamten.planilha_certa.ContratoPriorAlta;
import com.teamten.planilha_certa.ContratoPriorBaixa;
import com.teamten.planilha_certa.Service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping("/prior-alta")
    public ResponseEntity<ContratoPriorAlta> criarContratoPriorAlta(@RequestBody ContratoPriorAlta contrato) {
        ContratoPriorAlta novoContrato = contratoService.criarContratoPriorAlta(contrato);
        return new ResponseEntity<>(novoContrato, HttpStatus.CREATED);
    }

    @PostMapping("/prior-baixa")
    public ResponseEntity<ContratoPriorBaixa> criarContratoPriorBaixa(@RequestBody ContratoPriorBaixa contrato) {
        ContratoPriorBaixa novoContrato = contratoService.criarContratoPriorBaixa(contrato);
        return new ResponseEntity<>(novoContrato, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Contrato> editarContrato(long id, Contrato contratoAtualizado) {
        Contrato contratoEditado = contratoService.editarContrato(id, contratoAtualizado);
        if (contratoEditado != null) {
            return ResponseEntity.ok(contratoEditado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> excluirContrato(long id) {
        if (contratoService.excluirContrato(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listarContratos() {
        List<Contrato> contratos = contratoService.listarContratos();
        return ResponseEntity.ok(contratos);
    }
}