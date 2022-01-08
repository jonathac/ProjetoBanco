package br.com.projetoBanco.bo;

import java.util.UUID;

import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.ContaCorrente;

public class ContaCorrenteBo {

	// cadastrar metodos

	public void descontarTaxa() {
		ContaCorrente contaCorrente = new ContaCorrente();
		double saldoFinal = contaCorrente.getSaldo() * (1 - (contaCorrente.getTaxaManutencao() / 100));

		contaCorrente.setSaldo(saldoFinal);
	}

	public ContaCorrente cadastrarContaCorrente(Cliente cliente) {

		ContaCorrente contaCorrente = new ContaCorrente();

		contaCorrente.setIdConta(UUID.randomUUID().toString());
		contaCorrente.setNumeroConta(UUID.randomUUID().toString());
		contaCorrente.setSaldo(0.0);
		contaCorrente.setTaxaManutencao(0.45);
		contaCorrente.setCliente(cliente);

		return contaCorrente;
	}

	public double depositar(double saldo, double valorDepositar) {
		
		saldo += valorDepositar;
		
		return saldo;
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

}
