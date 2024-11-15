package com.teamten.planilha_certa.ClassTB4Projetos;

public class Projetos {
    private long idProjeto;
    private String nomeProjeto;
    private String descricaoProjeto;
    private long idContrato;
    private long idConsultor;
    private String nomeConsultor;
    private String servico;
    private boolean cadastro;

    public Projetos(){
    }

    public Projetos(long idProjeto, String nomeProjeto, String descricaoProjeto, long idContrato, long idConsultor, String nomeConsultor, String servico, boolean cadastro) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.idContrato = idContrato;
        this.idConsultor = idConsultor;
        this.nomeConsultor = nomeConsultor;
        this.servico = servico;
        this.cadastro = cadastro;
    }


    public boolean isCadastro() {
        return cadastro;
    }

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
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

    public String getNomeConsultor() {
        return nomeConsultor;
    }

    public String getServico() {
        return servico;
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

    public void setNomeConsultor(String nomeConsultor) {
        this.nomeConsultor = nomeConsultor;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

}

