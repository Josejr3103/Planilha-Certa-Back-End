package com.teamten.planilha_certa.ClassTB5Etapas;

public class Etapas {
    private long idProjeto;
    private String nome;
    private EtapaStatus statusDaEtapa;
    private float pagamento;
    private int faturamento;

    public Etapas(long idProjeto, String nome, EtapaStatus statusDaEtapa, float pagamento, int faturamento) {
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.statusDaEtapa = statusDaEtapa;
        this.pagamento = pagamento;
        this.faturamento = faturamento;
    }

    // Getters e Setters
    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EtapaStatus getStatusDaEtapa() {
        return statusDaEtapa;
    }

    public void setStatusDaEtapa(EtapaStatus statusDaEtapa) {
        this.statusDaEtapa = statusDaEtapa;
    }

    public float getPagamento() {
        return pagamento;
    }

    public void setPagamento(float pagamento) {
        this.pagamento = pagamento;
    }

    public int getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(int faturamento) {
        this.faturamento = faturamento;
    }
}
