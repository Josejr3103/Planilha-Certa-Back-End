package com.teamten.planilha_certa.Service;

import com.teamten.planilha_certa.Consultor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ConsultorService {

    private final Map<Long, Consultor> consultoresDatabase = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Consultor criarConsultor(Consultor consultor) {
        long id = idCounter.incrementAndGet();
        consultor.setIdConsultor(id);
        consultoresDatabase.put(id, consultor);
        return consultor;
    }

    public Consultor editarConsultor(long id, Consultor consultorAtualizado) {
        if (consultoresDatabase.containsKey(id)) {
            consultorAtualizado.setIdConsultor(id);
            consultoresDatabase.put(id, consultorAtualizado);
            return consultorAtualizado;
        }
        return null;
    }

    public boolean excluirConsultor(long id) {
        return consultoresDatabase.remove(id) != null;
    }

    public List<Consultor> listarConsultores() {
        return new ArrayList<>(consultoresDatabase.values());
    }
}
