// Arquivo: ProjetoApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        // Obtenha o bean do serviço
        ProjetosService projetosService = context.getBean(ProjetosService.class);

        // Crie um novo projeto para testar o cadastro
        Projetos novoProjeto = new Projetos(0, "Projeto Teste", "Descrição Teste", 123, 456, "Serviço Teste", "Etapas Teste");
        Projetos projetoCadastrado = projetosService.cadastrarProjeto(novoProjeto);
        System.out.println("Projeto cadastrado: " + projetoCadastrado.getNomeProjeto());

        // Liste todos os projetos
        projetosService.listarProjetos().forEach(projeto -> System.out.println("Projeto: " + projeto.getNomeProjeto()));

        // Edite o projeto
        projetoCadastrado.setNomeProjeto("Projeto Teste Editado");
        Projetos projetoEditado = projetosService.editarProjeto(projetoCadastrado.getIdProjeto(), projetoCadastrado);
        if (projetoEditado != null) {
            System.out.println("Projeto editado: " + projetoEditado.getNomeProjeto());
        } else {
            System.out.println("Projeto não encontrado para edição.");
        }

        // Copie o projeto
        Projetos projetoCopiado = projetosService.copiarProjeto(projetoCadastrado.getIdProjeto());
        if (projetoCopiado != null) {
            System.out.println("Projeto copiado: " + projetoCopiado.getNomeProjeto() + " com novo ID: " + projetoCopiado.getIdProjeto());
        } else {
            System.out.println("Projeto não copiado.");
        }

        // Exclua o projeto
        boolean excluido = projetosService.excluirProjeto(projetoCadastrado.getIdProjeto());
        System.out.println("Projeto excluído: " + (excluido ? "Sim" : "Não"));
    }
}