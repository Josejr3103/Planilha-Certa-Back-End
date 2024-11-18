package com.teamten.planilha_certa.ClassTB6Relatorios;

import java.util.Date;

public class Relatorios {
    long idRelatorio;
    String cpfCliente;
    String nomeCliente;
    String categoriaCliente;
    long  idContrato;
    Date dataInicio;
    float valorServico;
    float desconto;
    float valorLiquido;
    long idProjeto;
    String descicaoProjeto;
    String service;
    long  idConsultor;
    String nomeConsultor;
    String especializacao;
    long idEtapa;
    String nomeEtapa;
    boolean analiseInicial;
    String statusAnaliseInicial;
    float pagamentoET1;
    float descontoET1;
    float liquidoET1;
    boolean implementacao;
    String statusImplementacao;
    float pagamentoET2;
    float descontoET2;
    float liquidoET2;
    boolean revisaoFinal;
    String statusRevisaoFinal;
    float pagamentoET3;
    float descontoET3;
    float liquidoET3;
    String faturamento;
    float pagamento;
    float descontoEtapa;
    float liquido;

    public Relatorios(){

    }

    public Relatorios(long idRelatorio, String cpfCliente, String nomeCliente, String categoriaCliente, long idContrato, Date dataInicio, float valorServico, float desconto, float valorLiquido, long idProjeto, String descicaoProjeto, String service, long idConsultor, String nomeConsultor, String especializacao, long idEtapa, String nomeEtapa, boolean analiseInicial, String statusAnaliseInicial, float pagamentoET1, float descontoET1, float liquidoET1, boolean implementacao, String statusImplementacao, float pagamentoET2, float descontoET2, float liquidoET2, boolean revisaoFinal, String statusRevisaoFinal, float pagamentoET3, float descontoET3, float liquidoET3, String faturamento, float pagamento, float descontoEtapa, float liquido) {
        this.idRelatorio = idRelatorio;
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.categoriaCliente = categoriaCliente;
        this.idContrato = idContrato;
        this.dataInicio = dataInicio;
        this.valorServico = valorServico;
        this.desconto = desconto;
        this.valorLiquido = valorLiquido;
        this.idProjeto = idProjeto;
        this.descicaoProjeto = descicaoProjeto;
        this.service = service;
        this.idConsultor = idConsultor;
        this.nomeConsultor = nomeConsultor;
        this.especializacao = especializacao;
        this.idEtapa = idEtapa;
        this.nomeEtapa = nomeEtapa;
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
        this.pagamento = pagamento;
        this.descontoEtapa = descontoEtapa;
        this.liquido = liquido;
    }

    public long getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(long idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
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

    public long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(long idContrato) {
        this.idContrato = idContrato;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public float getValorServico() {
        return valorServico;
    }

    public void setValorServico(float valorServico) {
        this.valorServico = valorServico;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public float getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(float valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getDescicaoProjeto() {
        return descicaoProjeto;
    }

    public void setDescicaoProjeto(String descicaoProjeto) {
        this.descicaoProjeto = descicaoProjeto;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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

    public long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(long idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getNomeEtapa() {
        return nomeEtapa;
    }

    public void setNomeEtapa(String nomeEtapa) {
        this.nomeEtapa = nomeEtapa;
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
    }

    public float getDescontoET1() {
        return descontoET1;
    }

    public void setDescontoET1(float descontoET1) {
        this.descontoET1 = descontoET1;
    }

    public float getLiquidoET1() {
        return liquidoET1;
    }

    public void setLiquidoET1(float liquidoET1) {
        this.liquidoET1 = liquidoET1;
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
    }

    public float getDescontoET2() {
        return descontoET2;
    }

    public void setDescontoET2(float descontoET2) {
        this.descontoET2 = descontoET2;
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
    }

    public float getDescontoET3() {
        return descontoET3;
    }

    public void setDescontoET3(float descontoET3) {
        this.descontoET3 = descontoET3;
    }

    public float getLiquidoET3() {
        return liquidoET3;
    }

    public void setLiquidoET3(float liquidoET3) {
        this.liquidoET3 = liquidoET3;
    }

    public String getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(String faturamento) {
        this.faturamento = faturamento;
    }

    public float getPagamento() {
        return pagamento;
    }

    public void setPagamento(float pagamento) {
        this.pagamento = pagamento;
    }

    public float getDescontoEtapa() {
        return descontoEtapa;
    }

    public void setDescontoEtapa(float descontoEtapa) {
        this.descontoEtapa = descontoEtapa;
    }

    public float getLiquido() {
        return liquido;
    }

    public void setLiquido(float liquido) {
        this.liquido = liquido;
    }
}    