package com.teamten.planilha_certa.ClassTB3Contrato;

import java.util.Date;

public class Contrato {
    private long idContrato;
    private long idCliente;
    private String nomeCliente;
    private Date dataInicio;
    private Date prazo;
    private String prazoDescricao;
    private float valorServico;
    private float desconto;
    private float valorLiquido;
    private String prioridadeAtendimento;

    public Contrato(long idContrato, long idCliente, String nomeCliente, Date dataInicio, Date prazo, float valorServico,
                    float desconto, String prioridadeAtendimento) {
        this.idContrato = idContrato;
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.dataInicio = dataInicio;
        this.prazo = prazo;
        this.prazoDescricao = prazo.toString();
        this.valorServico = valorServico;
        this.desconto = desconto;
        this.valorLiquido = valorServico - desconto;
        this.prioridadeAtendimento = prioridadeAtendimento;
    }

    public Contrato() {
    }

    // Getters e Setters
    public long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(long idContrato) {
        this.idContrato = idContrato;
    }

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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public String getPrazoDescricao() {
        return prazoDescricao;
    }

    public float getValorServico() {
        return valorServico;
    }

    public void setValorServico(float valorServico) {
        this.valorServico = valorServico;
        this.valorLiquido = valorServico - desconto;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
        this.valorLiquido = valorServico - desconto;
    }

    public float getValorLiquido() {
        return valorLiquido;
    }

    public String getPrioridadeAtendimento() {
        return prioridadeAtendimento;
    }

    public void setPrioridadeAtendimento(String prioridadeAtendimento) {
        this.prioridadeAtendimento = prioridadeAtendimento;
    }
}