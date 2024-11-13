package com.teamten.planilha_certa.ClassTB5Etapas;

public class EtapasImplementacao extends Etapas {

    public EtapasImplementacao(long idProjeto, long idEtapa, String statusDaEtapa, float pagamento, int faturamento) {
        super(idProjeto, idEtapa, "Implementação", statusDaEtapa, pagamento, faturamento);
    }

    public EtapasImplementacao() {
    }
}
