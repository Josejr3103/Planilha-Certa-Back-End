package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.Etapas;
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

    @PostMapping
    public ResponseEntity<Etapas> criarEtapa(@RequestBody Etapas etapa) {
        Etapas novaEtapa = etapasService.criarEtapa(etapa);
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
