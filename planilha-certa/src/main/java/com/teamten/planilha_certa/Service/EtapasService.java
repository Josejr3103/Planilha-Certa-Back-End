package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.Etapas;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EtapasService {

    private final Map<Long, Etapas> etapasDatabase = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Etapas criarEtapa(Etapas etapa) {
        long id = idCounter.incrementAndGet();
        etapa.setIdProjeto(id);
        etapasDatabase.put(id, etapa);
        return etapa;
    }

    public Etapas editarEtapa(long id, Etapas etapaAtualizada) {
        if (etapasDatabase.containsKey(id)) {
            etapaAtualizada.setIdProjeto(id);
            etapasDatabase.put(id, etapaAtualizada);
            return etapaAtualizada;
        }
        return null;
    }

    public boolean excluirEtapa(long id) {
        return etapasDatabase.remove(id) != null;
    }

    public List<Etapas> listarEtapas() {
        return new ArrayList<>(etapasDatabase.values());
    }
}
