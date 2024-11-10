package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.ClassTB4Projetos.Projetos;
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
    public ResponseEntity<Projetos> cadastrarProjeto(@RequestBody Projetos projetos) {
        Projetos novoProjeto = projetosService.cadastrarProjeto(projetos);
        if (novoProjeto != null) {
            return new ResponseEntity<>(novoProjeto, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /*
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
    */


    @GetMapping
    public ResponseEntity<List<Projetos>> listarProjetos() {
        List<Projetos> projetos = projetosService.listarProjetos();

        if (projetos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        } else {
            return ResponseEntity.ok(projetos); // Retorna 200 OK com a lista
        }
    }
}
