package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.ClassTB4Projetos.Projetos;
import com.teamten.planilha_certa.Service.ProjetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/projetos")
public class ProjetosController {

    @Autowired
    private ProjetosService projetosService;

    @PostMapping("/financeiro")
    public ResponseEntity<Projetos> cadastrarProjetoFinanceiro(@RequestBody Projetos projetos) {
        Projetos novoProjeto = projetosService.cadastrarProjetoFinanceiro(projetos);
        if (novoProjeto != null) {
            return new ResponseEntity<>(novoProjeto, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/gestao")
    public ResponseEntity<Projetos> cadastrarProjetoGestao(@RequestBody Projetos projetos) {
        Projetos novoProjeto = projetosService.cadastrarProjetoGestao(projetos);
        if (novoProjeto != null) {
            return new ResponseEntity<>(novoProjeto, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/ti")
    public ResponseEntity<Projetos> cadastrarProjetoTi(@RequestBody Projetos projetos) {
        Projetos novoProjeto = projetosService.cadastrarProjetoTi(projetos);
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
