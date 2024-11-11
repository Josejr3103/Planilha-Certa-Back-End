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

    @GetMapping
    public ResponseEntity<List<Projetos>> listarProjetos() {
        List<Projetos> projetos = projetosService.listarProjetos();

        if (projetos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        } else {
            return ResponseEntity.ok(projetos); // Retorna 200 OK com a lista
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarProjeto(@PathVariable("id") long idProjeto, @RequestBody Projetos projeto) {
        projeto.setIdProjeto(idProjeto); // Define o ID do projeto com o valor do caminho da URL
        boolean atualizado = projetosService.editarProjeto(projeto);
        if (atualizado) {
            return ResponseEntity.ok("Projeto atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar projeto.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProjeto(@PathVariable("id") long idProjeto) {
        boolean excluido = projetosService.excluirProjeto(idProjeto);
        if (excluido) {
            return ResponseEntity.ok("Projeto excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir projeto.");
        }
    }
}
