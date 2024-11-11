package com.teamten.planilha_certa.ClassTB5Etapas;

public class EtapasRevisaoFinal extends Etapas {

    public EtapasRevisaoFinal(long idProjeto, long idEtapa, float pagamento, int faturamento) {
        super(idProjeto, idEtapa, "Revisão Final", EtapaStatus.EM_ABERTO, pagamento, faturamento);
    }

    public EtapasRevisaoFinal() {
    }
}
