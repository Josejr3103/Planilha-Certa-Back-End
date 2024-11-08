package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.Contrato;
import com.teamten.planilha_certa.ContratoPriorAlta;
import com.teamten.planilha_certa.ContratoPriorBaixa;
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

    public ContratoPriorAlta editarContratoPriorAlta(long id, ContratoPriorAlta contratoAtualizado) {
        if (contratoDatabase.containsKey(id) && contratoAtualizado.getPrioridadeAtendimento().equals("Alta")) {
            contratoAtualizado.setIdContrato(id);
            contratoDatabase.put(id, contratoAtualizado);
            return contratoAtualizado;
        }
        return null;
    }

    public ContratoPriorBaixa editarContratoPriorBaixa(long id, ContratoPriorBaixa contratoAtualizado) {
        if (contratoDatabase.containsKey(id) && contratoAtualizado.getPrioridadeAtendimento().equals("Baixa")) {
            contratoAtualizado.setIdContrato(id);
            contratoDatabase.put(id, contratoAtualizado);
            return contratoAtualizado;
        }
        return null;
    }

    public boolean excluirContratoPriorAlta(long id) {
        Contrato contrato = contratoDatabase.get(id);
        if (contrato != null && contrato.getPrioridadeAtendimento().equals("Alta")) {
            contratoDatabase.remove(id);
            return true;
        }
        return false;
    }

    public boolean excluirContratoPriorBaixa(long id) {
        Contrato contrato = contratoDatabase.get(id);
        if (contrato != null && contrato.getPrioridadeAtendimento().equals("Baixa")) {
            contratoDatabase.remove(id);
            return true;
        }
        return false;
    }

    public List<Contrato> listarContratos() {
        return new ArrayList<>(contratoDatabase.values());
    }
}