package com.teamten.planilha_certa.ClassTB3Contrato;

import java.util.Date;

public class ContratoPriorBaixa extends Contrato {

    public ContratoPriorBaixa(long idContrato, long idCliente, String nomeCliente, Date dataInicio, Date prazo,
                              float valorServico, float desconto) {
        super(idContrato, idCliente, nomeCliente, dataInicio, prazo, valorServico, desconto, "Baixa");
    }
}