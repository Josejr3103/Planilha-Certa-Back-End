public class Consultoria {
    private long IdContrato;
    private String DataInicio;
    private String Prazo;
    private float ValorServico;
    private float Desconto;
    private float ValorLiquido;

    public Consultoria(long IdContrato, String DataInicio, String Prazo, float ValorServico, float Desconto,
            float ValorLiquido) {
        this.IdContrato = IdContrato;
        this.DataInicio = DataInicio;
        this.Prazo = Prazo;
        this.ValorServico = ValorServico;
        this.Desconto = Desconto;
        this.ValorLiquido = ValorLiquido;
    }

    // Getters e Setters
    public long getIdContrato() {
        return IdContrato;
    }

    public void setIdContrato(long idContrato) {
        this.IdContrato = idContrato;
    }

    public String getDataInicio() {
        return DataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.DataInicio = dataInicio;
    }

    public String getPrazo() {
        return Prazo;
    }

    public void setPrazo(String prazo) {
        this.Prazo = prazo;
    }

    public float getValorServico() {
        return ValorServico;
    }

    public void setValorServico(float valorServico) {
        this.ValorServico = valorServico;
    }

    public float getDesconto() {
        return Desconto;
    }

    public void setDesconto(float desconto) {
        this.Desconto = desconto;
    }

    public float getValorLiquido() {
        return ValorLiquido;
    }

    public void setValorLiquido(float valorLiquido) {
        this.ValorLiquido = valorLiquido;
    }

}
