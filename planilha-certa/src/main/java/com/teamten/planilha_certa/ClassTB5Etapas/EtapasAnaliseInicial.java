package com.teamten.planilha_certa.ClassTB5Etapas;

public class EtapasAnaliseInicial extends Etapas {

    public EtapasAnaliseInicial(long idProjeto, long idEtapa, float pagamento, int faturamento) {
        super(idProjeto, idEtapa, "Análise Inicial", EtapaStatus.EM_ABERTO, pagamento, faturamento);
    }

    public EtapasAnaliseInicial() {
    }
}
