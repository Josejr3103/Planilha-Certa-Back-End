package com.teamten.planilha_certa.ClassTB3Contrato;

import java.util.Date;

public class ContratoPriorAlta extends Contrato {

    public ContratoPriorAlta(long idContrato, long idCliente, String nomeCliente, String cpfCliente, Date dataInicio, float valorServico, float desconto, float valorLiquido, String prioridadeAtendimento, boolean cadastro) {
        super(idContrato, idCliente, nomeCliente, cpfCliente, dataInicio, valorServico, desconto, valorLiquido, prioridadeAtendimento, cadastro);
    }

    public ContratoPriorAlta() {
    }
}