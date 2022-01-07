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

		return contaCorrente;
	}

}
