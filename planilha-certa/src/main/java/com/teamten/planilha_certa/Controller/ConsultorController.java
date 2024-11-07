package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.Consultor;
import com.teamten.planilha_certa.Service.ConsultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultores")
public class ConsultorController {

    @Autowired
    private ConsultorService consultorService;

    @PostMapping
    public ResponseEntity<Consultor> criarConsultor(@RequestBody Consultor consultor) {
        Consultor novoConsultor = consultorService.criarConsultor(consultor);
        return new ResponseEntity<>(novoConsultor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultor> editarConsultor(@PathVariable long id, @RequestBody Consultor consultorAtualizado) {
        Consultor consultorEditado = consultorService.editarConsultor(id, consultorAtualizado);
        if (consultorEditado != null) {
            return ResponseEntity.ok(consultorEditado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirConsultor(@PathVariable long id) {
        if (consultorService.excluirConsultor(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Consultor>> listarConsultores() {
        List<Consultor> consultores = consultorService.listarConsultores();
        return ResponseEntity.ok(consultores);
    }
}
