package br.com.projetoBanco.teste;

import java.util.Scanner;

import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.Conta;

public class Main {
	public static void main(String[] args) {

		
	}
}



/////Exemplo Sprint1
/*
 * static void menu1() { System.out.println("Selecione a opcao desejada: ");
 * System.out.println("1 - Cadastrar novo cliente");
 * System.out.println("2 - Cadastrar nova conta");
 * System.out.println("3 - Login"); System.out.println("4 - Sair");
 * 
 * }
 * 
 * static void menu2() { System.out.println("Selecione a opcao desejada: ");
 * System.out.println("1 - Sacar dinheiro");
 * System.out.println("2 - Depositar dinheiro");
 * System.out.println("3 - Consultar saldo"); System.out.println("4 - Sair");
 * 
 * }
 * 
 * public static void main(String[] args) { Scanner sc = new Scanner(System.in);
 * 
 * // Instancias Cliente cliente = new Cliente(); Conta conta = new Conta();
 * conta.setCliente(cliente);
 * 
 * int selecaoOpcao = 0;
 * 
 * while (selecaoOpcao != 4) { System.out.println("");
 * System.out.println("***Bem-Vindo ao Banco***");
 * 
 * if (!conta.isStatusLogin()) { menu1(); selecaoOpcao = sc.nextInt();
 * 
 * switch (selecaoOpcao) { case 1: cliente.cadastrarCliente(); break;
 * 
 * case 2: conta.cadastrarConta(cliente); break;
 * 
 * case 3: conta.login(); break;
 * 
 * case 4: System.out.println("O programa esta¡ sendo finalizado...");
 * System.exit(0);
 * 
 * default: System.out.println("Opcao invalida, tente novamente..."); break; } }
 * 
 * else { menu2(); selecaoOpcao = sc.nextInt();
 * 
 * switch (selecaoOpcao) { case 1: conta.saque(); break;
 * 
 * case 2: conta.depositar(); break;
 * 
 * case 3: conta.consultarSaldo(); break;
 * 
 * case 4: System.out.println("O programa esta sendo finalizado...");
 * System.exit(0);
 * 
 * default: System.out.println("Opcao invalida, tente novamente..."); break; } }
 * 
 * }
 * 
 * }
 * 
 * }
 */