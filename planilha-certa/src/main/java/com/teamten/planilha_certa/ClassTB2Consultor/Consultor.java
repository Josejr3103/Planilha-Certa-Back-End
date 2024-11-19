package com.teamten.planilha_certa.ClassTB2Consultor;

public class Consultor {
    private long idConsultor;
    private String nomeConsultor;
    private String especializacao;
    private boolean alocado;

    public Consultor(long idConsultor, String nomeConsultor, String especializacao, boolean alocado) {
        this.idConsultor = idConsultor;
        this.nomeConsultor = nomeConsultor;
        this.especializacao = especializacao;
        this.alocado = alocado;
    }

    public Consultor() {
    }

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

    public boolean isAlocado() {
        return alocado;
    }
    public void setAlocado(boolean alocado) {
        this.alocado = alocado;
    }
}
