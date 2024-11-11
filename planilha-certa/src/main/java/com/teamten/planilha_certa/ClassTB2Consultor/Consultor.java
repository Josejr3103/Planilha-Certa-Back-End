package com.teamten.planilha_certa.ClassTB2Consultor;

public class Consultor {
    private long idConsultor;
    private String nomeConsultor;
    private String especializacao;

    public Consultor(long idConsultor, String nomeConsultor, String especializacao) {
        this.idConsultor = idConsultor;
        this.nomeConsultor = nomeConsultor;
        this.especializacao = especializacao;
    }

    public Consultor() {
    }

    // Getters e Setters
    public long getIdConsultor() {
        return idConsultor;
    }

    public void setIdConsultor(long idConsultor) {
        this.idConsultor = idConsultor;
    }

    public String getNomeConsultor() {
        return nomeConsultor;
    }

    public void setNomeConsultor(String nomeConsultor) {
        this.nomeConsultor = nomeConsultor;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }
}
