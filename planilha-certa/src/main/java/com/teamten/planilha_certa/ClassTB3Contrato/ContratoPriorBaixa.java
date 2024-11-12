package com.teamten.planilha_certa.ClassTB3Contrato;

import java.util.Date;

public class ContratoPriorBaixa extends Contrato {

    public ContratoPriorBaixa(long idContrato, long idCliente, String nomeCliente, Date dataInicio,
                              float valorServico, float desconto) {
        super(idContrato, idCliente, nomeCliente, dataInicio, valorServico, desconto, "Baixa");
    }

    public ContratoPriorBaixa() {
    }
}