package com.teamten.planilha_certa;

import com.teamten.planilha_certa.Service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Date;

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






		// Exemplo de uso de ConsultorService
		ConsultorService consultorService = context.getBean(ConsultorService.class);

		ConsultorEspeciFinanceiro consultorFinanceiro = new ConsultorEspeciFinanceiro(0, "Reinaldo");
		ConsultorEspeciGestao consultorGestao = new ConsultorEspeciGestao(0, "Felipe");
		ConsultorEspeciTI consultorTI = new ConsultorEspeciTI(0, "Alexandre");

		Consultor consultorFinanceiroCadastrado = consultorService.criarConsultor(consultorFinanceiro);
		Consultor consultorGestaoCadastrado = consultorService.criarConsultor(consultorGestao);
		Consultor consultorTICadastrado = consultorService.criarConsultor(consultorTI);

		System.out.println("Consultor Financeiro cadastrado: " + consultorFinanceiroCadastrado.getNomeConsultor());
		System.out.println("Consultor Gestão cadastrado: " + consultorGestaoCadastrado.getNomeConsultor());
		System.out.println("Consultor TI cadastrado: " + consultorTICadastrado.getNomeConsultor());

		// Liste todos os consultores
		consultorService.listarConsultores().forEach(consultor -> System.out.println("Consultor: " + consultor.getNomeConsultor()));



		// Exemplo de uso de EtapasService
		EtapasService etapasService = context.getBean(EtapasService.class);

		EtapasAnaliseInicial etapaAnalise = new EtapasAnaliseInicial(1, 1000.0f, 200);
		EtapasImplementacao etapaImplementacao = new EtapasImplementacao(2, 2000.0f, 500);
		EtapasRevisaoFinal etapaRevisao = new EtapasRevisaoFinal(3, 500.0f, 100);

		Etapas etapaAnaliseCadastrada = etapasService.criarEtapa(etapaAnalise);
		Etapas etapaImplementacaoCadastrada = etapasService.criarEtapa(etapaImplementacao);
		Etapas etapaRevisaoCadastrada = etapasService.criarEtapa(etapaRevisao);

		System.out.println("Etapa Análise Inicial cadastrada: " + etapaAnaliseCadastrada.getNome());
		System.out.println("Etapa Implementação cadastrada: " + etapaImplementacaoCadastrada.getNome());
		System.out.println("Etapa Revisão Final cadastrada: " + etapaRevisaoCadastrada.getNome());

		// Liste todas as etapas
		etapasService.listarEtapas().forEach(etapa -> System.out.println("Etapa: " + etapa.getNome()));

		// Exemplo de uso de ContratoService
		ContratoService contratoService = context.getBean(ContratoService.class);

		ContratoPriorAlta contratoAlta = new ContratoPriorAlta(0, 123, "Cliente A", new Date(1), new Date(System.currentTimeMillis() + 86400000L), 10000.0f, 500.0f);
		ContratoPriorBaixa contratoBaixa = new ContratoPriorBaixa(0, 456, "Cliente B", new Date(2), new Date(System.currentTimeMillis() + 172800000L), 8000.0f, 300.0f);

		ContratoPriorAlta contratoAltaCadastrado = contratoService.criarContratoPriorAlta(contratoAlta);
		ContratoPriorBaixa contratoBaixaCadastrado = contratoService.criarContratoPriorBaixa(contratoBaixa);

		System.out.println("Contrato Prioridade Alta cadastrado: " + contratoAltaCadastrado.getNomeCliente());
		System.out.println("Contrato Prioridade Baixa cadastrado: " + contratoBaixaCadastrado.getNomeCliente());

		// Liste todos os contratos
		contratoService.listarContratos().forEach(contrato -> System.out.println("Contrato: " + contrato.getNomeCliente()));

	}


}