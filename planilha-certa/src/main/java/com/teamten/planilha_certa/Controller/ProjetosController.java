package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.Projetos;
import com.teamten.planilha_certa.Service.ProjetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetosController {

    @Autowired
    private ProjetosService projetosService;

    @PostMapping
    public ResponseEntity<Projetos> cadastrarProjeto(@RequestBody Projetos projeto) {
        Projetos novoProjeto = projetosService.cadastrarProjeto(projeto);
        return new ResponseEntity<>(novoProjeto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projetos> editarProjeto(@PathVariable long id, @RequestBody Projetos projetoAtualizado) {
        Projetos projetoEditado = projetosService.editarProjeto(id, projetoAtualizado);
        if (projetoEditado != null) {
            return ResponseEntity.ok(projetoEditado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProjeto(@PathVariable long id) {
        if (projetosService.excluirProjeto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Projetos>> listarProjetos() {
        List<Projetos> projetos = projetosService.listarProjetos();
        return ResponseEntity.ok(projetos);
    }
}
