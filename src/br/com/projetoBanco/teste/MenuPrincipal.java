package br.com.projetoBanco.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import br.com.projetoBanco.Utils.BancoDados;
import br.com.projetoBanco.beans.BandeiraCartao;
import br.com.projetoBanco.beans.Cartao;
import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.ContaCorrente;
import br.com.projetoBanco.beans.ContaPoupanca;
import br.com.projetoBanco.beans.TipoCartao;
import br.com.projetoBanco.beans.TipoChavePix;
import br.com.projetoBanco.bo.CartaoBo;
import br.com.projetoBanco.bo.ClienteBo;
import br.com.projetoBanco.bo.ComprasBo;
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
	CartaoBo cartao = new CartaoBo();
	BandeiraCartao bandeira;
	ComprasBo compras = new ComprasBo();

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
			System.out.println("2- Cadastrar Conta");
			System.out.println("3- Acessar Conta");
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
				acessarConta();
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
				bancoDados.getContaPoupanca()
						.add(contaPoupancaBo.cadastrarContaPoupanca((bancoDados.buscarCliente(cpfCliente))));
				System.out.println("Conta cadastrada com sucesso!!!");
			}

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

	public void acessarConta() {

		System.out.println("---Acessar Conta---");
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
			System.out.println("Acesso negado, tente novamente!!!");
			opcao = -1;
			break;
		case 1:
			// acessar conta corrente
			subMenuTransacoesContaCorrente(bancoDados.acessarContaCorrente(bancoDados.buscarCliente(cpfCliente)));
			opcao = 3;
			break;
		case 2:
			// acessar conta poupanca
			subMenuTransacoesContaPoupanca(bancoDados.acessarContaPoupanca(bancoDados.buscarCliente(cpfCliente)));
			opcao = 3;
			break;
		default:
			System.out.println("Acesso negado, tente novamente!!!");
			opcao = -1;
			break;

		}

	}

	public void subMenuTransacoesContaCorrente(ContaCorrente contaCorrente) {

		while (opcao != -1) {

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
			System.out.println("6- Cartões");
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
				// opcao = 3;
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

				opcao = 3;
				break;
			case 3:
				System.out.println("---Transferência---");
				System.out.println("Insira um valor para a transferência: ");
				valorTransferir = sc.nextDouble();
				// System.out.println("Selecione a conta que deseja enviar o valor: ");
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
					// opcao = -1;
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
					// opcao = -1;
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
					// opcao = -1;
					break;

				default:
					System.out.println("Acesso negado, tente novamente!!!");
					// opcao = -1;
					break;

				}
				// opcao = 3;
				break;

			case 4:
				while (opcao != -1) {
					System.out.println("---Pix---");
					System.out.println("1- Cadastrar chave Pix");
					System.out.println("2- Realizar Pix");
					System.out.println("0- Sair");
					int opcao = sc.nextInt();

					switch (opcao) {
					case 0:
						opcao = -1;
						break;
					case 1:
						cadastrarChavePix(contaCorrente.getCliente());
						// opcao = -1;
						break;
					case 2:

						if (pixBo.validaPix(contaCorrente.getPix())) {

							System.out.println("Insira a chave pix da conta a receber: ");
							chavePix = sc.next();
							if (pixBo.validaPix(bancoDados.consultarChavePix(chavePix).getPix())) {
								System.out.println("Insira o valor da trasnferencia: ");
								valorTransferir = sc.nextDouble();
								status = contaBo.transferencia(valorTransferir, contaCorrente,
										bancoDados.consultarChavePix(chavePix), false);

								if (status == true) {
									System.out.println("Transferencia realizada com sucesso...");
								} else {
									System.out.println("Não foi possivel realizar transferencia, tente novamente...");
								}
							} else {
								System.out.println("Chave pix não encontrada.");
							}

						} else {
							System.out.println("Chave Pix não ativada.");
							System.out.println("Realize o ativacao da chave pix e tente novamente");
						}

						// opcao = -1;
						break;
					default:
						System.out.println("Opcao invalida, tente novamente");
						break;
					// opcao = -1;
					}
				}

				opcao = 0;
				break;
			case 5:
				System.out.println("---Saldo---");
				System.out.println(contaBo.exibirSaldo(contaCorrente, contaCorrente.getCliente()));

				// opcao = 3;
				break;
			case 6:

				while (opcao != -1) {

					int dia;
					String vencimentoCartao;
					String senha = "";
					int bandeiraCartao;
					int tipoCartao = 0;

					System.out.println("---Cartões---");
					System.out.println("1- Solicitar Cartão");// ok
					System.out.println("2- Acessar cartão de crédito");
					System.out.println("3- Acessar cartão de débito");
					System.out.println("4- Informar perda ou roubo");// ok
					System.out.println("5- Alterar senha");// ok
					System.out.println("6- Fazer compras");
					System.out.println("Default - imprimir cartões para teste");

					System.out.println("0- Sair");
					opcao = sc.nextInt();

					switch (opcao) {
					case 1:

						if (!bancoDados.verificaCartao(contaCorrente)) {

							System.out.println("Solicitação de Cartão");

							do {
								System.out.println("Escolha um dos valores abaixo: ");
								System.out.println("Qual cartao deseja solicitar: ");
								System.out.println("0- Crédito");
								System.out.println("1- Débito");
								tipoCartao = sc.nextInt();
							} while (tipoCartao < 0 && tipoCartao > 1);

							do {
								System.out.println("Escolha um dos valores abaixo: ");
								System.out.println("Qual a bandeira você deseja para seu cartao: ");
								System.out.println("0- VISA");
								System.out.println("1- MASTER");
								System.out.println("2- ELO");
								bandeiraCartao = sc.nextInt();
							} while (bandeiraCartao < 0 && bandeiraCartao > 2);

							do {
								System.out.println("Cadastre senha numerica de 4 digitos");
								senha = sc.next();
							} while (!cartao.validarSenha(senha));

							if (cartao.selecaoTipo(tipoCartao).equals(TipoCartao.CREDITO)) {
								System.out.println("informe uma dia para vencimento do seu cartao: ");
								do {
									System.out.println("Escolher um dia entre 1 a 30.");
									dia = sc.nextInt();
								} while (dia < 1 && dia > 30);

								vencimentoCartao = String.valueOf(dia);
							} else {
								vencimentoCartao = String.valueOf(0);
							}

							bancoDados.cadastrarCartao(cartao.cadastrarCartao(contaCorrente, senha,
									cartao.selecaoBandeira(bandeiraCartao), cartao.selecaoTipo(tipoCartao)));

							cartao.dataVencimento(bancoDados.consultarCartao(contaCorrente), vencimentoCartao);
							cartao.ativarCartao(bancoDados.consultarCartao(contaCorrente), true);
							System.out.println("Cartão solicitado com sucesso!");
							System.out.println("Deseja habilitar função " + cartao.selecaoTipo(tipoCartao)
									+ " do seu cartão? s/n");
							String valida = sc.next().toLowerCase();
							while (!valida.equals("s") && !valida.equals("n")) {
								System.out.println("Deseja ativar seu cartão? s/n");
								valida = sc.next().toLowerCase();
							}
							if (valida.equals("s")) {
								if (cartao.selecaoTipo(tipoCartao).equals(TipoCartao.CREDITO)) {
									cartao.liberarCredito(bancoDados.consultarCartao(contaCorrente));
									System.out.println("Ativação concluida com sucesso.");
								} else if (cartao.selecaoTipo(tipoCartao).equals(TipoCartao.DEBITO)) {
									cartao.liberarDebito(bancoDados.consultarCartao(contaCorrente));
									System.out.println("Ativação concluida com sucesso.");
								}

							}

						} else if (bancoDados.consultarCartao(contaCorrente).isCredito() == true
								&& bancoDados.consultarCartao(contaCorrente).isDebito() == false) {
							System.out.println("Voce já tem um cartão de crédito ativo.");
							System.out.println("Deseja solicitar a função Débito? s/n");
							String valida = sc.next().toLowerCase();
							while (!valida.equals("s") && !valida.equals("n")) {
								System.out.println("Deseja ativar seu cartão? s/n");
								valida = sc.next().toLowerCase();
							}
							if (valida.equals("s")) {
								System.out.println("Informe a senha do cartão atual: ");
								senha = sc.next();
								if (bancoDados.consultarCartao(contaCorrente).getSenha().equals(senha)) {
									cartao.adicionarDebito(bancoDados.consultarCartao(contaCorrente));
									System.out.println("Função débito habilitada com sucesso!");
								} else {
									System.out.println("Senha incorreta, tente novamente");
								}
							} else {
								System.out.println("Não foi solicitado nenhum cartão");
							}

						}

						else if (bancoDados.consultarCartao(contaCorrente).isDebito() == true
								&& bancoDados.consultarCartao(contaCorrente).isCredito() == false) {
							System.out.println("Voce já tem um cartão de débito ativo.");
							System.out.println("Deseja solicitar a função Crédito? s/n");
							String valida = sc.next().toLowerCase();
							while (!valida.equals("s") && !valida.equals("n")) {
								System.out.println("Deseja ativar seu cartão? s/n");
								valida = sc.next().toLowerCase();
							}
							if (valida.equals("s")) {
								System.out.println("Informe a senha do cartão atual: ");
								senha = sc.next();
								if (bancoDados.consultarCartao(contaCorrente).getSenha().equals(senha)) {

									System.out.println("informe uma dia para vencimento do seu cartao: ");
									do {
										System.out.println("Escolher um dia entre 1 a 30.");
										dia = sc.nextInt();
									} while (dia < 1 && dia > 30);

									vencimentoCartao = String.valueOf(dia);

									cartao.adicionarCredito(bancoDados.consultarCartao(contaCorrente),
											vencimentoCartao);
									System.out.println("Função Crédito habilitada com sucesso!");
								} else {
									System.out.println("Senha incorreta, tente novamente");
								}
							} else {
								System.out.println("Não foi solicitado nenhum cartão");
							}
						}

						else if (bancoDados.consultarCartao(contaCorrente).isAtivo() == false) {
							System.out.println("Seu cartão já possui as funções de crédito e débito, e não está ativo");
							System.out.println("Deseja ativar seu cartão? s/n");
							String valida = sc.next().toLowerCase();
							while (!valida.equals("s") && !valida.equals("n")) {
								System.out.println("Deseja ativar seu cartão? s/n");
								valida = sc.next().toLowerCase();
							}
							if (valida.equals("s")) {
								cartao.ativarCartao(bancoDados.consultarCartao(contaCorrente), true);
								System.out.println("Ativação concluida com sucesso.");
							}
						}

						else {
							System.out.println("Seu cartão já possui as funções de crédito e débito ativas.");

						}

						break;
					case 2:
						Cartao cartao = bancoDados.consultarCartao(contaCorrente);
						while (opcao != -1) {

							if (bancoDados.verificaCartao(contaCorrente) && cartao.isCredito()) {

								for (int i = 0; i < 3 && !senha.equals(cartao.getSenha()); i++) {
									if (i != 0) {
										System.out.println("Senha incorreta, tente novamente " + (i + 1) + "/3");
									}
									System.out.println("Informe a senha do seu cartão de crédito");
									senha = sc.next();
								}
								if (senha.equals(cartao.getSenha())) {

									System.out.println("Cartão de crédito");
									System.out.println("1- Exibir limite");// ok
									System.out.println("2- Exibir fatura");
									System.out.println("3- Pagar fatura");
									System.out.println("4- Alterar data de vencimento");// ok
									System.out.println("5- Habilitar cartão");// ok
									System.out.println("6- Bloquear cartão");// ok
									System.out.println("7- Alterar limite do cartão");// ok
									System.out.println("0- Sair");
									opcao = sc.nextInt();

									switch (opcao) {
									case 0:
										opcao = -1;
										break;
									case 1:
										System.out.println("Limite");
										System.out.println("O limite total do seu cartão é de: R$ "
												+ this.cartao.limiteCartaoCreditoTotal(cartao));
										System.out.println("O seu limite disponivel para compras é de: R$ "
												+ this.cartao.limiteCartaoCreditoDisponivel(cartao));
										// opcao = -1;
										break;
									case 2:
										System.out.println("Fatura");

										// opcao = -1;
										break;
									case 3:
										System.out.println("Pagar Fatura");

										// opcao = -1;
										break;
									case 4:
										System.out.println("Alterar data de vencimento");
										System.out.println("informe uma dia para vencimento do seu cartao: ");
										do {
											System.out.println("Escolher um dia entre 1 a 30.");
											dia = sc.nextInt();
										} while (dia < 1 && dia > 30);

										vencimentoCartao = String.valueOf(dia);
										this.cartao.dataVencimento(cartao, vencimentoCartao);
										
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										String data = sdf.format(cartao.getCartaoCredito().getVencimentoFatura());
										
										System.out.println("Sua data de vencimento foi alterada.");
										System.out.println("Sua próxima fatura esta previsto para "
												+ data);

										// opcao = -1;
										break;
									case 5:
										System.out.println("Habilitar cartão");
										if (cartao.isCreditoBloqueado()) {
											this.cartao.liberarCredito(cartao);
											System.out.println("Função crédito habilitada com sucesso");
										} else {
											System.out.println("A função crédito do seu cartão já está habilitada.");
										}

										// opcao = -1;
										break;
									case 6:
										System.out.println("Bloquear cartão");
										if (!cartao.isCreditoBloqueado()) {
											this.cartao.bloquearCredito(cartao);
											System.out.println("Função crédito bloqueada com sucesso");
										} else {
											System.out.println("A função crédito do seu cartão já está bloqueada.");
										}

										// opcao = -1;
										break;
									case 7:
										System.out.println("Alterar limite do cartão");
										if (cartao.getCartaoCredito().getLimite() < this.cartao
												.limiteCartaoCreditoTotal(cartao)) {
											System.out.println("Você um limite pré-aprovado de: R$ "
													+ this.cartao.limiteCartaoCreditoTotal(cartao));

											System.out.println("Deseja alterar o limite do seu cartão? s/n");
											String valida = sc.next().toLowerCase();
											while (!valida.equals("s") && !valida.equals("n")) {
												System.out.println("Deseja ativar seu cartão? s/n");
												valida = sc.next().toLowerCase();
											}

											this.cartao.alterarLimiteCartaoCredito(cartao);
											System.out.println("Seu limite foi alterado com sucesso");
											System.out.println(
													"Seu novo limite é: R$ " + cartao.getCartaoCredito().getLimite());
										}
										break;
									default:
										System.out.println("Opção inválida, tente novamente!!!");
										opcao = -1;
										break;

									}
								} else {
									System.out.println("Seu cartão não possui a função Crédito.");
								}
							}
						}
						opcao = 0;
						break;
					case 3:

						break;
					case 4:
						System.out.println("Perda ou Roubo");
						cartao = bancoDados.consultarCartao(contaCorrente);

						if (bancoDados.verificaCartao(contaCorrente)) {
							for (int i = 0; i < 3 && !senha.equals(cartao.getSenha()); i++) {
								if (i != 0) {
									System.out.println("Senha incorreta, tente novamente " + (i + 1) + "/3");
								}
								System.out.println("Informe a senha do seu cartão de crédito");
								senha = sc.next();
							}
							if (senha.equals(cartao.getSenha())) {
								this.cartao.ativarCartao(cartao, false);
								System.out.println("Cartão bloqueado com sucesso.");
							} else {
								System.out.println("Senha incorreta");
							}
						}
						// opcao = -1;
						break;
					case 5:
						System.out.println("Alterar senha");
						cartao = contaCorrente.getCartao();

						if (bancoDados.verificaCartao(contaCorrente)) {
							senha = "";
							for (int i = 0; i < 3 && !senha.equals(cartao.getSenha()); i++) {
								if (i != 0) {
									System.out.println("Senha incorreta, tente novamente " + (i + 1) + "/3");
								}
								System.out.println("Informe a senha do seu cartão de crédito");
								senha = sc.next();
							}
							if (senha.equals(cartao.getSenha())) {

								do {
									System.out.println("Cadastre senha numerica de 4 digitos");
									senha = sc.next();
								} while (!this.cartao.validarSenha(senha));
								this.cartao.alterarSenha(cartao, senha);
								System.out.println("Senha alterada com sucesso");
							} else {
								System.out.println("Senha incorreta");
							}
						}
						break;
					case 6:
						while (opcao != -1) {
							String estabelecimentoCompra;
							double valorCompra;

							System.out.println("---COMPRAS---");
							System.out.println("Insira o nome do estabelecimento onde esta sendo realizado a compra: ");
							sc.nextLine();
							estabelecimentoCompra = sc.nextLine();
							System.out.println("Insira o valor total da compra: ");
							valorCompra = sc.nextDouble();
							System.out.println("Insira o número do cartão");
							System.out
									.println("Para efeitos de teste estamos pesquisando o numero pelo cpf do cliente");

							cpfCliente = sc.nextLine();
							while (clienteBo.validacaoCpf(cpfCliente) == false) {
								System.out.println("Insira um valor válido para cpf: ");
								cpfCliente = sc.nextLine();
							}

							try {

								bancoDados.consultarCartaoCpf(cpfCliente).equals(null);
								cartao = bancoDados.consultarCartaoCpf(cpfCliente);

							} catch (Exception e) {
								System.out.println("Cartão não encontrado, tente novamente");
								opcao = -1;
								break;
							}

							// solicitar opcao debito ou credito

							// compras no credito
							System.out.println("Informe a senha do seu cartão de crédito");
							senha = sc.next();
							if (senha.equals(cartao.getSenha())) {
								// if (cartao.isAtivo() && cartao.getCartaoCredito().isCreditoBloqueado() ==
								// false
								// && this.cartao.autorizarCompra(cartao.getCartaoCredito(), valorCompra)) {
								if (this.cartao.autorizarCompra(cartao.getCartaoCredito(), valorCompra)) {
									bancoDados.cadastrarCompras(compras.cadastrarCompras(estabelecimentoCompra,
											valorCompra, cartao.getCartaoCredito()));
									System.out.println("Compra autorizada");
								} else {
									System.out.println("Compra não autorizada");
								}
							} else {
								System.out.println("Senha incorreta");
								opcao = -1;
								break;
							}

							System.out.println("Deseja realizar mais alguma compra? s/n");
							String valida = sc.next().toLowerCase();
							while (!valida.equals("s") && !valida.equals("n")) {
								System.out.println("Deseja ativar seu cartão? s/n");
								valida = sc.next().toLowerCase();
							}
							if (valida.equals("n")) {
								opcao = -1;
							}

						}
						opcao = 0;
						break;
					default:

						for (Cartao obj : bancoDados.getCartao()) {
							System.out.println("Cliente: " + obj.getConta().getCliente().getNome());
							System.out.println("Número Cartao: " + obj.getNumero());
							System.out.println("Bandeira: " + obj.getBandeiraCartao());
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							String data = sdf.format(obj.getCartaoCredito().getVencimentoFatura());
							System.out.println("Vencimento cartao: " + data);
							
							System.out.println("");
							// corrigir parse da data, esta imprimindo sem formato
						}

						System.out.println("Opção inválida, tente novamente!!!");
						opcao = -1;
						break;
					}
				}

				opcao = 0;
				break;
			default:
				System.out.println("Opção inválida, tente novamente!!!");
				opcao = -1;
				break;
			}
		}

	}

	public void subMenuTransacoesContaPoupanca(ContaPoupanca contaPoupanca) {

		while (opcao != -1) {

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
			System.out.println("6- Cartão");
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
				// opcao = -1;
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

				// opcao = -1;
				break;
			case 3:
				System.out.println("---Transferência---");
				System.out.println("Insira um valor para a transferência: ");
				valorTransferir = sc.nextDouble();
				// System.out.println("Selecione a conta que deseja enviar o valor: ");
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
					// opcao = -1;
					break;
				case 1:
					// conta poupanca ----> conta poupanca
					status = contaBo.transferencia(valorTransferir,
							bancoDados.acessarContaPoupanca(bancoDados.buscarCliente(cpfCliente)),
							bancoDados.getContaPoupanca().get(opcao), false);
					if (status == true) {
						System.out.println("Transferencia realizada com sucesso...");

					} else {
						System.out.println("Não foi possivel realizar transferencia, tente novamente...");
					}
					// opcao = -1;
					break;
				case 2:
					// conta poupanca ----> conta poupanca
					status = contaBo.transferencia(valorTransferir,
							bancoDados.acessarContaPoupanca(bancoDados.buscarCliente(cpfCliente)),
							bancoDados.getContaCorrente().get(opcao), true);
					if (status == true) {
						System.out.println("Transferencia realizada com sucesso...");
						// taxa transacao corrente --- poupanca
					} else {
						System.out.println("Não foi possivel realizar transferencia, tente novamente...");
					}
					// opcao = -1;
					break;

				default:
					System.out.println("Acesso negado, tente novamente!!!");
					// opcao = -1;
					break;

				}
				// opcao = -1;
				break;
			case 4:

				while (opcao != -1) {
					System.out.println("---Pix---");
					System.out.println("1- Cadastrar chave Pix");
					System.out.println("2- Realizar Pix");
					System.out.println("0- Sair");
					int opcao = sc.nextInt();

					switch (opcao) {

					case 0:
						opcao = -1;
						break;
					case 1:
						cadastrarChavePix(contaPoupanca.getCliente());
						// opcao = -1;
						break;
					case 2:
						if (pixBo.validaPix(contaPoupanca.getPix())) {

							System.out.println("Insira a chave pix da conta a receber: ");
							chavePix = sc.next();
							if (pixBo.validaPix(bancoDados.consultarChavePix(chavePix).getPix())) {
								System.out.println("Insira o valor da trasnferencia: ");
								valorTransferir = sc.nextDouble();
								status = contaBo.transferencia(valorTransferir, contaPoupanca,
										bancoDados.consultarChavePix(chavePix), false);

								if (status == true) {
									System.out.println("Transferencia realizada com sucesso...");
								} else {
									System.out.println("Não foi possivel realizar transferencia, tente novamente...");
								}
							} else {
								System.out.println("Chave pix não encontrada.");
							}

						} else {
							System.out.println("Chave Pix não ativada.");
							System.out.println("Realize o ativacao da chave pix e tente novamente");
						}

						// opcao = -1;
						break;

					default:
						System.out.println("Opcao invalida, tente novamente");
						opcao = -1;
						break;
					}
					opcao = -1;
				}
				break;
			case 5:
				System.out.println("---Saldo---");
				System.out.println(contaBo.exibirSaldo(contaPoupanca, contaPoupanca.getCliente()));

				break;

			case 6:
				// CONSTRUIR
				break;
			default:
				System.out.println("Opção inválida, tente novamente!!!");
				opcao = -1;
				break;
			}
			opcao = -1;
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
		System.out.println("---Acessar Conta---");
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
