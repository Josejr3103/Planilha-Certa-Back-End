package com.teamten.planilha_certa.ClassTB5Etapas;

import java.sql.Date;

public class EtapasAnaliseInicial extends Etapas {

    public EtapasAnaliseInicial(long idProjeto, long idEtapa, Date prazo, float pagamento, int faturamento) {
        super(idProjeto, idEtapa, "Análise Inicial", EtapaStatus.EM_ABERTO, prazo, pagamento, faturamento);
    }

    public EtapasAnaliseInicial() {
    }
}
