package com.teamten.planilha_certa.ClassTB1Cliente;

public class ClienteVip extends Cliente {

    public ClienteVip() {
        super();
    }

    public ClienteVip(long idCliente, String nomeCliente, String cpfCliente, String historicoContratos, int pontos) {
        super(idCliente, nomeCliente, cpfCliente, "Vip",historicoContratos, pontos);
    }
}
