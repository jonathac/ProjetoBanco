package br.com.projetoBanco.beans;

import java.util.Scanner;

public class Cliente {
	Scanner sc = new Scanner(System.in);

	private String cpf;
	private String nome;
	private TipoCliente tipoCliente;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public void cadastrarCliente() {
		System.out.println("Cadastro de novo usuário");
		System.out.println("Insira o nome completo");
		setNome(sc.nextLine());

		System.out.println("Informe os dígitos do CPF");
		setCpf(sc.next());

		while (!getCpf().matches("[0-9]*") || getCpf().length() != 11) {
			System.out.println("Insira apenas os 11 digitos do CPF");
			setCpf(sc.next());
		}

		System.out.println("Cadastrado com sucesso!!!");

	}

	@Override
	public String toString() {
		return "Cliente: " + nome + "\n CPF: " + cpf;

	}
}