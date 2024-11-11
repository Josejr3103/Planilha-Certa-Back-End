package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.ClassTB2Consultor.Consultor;
import com.teamten.planilha_certa.ClassTB2Consultor.ConsultorEspeciFinanceiro;
import com.teamten.planilha_certa.ClassTB2Consultor.ConsultorEspeciGestao;
import com.teamten.planilha_certa.ClassTB2Consultor.ConsultorEspeciTI;
import com.teamten.planilha_certa.Service.ConsultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/consultores")
public class ConsultorController {

    @Autowired
    private ConsultorService consultorService;

    @PostMapping("/financeiro")
    public ResponseEntity<ConsultorEspeciFinanceiro> cadastrarConsultorEspeciFinanceiro(@RequestBody ConsultorEspeciFinanceiro consultor) {
        ConsultorEspeciFinanceiro novoConsultor = consultorService.cadastrarConsultorEspeciFinanceiro(consultor);
        if (novoConsultor != null) {
            return new ResponseEntity<>(novoConsultor, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/gestao")
    public ResponseEntity<ConsultorEspeciGestao> cadastrarConsultorEspeciGestao(@RequestBody ConsultorEspeciGestao consultor) {
        ConsultorEspeciGestao novoConsultor = consultorService.cadastrarConsultorEspeciGestao(consultor);
        if (novoConsultor != null) {
            return new ResponseEntity<>(novoConsultor, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/ti")
    public ResponseEntity<ConsultorEspeciTI> cadastrarConsultorEspeciTI(@RequestBody ConsultorEspeciTI consultor) {
        ConsultorEspeciTI novoConsultor = consultorService.cadastrarConsultorEspeciTI(consultor);
        if (novoConsultor != null) {
            return new ResponseEntity<>(novoConsultor, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Consultor>> listarConsultores() {
        List<Consultor> consultores = consultorService.listarConsultores();
        if (consultores.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        } else {
            return ResponseEntity.ok(consultores); // Retorna 200 OK com a lista de consultores
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarConsultor(@PathVariable("id") long idConsultor, @RequestBody Consultor consultor) {
        consultor.setIdConsultor(idConsultor);
        boolean atualizado = consultorService.editarConsultor(consultor);
        if (atualizado) {
            return ResponseEntity.ok("Consultor atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar consultor.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirConsultor(@PathVariable("id") long idConsultor) {
        boolean excluido = consultorService.excluirConsultor(idConsultor);
        if (excluido) {
            return ResponseEntity.ok("Consultor excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir consultor.");
        }
    }
}