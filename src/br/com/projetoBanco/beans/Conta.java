package br.com.projetoBanco.beans;

import java.util.Scanner;

public class Conta {
	Scanner sc = new Scanner(System.in);

	private String numeroConta;
	private double saldo;
	private Cliente cliente;
	private String senha;
	private static int contasCriadas = 0;
	private boolean statusLogin;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	/*
	 * public void setSaldo(double saldo) { this.saldo = saldo; }
	 */

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isStatusLogin() {
		return statusLogin;
	}

	public void setStatusLogin(boolean statusLogin) {
		this.statusLogin = statusLogin;
	}

	public void transferir() {

		double valorTransferir;
		String novaTentativaTransferir;

		System.out.println("***Area de transferência***");
		System.out.println("Informe o valor que você deseja transferir: ");
		valorTransferir = sc.nextDouble();

		// Saldo insuficiente
		while (valorTransferir > getSaldo()) {
			System.out.println("Transferência não realizada, saldo insuficiente");
			System.out.println("Seu saldo é: R$ " + getSaldo());

			System.out.println("Deseja inserir um novo valor? s/n");
			novaTentativaTransferir = sc.next();

			while (!novaTentativaTransferir.toLowerCase().equals("s")
					&& !novaTentativaTransferir.toLowerCase().equals("n")) {
				System.out.println("Inválido, tente novamente");
				System.out.println("Deseja inserir um novo valor? s/n");
				novaTentativaTransferir = sc.next();
			}

			// validação para nova tentativa
			if (novaTentativaTransferir.toLowerCase().equals("n")) {
				break;
			}

			System.out.println("***Area de transferência***");
			System.out.println("Informe o valor que você deseja transferir: ");
			valorTransferir = sc.nextDouble();
		}

		// saldo suficiente
		if (valorTransferir <= getSaldo()) {
			this.saldo -= valorTransferir;// atualização do saldo

			System.out.println("Transferência realizada com sucesso");
			System.out.println("Seu saldo é: R$ " + getSaldo());
		}
	}

	public void depositar() {
		double valorDepositar;

		System.out.println("***Área de Depósito***");
		System.out.println("Informe o valor que você deseja depositar: ");
		valorDepositar = sc.nextDouble();

		this.saldo += valorDepositar;// Atualização saldo
		System.out.println("Depósito realizado com sucesso.");
		System.out.println("Seu saldo é: R$ " + getSaldo());

	}

	public void consultarSaldo() {

		if (getSaldo() <= 5000) {
			cliente.setTipoCliente(TipoCliente.COMUM);
		} else if (getSaldo() > 5000 && getSaldo() <= 14999) {
			cliente.setTipoCliente(TipoCliente.SUPER);
		} else {
			cliente.setTipoCliente(TipoCliente.PREMIUM);
		}

		System.out.println(cliente);
		System.out.println("O número da conta é: " + getNumeroConta());
		System.out.println("Atualmente seu tipo de conta é: " + cliente.getTipoCliente());
		System.out.println("Seu saldo é: R$ " + getSaldo());
	}

	public void cadastrarConta(Cliente cliente) {
		setCliente(cliente);
		setNumeroConta(novaConta());
		this.saldo = 0.0;

		System.out.println("Cadastre uma senha (apenas 4 digitos): ");
		String senhaCadastrada = sc.next();

		while (!senhaCadastrada.matches("[0-9]*") || senhaCadastrada.length() != 4) {
			System.out.println("Senha inválida, tente novamente: ");
			senhaCadastrada = sc.next();
		}

		setSenha(senhaCadastrada);
		System.out.println("Cadastrado com sucesso!!!");
	}

	public String novaConta() {
		return String.valueOf(contasCriadas++);
	}

	public void saque() {
		double valorSaque;
		String novaTentativaSaque;

		System.out.println("***Área de Saque***");
		System.out.println("Informe o valor que você deseja retirar: ");
		valorSaque = sc.nextDouble();

		// Saldo insuficiente
		while (valorSaque > getSaldo()) {
			System.out.println("O saque não é possivel, saldo insuficiente");
			System.out.println("Seu saldo é: R$ " + getSaldo());

			System.out.println("Deseja inserir um novo valor? s/n");
			novaTentativaSaque = sc.next();

			while (!novaTentativaSaque.toLowerCase().equals("s") && !novaTentativaSaque.toLowerCase().equals("n")) {
				System.out.println("Inválido, tente novamente");
				System.out.println("Deseja inserir um novo valor? s/n");
				novaTentativaSaque = sc.next();
			}

			// validação para nova tentativa
			if (novaTentativaSaque.toLowerCase().equals("n")) {
				break;
			}

			System.out.println("***Área de Saque***");
			System.out.println("Informe o valor que você deseja retirar: ");
			valorSaque = sc.nextDouble();

		}

		if (valorSaque <= getSaldo()) {
			this.saldo -= valorSaque;// Atualização saldo
			System.out.println("Saque realizado com sucesso.");
			System.out.println("Seu saldo é: R$ " + getSaldo());
		}
	}

	public void login() {
		System.out.println("***Tela de login***");
		System.out.println("Insira o número do CPF: ");
		String cpf = sc.next();
		System.out.println("Insira a senha: ");
		String senha = sc.next();

		if (cpf.equals(cliente.getCpf()) && senha.equals(getSenha())) {
			setStatusLogin(true);
			System.out.println("");
			System.out.println("***Login realizado com sucesso***");
		}

	}

}