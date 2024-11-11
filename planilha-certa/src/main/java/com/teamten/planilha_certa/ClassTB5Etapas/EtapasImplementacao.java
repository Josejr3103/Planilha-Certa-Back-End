package com.teamten.planilha_certa.ClassTB5Etapas;

public class EtapasImplementacao extends Etapas {

    public EtapasImplementacao(long idProjeto, long idEtapa, float pagamento, int faturamento) {
        super(idProjeto, idEtapa, "Implementação", EtapaStatus.EM_ABERTO, pagamento, faturamento);
    }

    public EtapasImplementacao() {
    }
}
