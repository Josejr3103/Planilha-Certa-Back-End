package com.teamten.planilha_certa;

public class Cliente {
    private long idCliente;
    private String nomeCliente;
    private String categoriaCliente;
    private String historicoContratos;
    private int pontos;

    public Cliente(long idCliente, String nomeCliente, String categoriaCliente, String historicoContratos, int pontos) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.categoriaCliente = categoriaCliente;
        this.historicoContratos = historicoContratos;
        this.pontos = pontos;
    }

    // Getters e Setters
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCategoriaCliente() {
        return categoriaCliente;
    }

    public void setCategoriaCliente(String categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }

    public String getHistoricoContratos() {
        return historicoContratos;
    }

    public void setHistoricoContratos(String historicoContratos) {
        this.historicoContratos = historicoContratos;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
