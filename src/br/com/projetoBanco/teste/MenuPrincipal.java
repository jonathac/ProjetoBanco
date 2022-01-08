package br.com.projetoBanco.teste;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.ContaCorrente;
import br.com.projetoBanco.beans.ContaPoupanca;
import br.com.projetoBanco.beans.Endereco;
import br.com.projetoBanco.beans.Pix;
import br.com.projetoBanco.beans.TipoChavePix;
import br.com.projetoBanco.bo.ClienteBo;
import br.com.projetoBanco.bo.ContaBo;
import br.com.projetoBanco.bo.ContaCorrenteBo;
import br.com.projetoBanco.bo.ContaPoupancaBo;
import br.com.projetoBanco.bo.EnderecoBo;
import br.com.projetoBanco.bo.PixBo;

public class MenuPrincipal {
	// criar metodos menus para cada fase e chamar no main

	Scanner sc = new Scanner(System.in);
	// Variaveis
	static int opcao = -1;
	String nomeCliente;
	String cpfCliente;
	String dataNascimentoCliente;
	String logradouroCliente;
	String numeroCliente;
	String cepCliente;
	String bairroCliente;
	String cidadeCliente;
	String estadoCliente;
	TipoChavePix tipoChavePix;
	String conteudoChavePix;

	// variaveis tipo objeto

	ArrayList<Cliente> cliente = new ArrayList<Cliente>();
	ArrayList<Endereco> endereco = new ArrayList<Endereco>();
	ArrayList<ContaCorrente> contaCorrente = new ArrayList<ContaCorrente>();
	ArrayList<ContaPoupanca> contaPoupanca = new ArrayList<ContaPoupanca>();
	ArrayList<Pix> pix = new ArrayList<Pix>();

	// Cliente[] cliente = new Cliente[10];
	// Endereco[] endereco = new Endereco[10];
	// ContaCorrente[] contaCorrente = new ContaCorrente[10];

	// Instancias
	ClienteBo clienteBo = new ClienteBo();
	EnderecoBo enderecoBo = new EnderecoBo();
	ContaCorrenteBo contaCorrenteBo = new ContaCorrenteBo();
	ContaPoupancaBo contaPoupancaBo = new ContaPoupancaBo();
	PixBo pixBo = new PixBo();

	public void menuPrincipal() {

		while (opcao != 0) {
			System.out.println("---Menu Principal---");
			System.out.println("1- Cadastrar Cliente");
			System.out.println("2- Cadastrar Conta/Pix");
			System.out.println("3- Transações");
			System.out.println("0- Sair");

			opcao = sc.nextInt();

			switch (opcao) {

			case 0:
				opcao = 0;
				System.out.println("Programa sendo finalizado....");
				System.out.println("....");
				System.out.println("...");
				System.out.println("..");
				System.out.println(".");

				break;
			case 1:
				cadastroCliente();
				break;

			case 2:
				menuCadastroConta();
				break;

			case 3:
				transacoes();
				break;

			default:
				System.out.println("Informe um valor válido!!!");
				break;
			}
		}
	}

	public void cadastroCliente() {
		sc.nextLine();
		System.out.println("Cadastro Cliente");
		System.out.println("Insira o nome: ");
		nomeCliente = sc.nextLine();
		System.out.println("Insira o cpf: ");
		cpfCliente = sc.nextLine();
		while (clienteBo.validacaoCpf(cpfCliente) == false) {
			System.out.println("Insira um valor válido para cpf: ");
			cpfCliente = sc.nextLine();
		}
		System.out.println("Insira sua data de nascimento: ");
		dataNascimentoCliente = sc.nextLine();

		System.out.println("---Endereco---");
		System.out.println("Insira o cep: ");
		cepCliente = sc.nextLine();
		while (enderecoBo.validarCep(cepCliente) == false) {
			System.out.println("Insira um valor válido para o cep: ");
			cepCliente = sc.nextLine();
		}
		System.out.println("Insira o logradouro: ");
		logradouroCliente = sc.nextLine();
		System.out.println("Insira o numero: ");
		numeroCliente = sc.nextLine();
		System.out.println("Insira o bairro: ");
		bairroCliente = sc.nextLine();
		System.out.println("Insira a cidade: ");
		cidadeCliente = sc.nextLine();
		System.out.println("Insira o estado: ");
		estadoCliente = sc.nextLine();

		endereco.add(enderecoBo.cadastrarEndereco(logradouroCliente, numeroCliente, cepCliente, bairroCliente,
				cidadeCliente, estadoCliente));

		cliente.add(clienteBo.cadastrarDados(cpfCliente, nomeCliente, dataNascimentoCliente,
				endereco.get(endereco.size() - 1)));

		System.out.println("Cliente " + cliente.get(cliente.size() - 1).getNome() + " cadastrado!");
	}

	public void menuCadastroConta() {

		System.out.println("---Cadastro de conta---");
		System.out.println("1- Cadastrar Conta Corrente");
		System.out.println("2- Cadastrar Conta Poupanca");
		System.out.println("3- Cadastrar chave pix");
		System.out.println("0- Sair");
		opcao = sc.nextInt();

		switch (opcao) {

		case 0:
			opcao = -1;
			break;
		case 1:
			opcao = selecionarCliente();
			// Criar conta corrente
			contaCorrente.add(contaCorrenteBo.cadastrarContaCorrente(cliente.get(opcao)));
			System.out.println("Conta cadastrada com sucesso!!!");
			opcao = -1;
			break;
		case 2:
			opcao = selecionarCliente();
			// Criar conta Poupança
			contaPoupanca.add(contaPoupancaBo.cadastrarContaPoupanca(cliente.get(opcao)));
			System.out.println("Conta cadastrada com sucesso!!!");
			opcao = -1;
			break;
		case 3:
			opcao = selecionarCliente();
			// Criar conta Poupança
			// contaPoupanca.add(contaPoupancaBo.cadastrarContaPoupanca(cliente.get(opcao)));
			// System.out.println("Conta cadastrada com sucesso!!!");
			cadastrarChavePix(opcao);
			opcao = -1;
			break;
		default:
			System.out.println("Conta não cadastrada, tente novamente");
			opcao = -1;
			break;
		}
	}

	public int selecionarCliente() {
		System.out.println("Selecione o Cliente:");

		for (int i = 0; i < cliente.size(); i++) {
			System.out.println(i + "- " + cliente.get(i).getNome());
		}
		opcao = sc.nextInt();

		while (opcao < 0 || opcao > (cliente.size() - 1)) {
			System.out.println("Insira um valor válido: ");
			opcao = sc.nextInt();
		}
		return opcao;
	}

	public void transacoes() {

		String clienteTransacao;
		System.out.println("---Transações---");
		System.out.println("Selecione a conta que deseja acessar: ");
		opcao = selecionarCliente();
		clienteTransacao = cliente.get(opcao).getNome();
		opcao = selecionarConta(clienteTransacao);

		switch (opcao) {

		case 0:
			System.out.println("Acesso negado, tente novamente!!!");
			opcao = -1;
			break;
		case 1:
			// acessar conta corrente
			opcao = acessarContaCorrente(clienteTransacao);
			subMenuTransacoesContaCorrente(opcao);
			opcao = -1;
			break;
		case 2:
//acessar conta poupanca
			break;
		default:
			System.out.println("Acesso negado, tente novamente!!!");
			opcao = -1;
			break;

		}

	}

	public void subMenuTransacoesContaCorrente(int indiceCliente) {

		System.out.println("---Transações---");
		System.out.println("1- Deposito");
		System.out.println("2- Saque");
		System.out.println("3- Transferência");
		System.out.println("4- Pix");
		System.out.println("5- Saldo");
		System.out.println("0- Sair");
		opcao = sc.nextInt();

		switch (opcao) {

		case 0:
			opcao = -1;
			break;
		case 1:
			System.out.println("---Deposito---");
			System.out.println("Insira um valor para o depósito: ");
			
			double deposito = sc.nextDouble();
			contaCorrente.get(indiceCliente).setSaldo(contaCorrenteBo.depositar(contaCorrente.get(indiceCliente).getSaldo(), deposito));
			
			///////////// gambi, mas funciona
			opcao = -1;
			break;
		case 2:

			opcao = -1;
			break;
		case 3:

			opcao = -1;
			break;
		case 4:

			opcao = -1;
			break;
		case 5:
			System.out.println("---Saldo---");
			System.out.println("Cliente: " + contaCorrente.get(indiceCliente).getCliente().getNome());
			System.out.println("Seu saldo: " + contaCorrente.get(indiceCliente).getSaldo());
			System.out.println("Seu tipo de conta é: " + contaCorrente.get(indiceCliente).getCliente().getTipoCliente());
			
			opcao = -1;
			break;
		default:
			System.out.println("Opção inválida, tente novamente!!!");
			opcao = -1;
			break;
		}

	}

	public void cadastrarChavePix(int cliente) {

		System.out.println("---Cadastrar Chave Pix---");
		System.out.println("Cliente selecionado: " + this.cliente.get(cliente).getNome());
		System.out.println("Selecione o tipo de chave Pix a cadastrar:");
		System.out.println("1- CPF");
		System.out.println("2- E-mail");
		System.out.println("3- Telefone");
		System.out.println("4- Aleatorio");
		opcao = sc.nextInt();
		sc.nextLine();

		switch (opcao) {

		case 0:
			opcao = -1;
			break;
		case 1:
			tipoChavePix = tipoChavePix.CPF;
			conteudoChavePix = this.cliente.get(cliente).getCpf();
			opcao = -1;
			break;
		case 2:
			tipoChavePix = tipoChavePix.EMAIL;
			conteudoChavePix = sc.nextLine();
			opcao = -1;
			break;
		case 3:
			tipoChavePix = tipoChavePix.TELEFONE;
			conteudoChavePix = sc.nextLine();
			opcao = -1;
			break;
		case 4:
			tipoChavePix = tipoChavePix.ALEATORIO;
			conteudoChavePix = UUID.randomUUID().toString();
			System.out.println("Sua chave Pix selecionado é: " + conteudoChavePix);
			opcao = -1;
			break;
		default:
			System.out.println("PIX não cadastrado, tente novamente");
			opcao = -1;
			break;
		}

		pix.add(pixBo.cadastrarPix(tipoChavePix, conteudoChavePix));
		if (pix.get(pix.size() - 1).isAtivado() == true) {
			System.out.println("Sua chave pix está ativa.");
			System.out.println("Tipo de chave selecionada: " + pix.get(pix.size() - 1).getTipoChave());
			System.out.println("Chave cadastrada: " + pix.get(pix.size() - 1).getConteudoChave());
		}
	}

	public int selecionarConta(String cliente) {
		String opcaoConta;
		int retorno;
		System.out.println("---Transações---");
		System.out.println("Selecione a opcao de conta: ");

		for (int i = 0; i < contaCorrente.size(); i++) {
			if (contaCorrente.get(i).getCliente().getNome().equals(cliente)) {
				System.out.println("CC - Conta Corrente");
			}
		}
		for (int i = 0; i < contaPoupanca.size(); i++) {
			if (contaPoupanca.get(i).getCliente().getNome().equals(cliente)) {
				System.out.println("CP - Conta Poupanca");
			}
		}
		opcaoConta = sc.next().toLowerCase();

		while (!opcaoConta.equals("cc") && !opcaoConta.equals("cp")) {
			System.out.println("Insira uma opção válida: ");
			opcaoConta = sc.next();
		}

		if (opcaoConta.equals("cc")) {
			retorno = 1;
		} else if (opcaoConta.equals("cp")) {
			retorno = 2;
		} else {
			retorno = 0;
		}

		return retorno;
		// pensar em metodo que devolva tipo de conta a se acessar, levar usuario a
		// conta corrente ou conta poupanca
	}

	// teste
	public int acessarContaCorrente(String clienteTransacao) {
		int retorno = -1;
		for (int i = 0; i < contaCorrente.size(); i++) {
			if(contaCorrente.get(i).getCliente().getNome().equals(clienteTransacao)) {
				retorno = i;
			}
			else {
				retorno = -1;
			}
		}
		
		return retorno;
	}

}
