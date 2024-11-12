package com.teamten.planilha_certa.ClassTB5Etapas;

import java.util.Date;

public class Etapas {
    private long idProjeto;
    private long idEtapa;
    private String nome;
    private EtapaStatus statusDaEtapa;
    private Date prazo;
    private float pagamento;
    private int faturamento;

    public Etapas(long idProjeto, long idEtapa, String nome, EtapaStatus statusDaEtapa, Date prazo, float pagamento, int faturamento) {
        this.idProjeto = idProjeto;
        this.idEtapa = idEtapa;
        this.nome = nome;
        this.statusDaEtapa = statusDaEtapa;
        this.prazo = prazo;
        this.pagamento = pagamento;
        this.faturamento = faturamento;
    }

    public Etapas() {
    }

    // Getters e Setters
    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(long idEtapa) {
        this.idEtapa = idEtapa;
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

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
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
