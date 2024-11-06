package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.Projetos;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProjetosService {

    private final Map<Long, Projetos> database = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Projetos cadastrarProjeto(Projetos projeto) {
        long id = idCounter.incrementAndGet();
        projeto.setIdProjeto(id);
        database.put(id, projeto);
        return projeto;
    }

    public Projetos editarProjeto(long id, Projetos projetoAtualizado) {
        if (database.containsKey(id)) {
            projetoAtualizado.setIdProjeto(id);
            database.put(id, projetoAtualizado);
            return projetoAtualizado;
        }
        return null;
    }

    public Projetos copiarProjeto(long id) {
        Projetos projetoExistente = database.get(id);
        if (projetoExistente != null) {
            long novoId = idCounter.incrementAndGet();
            Projetos novoProjeto = new Projetos(
                    novoId,
                    projetoExistente.getNomeProjeto(),
                    projetoExistente.getDescricaoProjeto(),
                    projetoExistente.getIdContrato(),
                    projetoExistente.getIdConsultor(),
                    projetoExistente.getServico(),
                    projetoExistente.getEtapas()
            );
            database.put(novoId, novoProjeto);
            return novoProjeto;
        }
        return null;
    }

    public boolean excluirProjeto(long id) {
        return database.remove(id) != null;
    }

    public List<Projetos> listarProjetos() {
        return new ArrayList<>(database.values());
    }
}
