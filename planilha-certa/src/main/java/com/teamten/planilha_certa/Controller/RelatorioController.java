package com.teamten.planilha_certa.Controller;

import com.teamten.planilha_certa.ClassTB6Relatorio.Relatorios;
import com.teamten.planilha_certa.Service.RelatoriosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatoriosService relatoriosService;

    @GetMapping("/por-cpf")
    public ResponseEntity<?> gerarRelatoriosPorCpf(@RequestParam String cpfCliente) {
        try {
            List<Relatorios> relatoriosList = relatoriosService.gerarRelatoriosPorCpf(cpfCliente);
            if (relatoriosList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum relatório encontrado para o CPF informado.");
            }
            return ResponseEntity.ok(relatoriosList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    /*
    @PostMapping("/criar")
    public ResponseEntity<?> criarRelatorio(@RequestBody Relatorios relatorio) {
        try {
            Relatorios novoRelatorio = relatoriosService.criarRelatorio(relatorio);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoRelatorio);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar relatório: " + e.getMessage());
        }
    }
     */
}
