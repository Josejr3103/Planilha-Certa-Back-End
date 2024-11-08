package com.teamten.planilha_certa.ClassTB1Cliente;

public class ClientePadrao extends Cliente {

    public ClientePadrao(long idCliente, String nomeCliente, String historicoContratos, int pontos) {
        super(idCliente, nomeCliente, "Normal", historicoContratos, pontos);
    }

    // Métodos específicos para ClientePadrao podem ser adicionados aqui
}