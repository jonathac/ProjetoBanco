package br.com.projetoBanco.bo;

import java.util.UUID;

import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.Conta;

public class ContaBo {

	// cadastrar metodos

	public double exibirSaldo() {
		Conta conta = new Conta();
		return conta.getSaldo();

		// igual sprint1
	}

	public void depositar(Cliente cliente, double valorDepositar) {
		Conta conta = new Conta();
		conta.setSaldo(conta.getSaldo() + valorDepositar);
		
	}

	public void saque(double valorSaque) {
		Conta conta = new Conta();
		conta.setSaldo(conta.getSaldo() - valorSaque);
	}

	public void transferencia(double valorTransferir) {
		Conta conta = new Conta();
		conta.setSaldo(conta.getSaldo() - valorTransferir);
	}

	public boolean validarSaldo(double valor) {
		boolean retorno = false;
		Conta conta = new Conta();

		if (conta.getSaldo() > valor) {
			retorno = true;
		} else {
			retorno = false;
		}

		return retorno;
	}

	public void cadastrarConta(Cliente cliente) {

		ContaCorrenteBo contaCorrentebo = new ContaCorrenteBo();

		contaCorrentebo.cadastrarContaCorrente(cliente);
	}

}