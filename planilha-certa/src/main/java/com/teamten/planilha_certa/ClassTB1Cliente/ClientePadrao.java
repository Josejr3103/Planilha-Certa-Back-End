package com.teamten.planilha_certa.ClassTB1Cliente;

public class ClientePadrao extends Cliente {

    public ClientePadrao() {
    }

    public ClientePadrao(long idCliente, String nomeCliente, String cpfCliente, String historicoContratos, int pontos) {
        super(idCliente, nomeCliente, cpfCliente,"Normal",  historicoContratos, pontos);
    }

}