package br.com.projetoBanco.Utils;

import java.util.ArrayList;

import br.com.projetoBanco.beans.Cliente;
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
	private ArrayList<Pix> pix = new ArrayList<Pix>();

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

	public ContaPoupanca acessarContaPoupanca(String clienteTransacao) {
		ContaPoupanca retorno = null;

		for (ContaPoupanca obj : getContaPoupanca()) {
			if (obj.getCliente().getNome().equals(clienteTransacao)) {
				retorno = obj;
			}
		}

		return retorno;
	}
	
	public Conta consultarChavePix(String chavePix) {

		Conta conta = null;

		for (ContaCorrente contaCorrente : getContaCorrente()) {
			if (contaCorrente.getPix().getConteudoChave().equals(chavePix)) {
				conta = contaCorrente;
			}
		}

		return conta;
	}

	public ContaCorrente acessarContaCorrente(String clienteTransacao) {
		ContaCorrente retorno = null;

		for (ContaCorrente obj : getContaCorrente()) {
			if (obj.getCliente().getNome().equals(clienteTransacao)) {
				retorno = obj;
			}
		}

		return retorno;
	}

	
}
