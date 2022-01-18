package br.com.projetoBanco.Utils;

import java.util.ArrayList;

import br.com.projetoBanco.beans.Cartao;
import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.Compras;
import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.ContaCorrente;
import br.com.projetoBanco.beans.ContaPoupanca;
import br.com.projetoBanco.beans.Endereco;
import br.com.projetoBanco.beans.Pix;

public class BancoDados {

	private ArrayList<Cliente> cliente = new ArrayList<>();
	private ArrayList<Endereco> endereco = new ArrayList<>();
	private ArrayList<ContaCorrente> contaCorrente = new ArrayList<>();
	private ArrayList<ContaPoupanca> contaPoupanca = new ArrayList<>();
	private ArrayList<Pix> pix = new ArrayList<>();
	private ArrayList<Cartao> cartao = new ArrayList<>();
	private ArrayList<Compras> compras = new ArrayList<>();

	public ArrayList<Cliente> getCliente() {
		return cliente;
	}

	public ArrayList<Endereco> getEndereco() {
		return endereco;
	}

	public ArrayList<ContaCorrente> getContaCorrente() {
		return contaCorrente;
	}

	public ArrayList<ContaPoupanca> getContaPoupanca() {
		return contaPoupanca;
	}

	public ArrayList<Pix> getPix() {
		return pix;
	}

	public ArrayList<Cartao> getCartao() {
		return cartao;
	}

	public void cadastroEndereco(Endereco endereco) {

		this.endereco.add(endereco);

	}

	public void cadastroCliente(Cliente cliente) {

		this.cliente.add(cliente);
	}

	public void cadastrarContaCorrente(ContaCorrente contaCorrente) {

		this.contaCorrente.add(contaCorrente);

	}

	public void cadastrarContaPoupanca(ContaPoupanca contaPoupanca) {

		this.contaPoupanca.add(contaPoupanca);

	}

	public void cadastrarPix(Pix pix) {

		this.pix.add(pix);

	}

	public ContaPoupanca acessarContaPoupanca(Cliente cliente) {
		ContaPoupanca retorno = null;

		for (ContaPoupanca obj : contaPoupanca) {
			if (obj.getCliente().equals(cliente)) {
				retorno = obj;
			}
		}

		return retorno;
	}

	public Conta consultarChavePix(String chavePix) {

		Conta conta = null;

		for (ContaCorrente obj : contaCorrente) {
			if (obj.getPix().getConteudoChave().equals(chavePix)) {
				conta = obj;
			}
		}

		return conta;
	}

	public ContaCorrente acessarContaCorrente(Cliente cliente) {
		ContaCorrente retorno = null;

		for (ContaCorrente obj : contaCorrente) {
			if (obj.getCliente().equals(cliente)) {
				retorno = obj;
			}
		}

		return retorno;
	}

	public Cliente buscarCliente(String cpf) {
		Cliente cliente = null;

		for (Cliente obj : this.cliente) {
			if (obj.getCpf().equals(cpf)) {
				cliente = obj;
			}
		}

		return cliente;
	}

	public void cadastrarCartao(Cartao cartao) {
		this.cartao.add(cartao);
	}

	public Cartao consultarCartao(Conta conta) {
		Cartao cartao = new Cartao();

		for (Cartao obj : this.cartao) {
			if (obj.getConta().equals(conta)) {
				cartao = obj;
			}
		}

		return cartao;
	}

	
	public Cartao consultarCartaoCpf (String cpfCliente) {
		Cartao cartao = null;
		
		for (Cartao obj : this.cartao) {
			if (obj.getConta().getCliente().getCpf().equals(cpfCliente)) {
				cartao = obj;
			}
		}
		
		return cartao;
	}
	
	public boolean verificaCartao(Conta conta) {
		boolean retorno = false;

		for (Cartao obj : this.cartao) {
			if (obj.getConta().equals(conta)) {
				retorno = true;
			}

		}

		return retorno;
	}

	public void cadastrarCompras(Compras compras) {
		this.compras.add(compras);
	}

}
