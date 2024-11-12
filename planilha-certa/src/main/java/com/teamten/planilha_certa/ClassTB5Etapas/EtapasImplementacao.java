package com.teamten.planilha_certa.ClassTB5Etapas;

import java.sql.Date;

public class EtapasImplementacao extends Etapas {

    public EtapasImplementacao(long idProjeto, long idEtapa, Date prazo, float pagamento, int faturamento) {
        super(idProjeto, idEtapa, "Implementação", EtapaStatus.EM_ABERTO, prazo, pagamento, faturamento);
    }

    public EtapasImplementacao() {
    }
}
