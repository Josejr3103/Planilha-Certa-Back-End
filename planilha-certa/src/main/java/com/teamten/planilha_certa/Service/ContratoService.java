package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.ClassTB3Contrato.Contrato;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorAlta;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorBaixa;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ContratoService {

    private final Map<Long, Contrato> contratoDatabase = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public ContratoPriorAlta criarContratoPriorAlta(ContratoPriorAlta contrato) {
        long id = idCounter.incrementAndGet();
        contrato.setIdContrato(id);
        contratoDatabase.put(id, contrato);
        return contrato;
    }

    public ContratoPriorBaixa criarContratoPriorBaixa(ContratoPriorBaixa contrato) {
        long id = idCounter.incrementAndGet();
        contrato.setIdContrato(id);
        contratoDatabase.put(id, contrato);
        return contrato;
    }

    public Contrato editarContrato(long id, Contrato contratoAtualizado) {
        if (contratoDatabase.containsKey(id)) {
            contratoAtualizado.setIdContrato(id);
            contratoDatabase.put(id, contratoAtualizado);
            return contratoAtualizado;
        }
        return null;
    }

    public boolean excluirContrato(long id) {
        return contratoDatabase.remove(id)!= null;
    }

    public List<Contrato> listarContratos() {
        return new ArrayList<>(contratoDatabase.values());
    }
}