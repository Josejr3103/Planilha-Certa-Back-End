package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.ClassTB3Contrato.Contrato;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorAlta;
import com.teamten.planilha_certa.ClassTB5Etapas.Etapas;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasAnaliseInicial;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasImplementacao;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasRevisaoFinal;
import com.teamten.planilha_certa.Service.EtapasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/etapas")
public class EtapasController {

    @Autowired
    private EtapasService etapasService;

    @PostMapping("/analiseinicial")
    public ResponseEntity<EtapasAnaliseInicial> cadastrarEtapasAnaliseInicial(@RequestBody EtapasAnaliseInicial etapa) {
        EtapasAnaliseInicial novoEtapa = etapasService.cadastrarEtapasAnaliseInicial(etapa);
        if (novoEtapa != null) {
            return new ResponseEntity<>(novoEtapa, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/implementacao")
    public ResponseEntity<EtapasImplementacao> cadastrarEtapasImplementacao(@RequestBody EtapasImplementacao etapa) {
        EtapasImplementacao novoEtapa = etapasService.cadastrarEtapasImplementacao(etapa);
        if (novoEtapa != null) {
            return new ResponseEntity<>(novoEtapa, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/revisaofinal")
    public ResponseEntity<EtapasRevisaoFinal> cadastrarEtapasRevisaoFinal(@RequestBody EtapasRevisaoFinal etapa) {
        EtapasRevisaoFinal novoEtapa = etapasService.cadastrarEtapasRevisaoFinal(etapa);
        if (novoEtapa != null) {
            return new ResponseEntity<>(novoEtapa, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Etapas>> listarEtapas() {
        List<Etapas> etapas = etapasService.listarEtapas();
        if (etapas.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        } else {
            return ResponseEntity.ok(etapas); // Retorna 200 OK com a lista de etapas
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarEtapa(@PathVariable("id") long idEtapa, @RequestBody Etapas etapas) {
        etapas.setIdEtapa(idEtapa);
        boolean atualizado = etapasService.editarEtapa(etapas);
        if (atualizado) {
            return ResponseEntity.ok("Etapa atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Etapa.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirEtapa(@PathVariable("id") long idEtapa) {
        boolean excluido = etapasService.excluirEtapa(idEtapa);
        if (excluido) {
            return ResponseEntity.ok("Etapa excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir Etapa.");
        }
    }










}
