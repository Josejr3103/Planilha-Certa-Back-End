package com.teamten.planilha_certa.ClassTB5Etapas;

public class EtapasImplementacao extends Etapas {

    public EtapasImplementacao(long idProjeto, float pagamento, int faturamento) {
        super(idProjeto, "Implementação", EtapaStatus.EM_ABERTO, pagamento, faturamento);
    }
}
