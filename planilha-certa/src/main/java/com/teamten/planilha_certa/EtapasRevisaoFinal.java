package com.teamten.planilha_certa;

public class EtapasRevisaoFinal extends Etapas {

    public EtapasRevisaoFinal(long idProjeto, float pagamento, int faturamento) {
        super(idProjeto, "Revisão Final", EtapaStatus.EM_ABERTO, pagamento, faturamento);
    }
}
