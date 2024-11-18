package com.teamten.planilha_certa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


//https://console.firebase.google.com/u/0/project/planilhacerta-114b1/firestore/databases/-default-/data/~2Fclientes~2F0?hl=pt-br

@SpringBootApplication
public class PlanilhaCertaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PlanilhaCertaApplication.class, args);

		System.out.println("O Projeto está rodando!");
	}
}