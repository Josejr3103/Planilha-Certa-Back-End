package com.teamten.planilha_certa;

public class Projetos {
    private long idProjeto;
    private String nomeProjeto;
    private String descricaoProjeto;
    private long idContrato;
    private long idConsultor;
    private String servico;

    private String etapas;public Projetos(long idProjeto, String nomeProjeto, String descricaoProjeto, long idContrato, long idConsultor, String servico, String etapas) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.idContrato = idContrato;
        this.idConsultor = idConsultor;
        this.servico = servico;
        this.etapas = etapas;
    }

    public long getIdProjeto() {
        return idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public long getIdContrato() {
        return idContrato;
    }

    public long getIdConsultor() {
        return idConsultor;
    }

    public String getServico() {
        return servico;
    }

    public String getEtapas() {
        return etapas;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public void setIdContrato(long idContrato) {
        this.idContrato = idContrato;
    }

    public void setIdConsultor(long idConsultor) {
        this.idConsultor = idConsultor;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public void setEtapas(String etapas) {
        this.etapas = etapas;
    }
}

