package com.teamten.planilha_certa;

import com.teamten.planilha_certa.Service.ClienteService;
import com.teamten.planilha_certa.Service.ProjetosService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PlanilhaCertaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PlanilhaCertaApplication.class, args);

		// Obtenha o bean do serviço
		ProjetosService projetosService = context.getBean(ProjetosService.class);

		// Crie um novo projeto para testar o cadastro
		Projetos novoProjeto = new Projetos(0, "Projeto Teste do Reinaldo", "Descrição Teste", 123, 456, "Serviço Teste", "Etapas Teste");
		Projetos projetoCadastrado = projetosService.cadastrarProjeto(novoProjeto);
		System.out.println("Projeto cadastrado: " + projetoCadastrado.getNomeProjeto());

		// Liste todos os projetos
		projetosService.listarProjetos().forEach(projeto -> System.out.println("Projeto: " + projeto.getNomeProjeto()));

		// Edite o projeto
		projetoCadastrado.setNomeProjeto("Projeto Teste Editado do Reinaldo");
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

		// Liste todos os projetos
		projetosService.listarProjetos().forEach(projeto -> System.out.println("Projeto: " + projeto.getNomeProjeto()));

		// Crie um novo projeto para testar o cadastro
		Projetos novoProjeto2 = new Projetos(1, "Projeto Teste 2", "Descrição Teste 2", 123, 456, "Serviço Teste 2", "Etapas Teste 2");
		Projetos projetoCadastrado2 = projetosService.cadastrarProjeto(novoProjeto2);
		System.out.println("Projeto cadastrado: " + projetoCadastrado2.getNomeProjeto());






		// Exemplo de uso de ClienteService
		ClienteService clienteService = context.getBean(ClienteService.class);

		ClienteVip clienteVip = new ClienteVip(0, "José", "Contrato1, Contrato2", 150);
		ClientePadrao clientePadrao = new ClientePadrao(0, "Warney", "Contrato3", 50);

		Cliente clienteVipCadastrado = clienteService.cadastrarCliente(clienteVip);
		Cliente clientePadraoCadastrado = clienteService.cadastrarCliente(clientePadrao);

		System.out.println("Cliente VIP cadastrado: " + clienteVipCadastrado.getNomeCliente());
		System.out.println("Cliente Padrão cadastrado: " + clientePadraoCadastrado.getNomeCliente());

		// Liste todos os clientes
		clienteService.listarClientes().forEach(cliente -> System.out.println("Cliente: " + cliente.getNomeCliente()));
	}


}