package com.teamten.planilha_certa.ClassTB5Etapas;

import java.sql.Date;

public class EtapasRevisaoFinal extends Etapas {

    public EtapasRevisaoFinal(long idProjeto, long idEtapa, Date prazo, float pagamento, int faturamento) {
        super(idProjeto, idEtapa, "Revisão Final", EtapaStatus.EM_ABERTO, prazo, pagamento, faturamento);
    }

    public EtapasRevisaoFinal() {
    }
}
