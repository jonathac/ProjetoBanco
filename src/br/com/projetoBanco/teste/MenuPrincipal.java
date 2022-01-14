package br.com.projetoBanco.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import br.com.projetoBanco.Utils.BancoDados;
import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.ContaCorrente;
import br.com.projetoBanco.beans.ContaPoupanca;
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
	Date dataNascimentoCliente;
	String logradouroCliente;
	String numeroCliente;
	String cepCliente;
	String bairroCliente;
	String cidadeCliente;
	String estadoCliente;
	TipoChavePix tipoChavePix;
	String conteudoChavePix;

	// Instancias
	ClienteBo clienteBo = new ClienteBo();
	EnderecoBo enderecoBo = new EnderecoBo();
	ContaCorrenteBo contaCorrenteBo = new ContaCorrenteBo();
	ContaPoupancaBo contaPoupancaBo = new ContaPoupancaBo();
	ContaBo contaBo = new ContaBo();
	PixBo pixBo = new PixBo();
	BancoDados bancoDados = new BancoDados();

	public void menuPrincipal() {

		if (bancoDados.getCliente().size() == 0) {
			System.out.println("Sem clientes cadastrados");
			cadastroCliente();
			do {
				System.out.println("---Cadastro de conta---");
				System.out.println("1- Cadastrar Conta Corrente");
				System.out.println("2- Cadastrar Conta Poupanca");
				opcao = sc.nextInt();
			} while (opcao != 1 && opcao != 2);

			switch (opcao) {
			case 1:
				bancoDados.cadastrarContaCorrente(contaCorrenteBo
						.cadastrarContaCorrente(bancoDados.getCliente().get(bancoDados.getCliente().size() - 1)));
				bancoDados.cadastrarPix(pixBo.cadastrarPix(tipoChavePix.CPF, ""));
				System.out.println("Conta cadastrada com sucesso!!!");
				opcao = -1;
				break;
			case 2:
				bancoDados.cadastrarContaPoupanca(contaPoupancaBo
						.cadastrarContaPoupanca(bancoDados.getCliente().get(bancoDados.getCliente().size() - 1)));
				bancoDados.cadastrarPix(pixBo.cadastrarPix(tipoChavePix.CPF, ""));
				System.out.println("Conta cadastrada com sucesso!!!");
				opcao = -1;
				break;
			default:
				System.out.println("Erro");
				opcao = -1;
				break;
			}
		}

		while (opcao != 0) {
			System.out.println("---Menu Principal---");
			System.out.println("1- Cadastrar Cliente");
			System.out.println("2- Cadastrar Conta/Pix");
			System.out.println("3- Transações");
			System.out.println("4- Cobrar Taxas/Rendimentos");
			System.out.println("0- Sair");

			opcao = sc.nextInt();
			sc.nextLine();
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
				boolean status = false;
				cadastroCliente();
				do {
					System.out.println("---Cadastro de conta---");
					System.out.println("1- Cadastrar Conta Corrente");
					System.out.println("2- Cadastrar Conta Poupanca");
					opcao = sc.nextInt();
				} while (opcao != 1 && opcao != 2);

				switch (opcao) {

				case 1:

					for (ContaCorrente obj : bancoDados.getContaCorrente()) {
						if (obj.getCliente().getNome()
								.equals(bancoDados.getCliente().get(bancoDados.getCliente().size() - 1).getNome())) {
							status = true;
						}
					}

					if (status == true) {
						System.out.println("Conta Corrente já existente...");
					} else {
						bancoDados.cadastrarContaCorrente(contaCorrenteBo.cadastrarContaCorrente(
								bancoDados.getCliente().get(bancoDados.getCliente().size() - 1)));
						System.out.println("Conta cadastrada com sucesso!!!");
					}
					opcao = -1;
					break;
				case 2:

					for (ContaPoupanca obj : bancoDados.getContaPoupanca()) {
						if (obj.getCliente().getNome()
								.equals(bancoDados.getCliente().get(bancoDados.getCliente().size() - 1).getNome())) {
							status = true;
						}
					}

					if (status == true) {
						System.out.println("Conta Corrente já existente...");
					} else {
						bancoDados.cadastrarContaPoupanca(contaPoupancaBo.cadastrarContaPoupanca(
								bancoDados.getCliente().get(bancoDados.getCliente().size() - 1)));
						System.out.println("Conta cadastrada com sucesso!!!");
					}
					opcao = -1;
					break;

				default:
					System.out.println("Erro");
					opcao = -1;
					break;
				}
				break;

			case 2:
				menuCadastroConta();
				break;

			case 3:
				transacoes();
				break;

			case 4:

				for (ContaCorrente obj : bancoDados.getContaCorrente()) {
					cobraTaxaManutencao(obj);
				}
				for (ContaPoupanca obj : bancoDados.getContaPoupanca()) {
					cobraTaxaRendimento(obj);
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
		boolean sair = false;
		System.out.println("Cadastro Cliente");
		System.out.println("Insira o nome: ");
		nomeCliente = sc.nextLine();
		System.out.println("Insira o cpf: ");
		cpfCliente = sc.nextLine();
		while (clienteBo.validacaoCpf(cpfCliente) == false) {
			System.out.println("Insira um valor válido para cpf: ");
			cpfCliente = sc.nextLine();
		}
		for (Cliente obj : bancoDados.getCliente()) {
			if (obj.getCpf().equals(cpfCliente)) {
				System.out.println("CPF já cadastrado");
				System.out.println("Tente outra opcao");
				sair = true;
			}
		}
		if (sair == false) {
			// data de nascimento
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Informe a data de nascimento: (dd/MM/yyyy)");
			String data = sc.nextLine();
			boolean status = false;
			while (status == false) {
				try {
					dataNascimentoCliente = sdf.parse(data);
					status = true;
				} catch (ParseException e) {
					System.out.println("Data de nascimento inválida");
					System.out.println("Informe a data de nascimento: (dd/MM/yyyy)");
					data = sc.nextLine();
				}
			}

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

			bancoDados.cadastroEndereco(enderecoBo.cadastrarEndereco(logradouroCliente, numeroCliente, cepCliente,
					bairroCliente, cidadeCliente, estadoCliente));
			bancoDados.cadastroCliente(clienteBo.cadastrarDados(cpfCliente, nomeCliente, dataNascimentoCliente,
					bancoDados.getEndereco().get(bancoDados.getEndereco().size() - 1)));

			System.out.println("Cliente " + bancoDados.getCliente().get(bancoDados.getCliente().size() - 1).getNome()
					+ " cadastrado!");

		}

	}

	public void menuCadastroConta() {
		System.out.println("---Cadastro de conta---");
		System.out.println("Informe o CPF do cliente titular da conta: ");
		cpfCliente = sc.nextLine();
		while (clienteBo.validacaoCpf(cpfCliente) == false) {
			System.out.println("Insira um valor válido para cpf: ");
			cpfCliente = sc.nextLine();
		}
		
		boolean status = false;

		try {
			
		bancoDados.buscarCliente(cpfCliente).equals(null);
		
		System.out.println("---Cadastro de conta---");
		System.out.println("1- Cadastrar Conta Corrente");
		System.out.println("2- Cadastrar Conta Poupanca");
		System.out.println("3- Cadastrar Chave Pix");
		System.out.println("0- Sair");
		opcao = sc.nextInt();
			
		} catch (Exception e) {
			System.out.println("Cliente nao encontrado!!!");
			System.out.println("Tente Novamente...");
			opcao = 0;
		}
		
		switch (opcao) {

		case 0:
			opcao = -1;
			break;
		case 1:
			// Criar conta corrente

			for (ContaCorrente obj : bancoDados.getContaCorrente()) {
				if (obj.getCliente().getNome().equals(bancoDados.buscarCliente(cpfCliente).getNome())) {
					status = true;
				}
			}

			if (status == true) {
				System.out.println("Conta Corrente já existente...");
			} else {
				bancoDados.getContaCorrente()
						.add(contaCorrenteBo.cadastrarContaCorrente(bancoDados.buscarCliente(cpfCliente)));
			//	bancoDados.cadastrarPix(pixBo.cadastrarPix(tipoChavePix.CPF, ""));
				System.out.println("Conta cadastrada com sucesso!!!");
			}
			opcao = -1;
			break;
		case 2:
			// Criar conta Poupança
			for (ContaPoupanca contaPoupanca : bancoDados.getContaPoupanca()) {
				if (contaPoupanca.getCliente().getNome().equals(bancoDados.buscarCliente(cpfCliente).getNome())) {
					status = true;
				}
			}

			if (status == true) {
				System.out.println("Conta Poupanca já existente...");
			} else {
				bancoDados.getContaPoupanca().add(contaPoupancaBo.cadastrarContaPoupanca((bancoDados.buscarCliente(cpfCliente))));
			//	bancoDados.cadastrarPix(pixBo.cadastrarPix(tipoChavePix.CPF, ""));
				System.out.println("Conta cadastrada com sucesso!!!");
			}

			opcao = -1;
			break;
		case 3:
			cadastrarChavePix(bancoDados.buscarCliente(cpfCliente));
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

		for (Cliente obj : bancoDados.getCliente()) {

			System.out.println(bancoDados.getCliente().indexOf(obj) + "- " + obj.getNome());

		}
		opcao = sc.nextInt();

		while (opcao < 0 || opcao > (bancoDados.getCliente().size() - 1)) {
			System.out.println("Insira um valor válido: ");
			opcao = sc.nextInt();
		}
		return opcao;
	}

	public void transacoes() {

		
		System.out.println("---Transações---");
		System.out.println("Informe o CPF do titular da conta: ");
		cpfCliente = sc.nextLine();
		while (clienteBo.validacaoCpf(cpfCliente) == false) {
			System.out.println("Insira um valor válido para cpf: ");
			cpfCliente = sc.nextLine();
		}

		try {
			
			bancoDados.buscarCliente(cpfCliente).equals(null);
			opcao = selecionarConta(bancoDados.buscarCliente(cpfCliente));		
			
			} catch (Exception e) {
				opcao = 0;
			}

		switch (opcao) {

		case 0:
			System.out.println("Cliente nao encontrado!!!");
			System.out.println("Tente Novamente...");
			opcao = -1;
			break;
		case 1:
			// acessar conta corrente
			subMenuTransacoesContaCorrente(bancoDados.acessarContaCorrente(bancoDados.buscarCliente(cpfCliente)));
			opcao = -1;
			break;
		case 2:
			// acessar conta poupanca
			subMenuTransacoesContaPoupanca(bancoDados.acessarContaPoupanca(bancoDados.buscarCliente(cpfCliente)));
			opcao = -1;
			break;
		default:
			System.out.println("Acesso negado, tente novamente!!!");
			opcao = -1;
			break;

		}

	}

	public void subMenuTransacoesContaCorrente(ContaCorrente contaCorrente) {

		boolean status = false;
		double deposito = 0.0;
		double saque = 0.0;
		double valorTransferir = 0.0;
		
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
			contaBo.depositar(contaCorrente, deposito);
			System.out.println("Deposito realizado com sucesso!!!");
			opcao = -1;
			break;
		case 2:
			System.out.println("---Saque---");
			System.out.println("Insira um valor para o depósito: ");

			saque = sc.nextDouble();
			status = contaBo.saque(contaCorrente, saque);

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
			//System.out.println("Selecione a conta que deseja enviar o valor: ");
			System.out.println("Informe o CPF da conta de destino: ");
			cpfCliente = sc.nextLine();
			while (clienteBo.validacaoCpf(cpfCliente) == false) {
				System.out.println("Insira um valor válido para cpf: ");
				cpfCliente = sc.nextLine();
			}
						
			//opcao = selecionarCliente();
			//clienteTransacao = bancoDados.buscarCliente(cpfCliente).getNome();
			opcao = selecionarConta(bancoDados.buscarCliente(cpfCliente));

			switch (opcao) {

			case 0:
				System.out.println("Acesso negado, tente novamente!!!");
				opcao = -1;
				break;
			case 1:
				// conta corrente ----> conta corrente
				status = contaBo.transferencia(valorTransferir, contaCorrente,
						bancoDados.acessarContaCorrente(bancoDados.buscarCliente(cpfCliente)), false);
				if (status == true) {
					System.out.println("Transferencia realizada com sucesso...");

				} else {
					System.out.println("Não foi possivel realizar transferencia, tente novamente...");
				}
				opcao = -1;
				break;
			case 2:
				// conta corrente ----> conta poupanca
				status = contaBo.transferencia(valorTransferir, contaCorrente,
						bancoDados.acessarContaPoupanca(bancoDados.buscarCliente(cpfCliente)), true);
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
			status = contaBo.transferencia(valorTransferir, contaCorrente, bancoDados.consultarChavePix(chavePix),
					false);

			if (status == true) {
				System.out.println("Transferencia realizada com sucesso...");
			} else {
				System.out.println("Não foi possivel realizar transferencia, tente novamente...");
			}

			opcao = -1;
			break;
		case 5:
			System.out.println("---Saldo---");
			System.out.println(contaBo.exibirSaldo(contaCorrente, contaCorrente.getCliente()));

			opcao = -1;
			break;
		default:
			System.out.println("Opção inválida, tente novamente!!!");
			opcao = -1;
			break;
		}

	}

	public void subMenuTransacoesContaPoupanca(ContaPoupanca contaPoupanca) {

		boolean status = false;
		double deposito = 0.0;
		double saque = 0.0;
		double valorTransferir = 0.0;
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
			contaBo.depositar(contaPoupanca, deposito);
			System.out.println("Deposito realizado com sucesso!!!");
			opcao = -1;
			break;
		case 2:
			System.out.println("---Saque---");
			System.out.println("Insira um valor para o depósito: ");

			saque = sc.nextDouble();
			status = contaBo.saque(contaPoupanca, saque);

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
			//System.out.println("Selecione a conta que deseja enviar o valor: ");
			System.out.println("Informe o CPF da conta de destino: ");
			cpfCliente = sc.nextLine();
			while (clienteBo.validacaoCpf(cpfCliente) == false) {
				System.out.println("Insira um valor válido para cpf: ");
				cpfCliente = sc.nextLine();
			}
						
			opcao = selecionarConta(bancoDados.buscarCliente(cpfCliente));

			switch (opcao) {

			case 0:
				System.out.println("Acesso negado, tente novamente!!!");
				opcao = -1;
				break;
			case 1:
				// conta poupanca ----> conta poupanca
				status = contaBo.transferencia(valorTransferir, bancoDados.acessarContaPoupanca(bancoDados.buscarCliente(cpfCliente)),
						bancoDados.getContaPoupanca().get(opcao), false);
				if (status == true) {
					System.out.println("Transferencia realizada com sucesso...");

				} else {
					System.out.println("Não foi possivel realizar transferencia, tente novamente...");
				}
				opcao = -1;
				break;
			case 2:
				// conta poupanca ----> conta poupanca
				status = contaBo.transferencia(valorTransferir, bancoDados.acessarContaPoupanca(bancoDados.buscarCliente(cpfCliente)),
						bancoDados.getContaCorrente().get(opcao), true);
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
			status = contaBo.transferencia(valorTransferir, contaPoupanca, bancoDados.consultarChavePix(chavePix),
					false);

			if (status == true) {
				System.out.println("Transferencia realizada com sucesso...");
			} else {
				System.out.println("Não foi possivel realizar transferencia, tente novamente...");
			}

			opcao = -1;
			break;
		case 5:
			System.out.println("---Saldo---");
			System.out.println(contaBo.exibirSaldo(contaPoupanca, contaPoupanca.getCliente()));

			break;
		default:
			System.out.println("Opção inválida, tente novamente!!!");
			opcao = -1;
			break;
		}

	}

	public void cadastrarChavePix(Cliente cliente) {
		boolean status = false;

		ContaCorrente cc = bancoDados.acessarContaCorrente(cliente);
		status = pixBo.validaPix(cc.getPix());
		
		if (!status) {
			System.out.println("---Cadastrar Chave Pix---");
			System.out.println("Cliente selecionado: " + cliente.getNome());
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
				conteudoChavePix = cliente.getCpf();
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

			bancoDados.cadastrarPix(pixBo.cadastrarPix(tipoChavePix, conteudoChavePix));
			this.pixBo.addPixConta(bancoDados.acessarContaCorrente(cliente),
					bancoDados.getPix().get(bancoDados.getPix().size() - 1));
			if (bancoDados.getPix().get(bancoDados.getPix().size() - 1).isAtivado() == true) {
				System.out.println("Sua chave pix está ativa.");
				System.out.println("Tipo de chave selecionada: "
						+ bancoDados.getPix().get(bancoDados.getPix().size() - 1).getTipoChave());
				System.out.println("Chave cadastrada: "
						+ bancoDados.getPix().get(bancoDados.getPix().size() - 1).getConteudoChave());
			}
		} else {
			System.out.println("Chave Pix já cadastrada!!!");
		}
	}

	public int selecionarConta(Cliente cliente) {
		String opcaoConta;
		int retorno;
		System.out.println("---Transações---");
		System.out.println("Selecione a opcao de conta: ");
		
		for (ContaCorrente obj : bancoDados.getContaCorrente()) {
			if (obj.getCliente().equals(cliente)) {
				System.out.println("CC - Conta Corrente");
			}
		}

		for (ContaPoupanca obj : bancoDados.getContaPoupanca()) {
			if (obj.getCliente().equals(cliente)) {
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
	}

	public void cobraTaxaManutencao(ContaCorrente contaCorrente) {
		contaCorrenteBo.descontarTaxa(contaCorrente);
	}

	public void cobraTaxaRendimento(ContaPoupanca contaPoupanca) {
		contaPoupancaBo.acrescentarRendimento(contaPoupanca);
	}

}
