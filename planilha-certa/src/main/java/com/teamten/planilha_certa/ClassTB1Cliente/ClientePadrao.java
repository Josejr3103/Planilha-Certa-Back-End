package com.teamten.planilha_certa.ClassTB1Cliente;

public class ClientePadrao extends Cliente {

    // Construtor vazio necessário para o Firestore
    public ClientePadrao() {
    }

    public ClientePadrao(long idCliente, String nomeCliente, String cpfCliente, String historicoContratos, int pontos) {
        super(idCliente, nomeCliente, cpfCliente,"Normal",  historicoContratos, pontos);
    }

    // Métodos específicos para ClientePadrao podem ser adicionados aqui
}