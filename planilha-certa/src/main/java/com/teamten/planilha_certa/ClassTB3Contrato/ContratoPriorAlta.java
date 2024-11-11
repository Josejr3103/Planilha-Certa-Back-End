package com.teamten.planilha_certa.ClassTB3Contrato;

import java.util.Date;

public class ContratoPriorAlta extends Contrato {

    public ContratoPriorAlta(long idContrato, long idCliente, String nomeCliente, Date dataInicio, Date prazo,
                             float valorServico, float desconto) {
        super(idContrato, idCliente, nomeCliente, dataInicio, prazo, valorServico, desconto, "Alta");
    }

    public ContratoPriorAlta() {
    }
}