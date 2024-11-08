package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.Etapas;
import com.teamten.planilha_certa.EtapasAnaliseInicial;
import com.teamten.planilha_certa.EtapasImplementacao;
import com.teamten.planilha_certa.EtapasRevisaoFinal;
import com.teamten.planilha_certa.Service.EtapasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etapas")
public class EtapasController {

    @Autowired
    private EtapasService etapasService;

    @PostMapping("/AnaliseInicial")
    public ResponseEntity<Etapas> criarEtapaAnaliseInicial(@RequestBody EtapasAnaliseInicial etapa) {
        EtapasAnaliseInicial novaEtapa = etapasService.criarEtapaAnaliseInicial(etapa);
        return new ResponseEntity<>(novaEtapa, HttpStatus.CREATED);
    }

    @PostMapping("/Implementacao")
    public ResponseEntity<Etapas> criarEtapaImplementacao(@RequestBody EtapasImplementacao etapa) {
        EtapasImplementacao novaEtapa = etapasService.criarEtapaImplementacao(etapa);
        return new ResponseEntity<>(novaEtapa, HttpStatus.CREATED);
    }

    @PostMapping("/RevisaoFinal")
    public ResponseEntity<Etapas> criarEtapaRevisaoFinal(@RequestBody EtapasRevisaoFinal etapa) {
        EtapasRevisaoFinal novaEtapa = etapasService.criarEtapaRevisaoFinal(etapa);
        return new ResponseEntity<>(novaEtapa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etapas> editarEtapa(@PathVariable long id, @RequestBody Etapas etapaAtualizada) {
        Etapas etapaEditada = etapasService.editarEtapa(id, etapaAtualizada);
        if (etapaEditada != null) {
            return ResponseEntity.ok(etapaEditada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEtapa(@PathVariable long id) {
        if (etapasService.excluirEtapa(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Etapas>> listarEtapas() {
        List<Etapas> etapas = etapasService.listarEtapas();
        return ResponseEntity.ok(etapas);
    }
}
