package com.teamten.planilha_certa.ClassTB1Cliente;

public class ClienteVip extends Cliente {

    public ClienteVip() {
        super();
    }

    public ClienteVip(long idCliente, String nomeCliente, String historicoContratos, int pontos) {
        super(idCliente, nomeCliente, "Vip", historicoContratos, pontos);
    }

    // Métodos específicos para ClienteVip podem ser adicionados aqui
}
