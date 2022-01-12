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
	ContaBo contaBo = new ContaBo();
	PixBo pixBo = new PixBo();

	public void menuPrincipal() {

		while (opcao != 0) {
			System.out.println("---Menu Principal---");
			System.out.println("1- Cadastrar Cliente");
			System.out.println("2- Cadastrar Conta/Pix");
			System.out.println("3- Transações");
			System.out.println("4- Cobrar Taxas/Rendimentos");
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
				menuCadastroConta();
				break;

			case 2:
				menuCadastroConta();
				break;

			case 3:
				transacoes();
				break;

			case 4:

				for (int i = 0; i < contaCorrente.size(); i++) {
					cobraTaxaManutencao(contaCorrente.get(i));
				}
				for (int i = 0; i < contaPoupanca.size(); i++) {
					cobraTaxaRendimento(contaPoupanca.get(i));
				}
				System.out.println("Taxas aplicadas e rendimentos aplicadas com sucesso!!!");
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
		boolean status = false;

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

			for (ContaCorrente contaCorrente : contaCorrente) {
				if (contaCorrente.getCliente().getNome().equals(cliente.get(opcao).getNome())) {
					status = true;
				}
			}

			if (status == true) {
				System.out.println("Conta Corrente já existente...");
			} else {
				contaCorrente.add(contaCorrenteBo.cadastrarContaCorrente(cliente.get(opcao)));
				System.out.println("Conta cadastrada com sucesso!!!");
			}
			opcao = -1;
			break;
		case 2:
			opcao = selecionarCliente();
			// Criar conta Poupança
			for (ContaPoupanca contaPoupanca : contaPoupanca) {
				if (contaPoupanca.getCliente().getNome().equals(cliente.get(opcao).getNome())) {
					status = true;
				}
			}

			if (status == true) {
				System.out.println("Conta Poupanca já existente...");
			} else {
				contaPoupanca.add(contaPoupancaBo.cadastrarContaPoupanca(cliente.get(opcao)));
				System.out.println("Conta cadastrada com sucesso!!!");
			}

			opcao = -1;
			break;
		case 3:
			opcao = selecionarCliente();
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
			opcao = acessarContaPoupanca(clienteTransacao);
			subMenuTransacoesContaPoupanca(opcao);
			opcao = -1;
			break;
		default:
			System.out.println("Acesso negado, tente novamente!!!");
			opcao = -1;
			break;

		}

	}

	public void subMenuTransacoesContaCorrente(int indiceCliente) {

		boolean status = false;
		double deposito = 0.0;
		double saque = 0.0;
		double valorTransferir = 0.0;
		String clienteTransacao;
		String chavePix;

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

			deposito = sc.nextDouble();
			contaBo.depositar(contaCorrente.get(indiceCliente), deposito);
			System.out.println("Deposito realizado com sucesso!!!");
			opcao = -1;
			break;
		case 2:
			System.out.println("---Saque---");
			System.out.println("Insira um valor para o depósito: ");

			saque = sc.nextDouble();
			status = contaBo.saque(contaCorrente.get(indiceCliente), saque);

			if (status == true) {
				System.out.println("Saque realizado com sucesso!!!");
			} else {
				System.out.println("Saldo indispónivel, tente novamente...");
			}

			opcao = -1;
			break;
		case 3:
			System.out.println("---Transferência---");
			System.out.println("Insira um valor para a transferência: ");
			valorTransferir = sc.nextDouble();
			System.out.println("Selecione a conta que deseja enviar o valor: ");
			opcao = selecionarCliente();
			clienteTransacao = cliente.get(opcao).getNome();
			opcao = selecionarConta(clienteTransacao);

			switch (opcao) {

			case 0:
				System.out.println("Acesso negado, tente novamente!!!");
				opcao = -1;
				break;
			case 1:
				// conta corrente ----> conta corrente
				opcao = acessarContaCorrente(clienteTransacao);
				status = contaBo.transferencia(valorTransferir, contaCorrente.get(indiceCliente),
						contaCorrente.get(opcao), false);
				if (status == true) {
					System.out.println("Transferencia realizada com sucesso...");

				} else {
					System.out.println("Não foi possivel realizar transferencia, tente novamente...");
				}
				opcao = -1;
				break;
			case 2:
				// conta corrente ----> conta poupanca
				opcao = acessarContaPoupanca(clienteTransacao);
				status = contaBo.transferencia(valorTransferir, contaCorrente.get(indiceCliente),
						contaPoupanca.get(opcao), true);
				if (status == true) {
					System.out.println("Transferencia realizada com sucesso...");
					// taxa transacao corrente --- poupanca
				} else {
					System.out.println("Não foi possivel realizar transferencia, tente novamente...");
				}
				opcao = -1;
				break;

			default:
				System.out.println("Acesso negado, tente novamente!!!");
				opcao = -1;
				break;

			}
			opcao = -1;
			break;
		case 4:
//construir pix
			System.out.println("---Pix---");
			System.out.println("Insira a chave pix da conta a receber: ");
			chavePix = sc.next();
			System.out.println("Insira o valor da trasnferencia: ");
			valorTransferir = sc.nextDouble();
			status = contaBo.transferencia(valorTransferir, contaCorrente.get(indiceCliente),
					consultarChavePix(chavePix), false);

			if (status == true) {
				System.out.println("Transferencia realizada com sucesso...");
			} else {
				System.out.println("Não foi possivel realizar transferencia, tente novamente...");
			}

			opcao = -1;
			break;
		case 5:
			System.out.println("---Saldo---");
			System.out.println(contaBo.exibirSaldo(contaCorrente.get(indiceCliente),contaCorrente.get(indiceCliente).getCliente()));
			
			opcao = -1;
			break;
		default:
			System.out.println("Opção inválida, tente novamente!!!");
			opcao = -1;
			break;
		}

	}

	public void subMenuTransacoesContaPoupanca(int indiceCliente) {

		boolean status = false;
		double deposito = 0.0;
		double saque = 0.0;
		double valorTransferir = 0.0;
		String clienteTransacao;
		String chavePix;

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

			deposito = sc.nextDouble();
			contaBo.depositar(contaPoupanca.get(indiceCliente), deposito);
			System.out.println("Deposito realizado com sucesso!!!");
			opcao = -1;
			break;
		case 2:
			System.out.println("---Saque---");
			System.out.println("Insira um valor para o depósito: ");

			saque = sc.nextDouble();
			status = contaBo.saque(contaPoupanca.get(indiceCliente), saque);

			if (status == true) {
				System.out.println("Saque realizado com sucesso!!!");
			} else {
				System.out.println("Saldo indispónivel, tente novamente...");
			}

			opcao = -1;
			break;
		case 3:
			System.out.println("---Transferência---");
			System.out.println("Insira um valor para a transferência: ");
			valorTransferir = sc.nextDouble();
			System.out.println("Selecione a conta que deseja enviar o valor: ");
			opcao = selecionarCliente();
			clienteTransacao = cliente.get(opcao).getNome();
			opcao = selecionarConta(clienteTransacao);

			switch (opcao) {

			case 0:
				System.out.println("Acesso negado, tente novamente!!!");
				opcao = -1;
				break;
			case 1:
				// conta poupanca ----> conta poupanca
				opcao = acessarContaPoupanca(clienteTransacao);
				status = contaBo.transferencia(valorTransferir, contaPoupanca.get(indiceCliente),
						contaPoupanca.get(opcao), false);
				if (status == true) {
					System.out.println("Transferencia realizada com sucesso...");

				} else {
					System.out.println("Não foi possivel realizar transferencia, tente novamente...");
				}
				opcao = -1;
				break;
			case 2:
				// conta poupanca ----> conta poupanca
				opcao = acessarContaPoupanca(clienteTransacao);
				status = contaBo.transferencia(valorTransferir, contaPoupanca.get(indiceCliente),
						contaCorrente.get(opcao), true);
				if (status == true) {
					System.out.println("Transferencia realizada com sucesso...");
					// taxa transacao corrente --- poupanca
				} else {
					System.out.println("Não foi possivel realizar transferencia, tente novamente...");
				}
				opcao = -1;
				break;

			default:
				System.out.println("Acesso negado, tente novamente!!!");
				opcao = -1;
				break;

			}
			opcao = -1;
			break;
		case 4:
			System.out.println("---Pix---");
			System.out.println("Insira a chave pix da conta a receber: ");
			chavePix = sc.next();
			System.out.println("Insira o valor da trasnferencia: ");
			valorTransferir = sc.nextDouble();
			status = contaBo.transferencia(valorTransferir, contaPoupanca.get(indiceCliente),
					consultarChavePix(chavePix), false);

			if (status == true) {
				System.out.println("Transferencia realizada com sucesso...");
			} else {
				System.out.println("Não foi possivel realizar transferencia, tente novamente...");
			}

			opcao = -1;
			break;
		case 5:
			System.out.println("---Saldo---");
			System.out.println(contaBo.exibirSaldo(contaPoupanca.get(indiceCliente),contaPoupanca.get(indiceCliente).getCliente()));
			opcao = -1;
			break;
		default:
			System.out.println("Opção inválida, tente novamente!!!");
			opcao = -1;
			break;
		}

	}

	public void cadastrarChavePix(int cliente) {
		if (contaCorrente.get(acessarContaCorrente(this.cliente.get(cliente).getNome())).getPix().isAtivado()) {
			System.out.println("Chave pix já cadastrada.");
		} else {

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

			this.pixBo.addPixConta(contaCorrente.get(acessarContaCorrente(this.cliente.get(cliente).getNome())),
					pix.get(pix.size() - 1));
			if (pix.get(pix.size() - 1).isAtivado() == true) {
				System.out.println("Sua chave pix está ativa.");
				System.out.println("Tipo de chave selecionada: " + pix.get(pix.size() - 1).getTipoChave());
				System.out.println("Chave cadastrada: " + pix.get(pix.size() - 1).getConteudoChave());
			}
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

	public int acessarContaCorrente(String clienteTransacao) {
		int retorno = -1;
		for (int i = 0; i < contaCorrente.size(); i++) {
			if (contaCorrente.get(i).getCliente().getNome().equals(clienteTransacao)) {
				retorno = i;
			}
		}

		return retorno;
	}

	public int acessarContaPoupanca(String clienteTransacao) {
		int retorno = -1;
		for (int i = 0; i < contaPoupanca.size(); i++) {
			if (contaPoupanca.get(i).getCliente().getNome().equals(clienteTransacao)) {
				retorno = i;
			}
		}

		return retorno;
	}

	public Conta consultarChavePix(String chavePix) {

		Conta conta = null;

		for (ContaCorrente contaCorrente : contaCorrente) {
			if (contaCorrente.getPix().getConteudoChave().equals(chavePix)) {
				conta = contaCorrente;
			}
		}

		return conta;
	}

	public void cobraTaxaManutencao(ContaCorrente contaCorrente) {
		contaCorrenteBo.descontarTaxa(contaCorrente);
	}

	public void cobraTaxaRendimento(ContaPoupanca contaPoupanca) {
		contaPoupancaBo.acrescentarRendimento(contaPoupanca);
	}

}
