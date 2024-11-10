package com.teamten.planilha_certa.ClassTB5Etapas;

public class EtapasAnaliseInicial extends Etapas {

    public EtapasAnaliseInicial(long idProjeto, float pagamento, int faturamento) {
        super(idProjeto, "Análise Inicial", EtapaStatus.EM_ABERTO, pagamento, faturamento);
    }
}
