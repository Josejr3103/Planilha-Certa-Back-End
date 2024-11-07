import java.util.ArrayList;
import java.util.Iterator;

public class ListaDeContratos {
   private ArrayList<Consultoria> cadastros = new ArrayList<>();

   public ListaDeContratos() {
   }

   public void AdicionarCadastro(long IdContrato, String DataInicio, String Prazo, float ValorServico, float Desconto,
         float ValorLiquido) {
      Consultoria consultoria = new Consultoria(IdContrato, DataInicio, Prazo, ValorLiquido, Desconto, ValorLiquido);
      this.cadastros.add(consultoria);
   }

   public void excluirContrato(long IdContrato) {
      Iterator<Consultoria> iterator = this.cadastros.iterator();
      while (iterator.hasNext()) {
         Consultoria cadastro = iterator.next();
         if (cadastro instanceof Consultoria) {
            if (cadastro.getIdContrato() == IdContrato) {
               iterator.remove();
            }
         }
      }
   }

   public void editarContrato(long IdContrato, String DataInicio, String Prazo, float ValorServico, float Desconto,
         float ValorLiquido) {
      Iterator<Consultoria> iterator = this.cadastros.iterator();
      while (iterator.hasNext()) {
         Consultoria cadastro = iterator.next();
         if (cadastro instanceof Consultoria) {
            if (cadastro.getIdContrato() == IdContrato) {
               cadastro.setDataInicio(DataInicio);
               cadastro.setPrazo(Prazo);
               cadastro.setValorServico(ValorServico);
               cadastro.setDesconto(Desconto);
               cadastro.setValorLiquido(ValorLiquido);
            }

         }
      }
   }
}
