package com.teamten.planilha_certa.ClassTB5Etapas;

public class Etapas {

    private long idEtapa;
    private long idProjeto;
    private long idContrato;
    private String nome;
    private boolean analiseInicial;
    private String statusAnaliseInicial;
    private float pagamentoET1;
    private float descontoET1;
    private float liquidoET1;
    private boolean implementacao;
    private String statusImplementacao;
    private float pagamentoET2;
    private float descontoET2;
    private float liquidoET2;
    private boolean revisaoFinal;
    private String statusRevisaoFinal;
    private float pagamentoET3;
    private float descontoET3;
    private float liquidoET3;
    private int faturamento;
    private float desconto;
    private float liquido;
    private boolean cadastro;

    public Etapas() {
    }

    public Etapas(long idEtapa, long idProjeto, long idContrato, String nome, boolean analiseInicial, String statusAnaliseInicial, float pagamentoET1, float descontoET1, float liquidoET1, boolean implementacao, String statusImplementacao, float pagamentoET2, float descontoET2, float liquidoET2, boolean revisaoFinal, String statusRevisaoFinal, float pagamentoET3, float descontoET3, float liquidoET3, int faturamento, float desconto, float liquido, boolean cadastro) {
        this.idEtapa = idEtapa;
        this.idProjeto = idProjeto;
        this.idContrato = idContrato;
        this.nome = nome;
        this.analiseInicial = analiseInicial;
        this.statusAnaliseInicial = statusAnaliseInicial;
        this.pagamentoET1 = pagamentoET1;
        this.descontoET1 = descontoET1;
        this.liquidoET1 = liquidoET1;
        this.implementacao = implementacao;
        this.statusImplementacao = statusImplementacao;
        this.pagamentoET2 = pagamentoET2;
        this.descontoET2 = descontoET2;
        this.liquidoET2 = liquidoET2;
        this.revisaoFinal = revisaoFinal;
        this.statusRevisaoFinal = statusRevisaoFinal;
        this.pagamentoET3 = pagamentoET3;
        this.descontoET3 = descontoET3;
        this.liquidoET3 = liquidoET3;
        this.faturamento = faturamento;
        this.desconto = desconto;
        this.liquido = liquido;
        this.cadastro = cadastro;
    }

    public long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(long idEtapa) {
        this.idEtapa = idEtapa;
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

    public float getPagamentoET1() {
        return pagamentoET1;
    }

    public void setPagamentoET1(float pagamentoET1) {
        this.pagamentoET1 = pagamentoET1;
        this.liquidoET1 = pagamentoET1 - descontoET1;
    }

    public float getDescontoET1() {
        return descontoET1;
    }

    public void setDescontoET1(float descontoET1) {
        this.descontoET1 = descontoET1;
        this.liquidoET1 = pagamentoET1 - descontoET1;
    }

    public float getLiquidoET1() {
        return liquidoET1;
    }

    public void setLiquidoET1(float liquidoET1) {
        this.liquidoET1 = pagamentoET1 - descontoET1;
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

    public float getPagamentoET2() {
        return pagamentoET2;
    }

    public void setPagamentoET2(float pagamentoET2) {
        this.pagamentoET2 = pagamentoET2;
        this.liquidoET2 = pagamentoET2 - descontoET2;
    }

    public float getDescontoET2() {
        return descontoET2;
    }

    public void setDescontoET2(float descontoET2) {
        this.descontoET2 = descontoET2;
        this.liquidoET2 = pagamentoET2 - descontoET2;
    }

    public float getLiquidoET2() {
        return liquidoET2;
    }

    public void setLiquidoET2(float liquidoET2) {
        this.liquidoET2 = liquidoET2;
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

    public float getPagamentoET3() {
        return pagamentoET3;
    }

    public void setPagamentoET3(float pagamentoET3) {
        this.pagamentoET3 = pagamentoET3;
        this.liquidoET3 = pagamentoET3 - descontoET3;
    }

    public float getDescontoET3() {
        return descontoET3;
    }

    public void setDescontoET3(float descontoET3) {
        this.descontoET3 = descontoET3;
        this.liquidoET3 = pagamentoET3 - descontoET3;
    }

    public float getLiquidoET3() {
        return liquidoET3;
    }

    public void setLiquidoET3(float liquidoET3) {
        this.liquidoET3 = liquidoET3;
    }

    public int getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(int faturamento) {
        this.faturamento = faturamento;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public float getLiquido() {
        return liquido;
    }

    public void setLiquido(float liquido) {
        this.liquido = liquido;
    }

    public boolean isCadastro() {
        return cadastro;
    }

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }
}
