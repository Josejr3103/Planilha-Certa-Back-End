package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.ClassTB5Etapas.Etapas;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasAnaliseInicial;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasImplementacao;
import com.teamten.planilha_certa.ClassTB5Etapas.EtapasRevisaoFinal;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EtapasService {

    private final Map<Long, Etapas> etapasDatabase = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public EtapasAnaliseInicial criarEtapaAnaliseInicial(EtapasAnaliseInicial etapa) {
        long id = idCounter.incrementAndGet();
        etapa.setIdProjeto(id);
        etapasDatabase.put(id, etapa);
        return etapa;
    }

    public EtapasImplementacao criarEtapaImplementacao(EtapasImplementacao etapa) {
        long id = idCounter.incrementAndGet();
        etapa.setIdProjeto(id);
        etapasDatabase.put(id, etapa);
        return etapa;
    }

    public EtapasRevisaoFinal criarEtapaRevisaoFinal(EtapasRevisaoFinal etapa) {
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
