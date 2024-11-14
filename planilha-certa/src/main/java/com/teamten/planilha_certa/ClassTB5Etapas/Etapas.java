package com.teamten.planilha_certa.ClassTB5Etapas;

public class Etapas {

    private long idEtapa;
    private long idProjeto;
    private long idContrato;
    private String nome;
    private boolean analiseInicial;
    private String statusAnaliseInicial;
    private boolean implementacao;
    private String statusImplementacao;
    private boolean revisaoFinal;
    private String statusRevisaoFinal;
    private float pagamento;
    private int faturamento;
    private boolean cadastro;

    public Etapas(long idEtapa, long idProjeto, long idContrato,String nome, boolean analiseInicial, String statusAnaliseInicial, boolean implementacao, String statusImplementacao, boolean revisaoFinal, String statusRevisaoFinal, float pagamento, int faturamento, boolean cadastro) {
        this.idEtapa = idEtapa;
        this.idProjeto = idProjeto;
        this.idContrato = idContrato;
        this.nome = nome;
        this.analiseInicial = analiseInicial;
        this.statusAnaliseInicial = statusAnaliseInicial;
        this.implementacao = implementacao;
        this.statusImplementacao = statusImplementacao;
        this.revisaoFinal = revisaoFinal;
        this.statusRevisaoFinal = statusRevisaoFinal;
        this.pagamento = pagamento;
        this.faturamento = faturamento;
        this.cadastro = cadastro;
    }

    public Etapas() {
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

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(long idContrato) {
        this.idContrato = idContrato;
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

    public boolean isAnaliseInicial() {
        return analiseInicial;
    }

    public void setAnaliseInicial(boolean analiseInicial) {
        this.analiseInicial = analiseInicial;
    }

    public String getStatusAnaliseInicial() {
        return statusAnaliseInicial;
    }

    public void setStatusAnaliseInicial(String statusAnaliseInicial) {
        this.statusAnaliseInicial = statusAnaliseInicial;
    }

    public boolean isImplementacao() {
        return implementacao;
    }

    public void setImplementacao(boolean implementacao) {
        this.implementacao = implementacao;
    }

    public String getStatusImplementacao() {
        return statusImplementacao;
    }

    public void setStatusImplementacao(String statusImplementacao) {
        this.statusImplementacao = statusImplementacao;
    }

    public boolean isRevisaoFinal() {
        return revisaoFinal;
    }

    public void setRevisaoFinal(boolean revisaoFinal) {
        this.revisaoFinal = revisaoFinal;
    }

    public String getStatusRevisaoFinal() {
        return statusRevisaoFinal;
    }

    public void setStatusRevisaoFinal(String statusRevisaoFinal) {
        this.statusRevisaoFinal = statusRevisaoFinal;
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
