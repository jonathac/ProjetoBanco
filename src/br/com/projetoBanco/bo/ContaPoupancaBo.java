package br.com.projetoBanco.bo;

import java.util.UUID;

import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.ContaCorrente;
import br.com.projetoBanco.beans.ContaPoupanca;

public class ContaPoupancaBo {

	public void acrescentarRendimento() {
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		double saldoFinal = contaPoupanca.getSaldo() * (1 + (contaPoupanca.getTaxaRendimento() / 100));

		contaPoupanca.setSaldo(saldoFinal);
	}

	public ContaPoupanca cadastrarContaPoupanca(Cliente cliente) {

		ContaPoupanca contaPoupanca = new ContaPoupanca();

		contaPoupanca.setIdConta(UUID.randomUUID().toString());
		contaPoupanca.setNumeroConta(UUID.randomUUID().toString());
		contaPoupanca.setSaldo(0.0);
		contaPoupanca.setTaxaRendimento(0.3);
		contaPoupanca.setCliente(cliente);

		return contaPoupanca;
	}

}
