package com.teamten.planilha_certa.ClassTB5Etapas;

public class EtapasAnaliseInicial extends Etapas {

    public EtapasAnaliseInicial(long idProjeto, long idEtapa, String statusDaEtapa ,float pagamento, int faturamento) {
        super(idProjeto, idEtapa, "Análise Inicial", statusDaEtapa, pagamento, faturamento);
    }

    public EtapasAnaliseInicial() {
    }
}
