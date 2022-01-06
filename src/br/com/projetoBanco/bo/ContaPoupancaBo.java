package br.com.projetoBanco.bo;

import java.util.UUID;

import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.ContaCorrente;
import br.com.projetoBanco.beans.ContaPoupanca;

public class ContaPoupancaBo {

	/*
	 * public double exibirSaldo () {
	 * 
	 * ContaPoupanca contaPoupanca = new ContaPoupanca(); return
	 * contaPoupanca.getSaldo(); }
	 */

	public void acrescentarRendimento() {
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		double saldoFinal = contaPoupanca.getSaldo() * (1 + (contaPoupanca.getTaxaRendimento() / 100));

		contaPoupanca.setSaldo(saldoFinal);

	}

	public void cadastrarContaPoupanca(Cliente cliente) {

		ContaPoupanca contaPoupanca = new ContaPoupanca();

		contaPoupanca.setIdConta(UUID.randomUUID().toString());
		contaPoupanca.setNumeroConta(UUID.randomUUID().toString());
		contaPoupanca.setSaldo(0.0);

	}

}
