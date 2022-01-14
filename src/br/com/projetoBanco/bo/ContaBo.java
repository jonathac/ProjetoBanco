package br.com.projetoBanco.bo;

import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.TipoCliente;

public class ContaBo {

	private Conta conta;

	// cadastrar metodos

	public String exibirSaldo(Conta conta, Cliente cliente) {

		TipoCliente tipoCliente;
		if (conta.getSaldo() <= 5000) {
			tipoCliente = TipoCliente.COMUM;
		} else if (conta.getSaldo() > 5000 && conta.getSaldo() <= 14999) {
			tipoCliente = TipoCliente.SUPER;
		} else {
			tipoCliente = TipoCliente.PREMIUM;
		}

		cliente.setTipoCliente(tipoCliente);

		return "Cliente: " + conta.getCliente().getNome() + "\nCPF: " + conta.getCliente().getCpf() + "\nSaldo: R$ "
				+ conta.getSaldo() + "\nTipo de conta: " + cliente.getTipoCliente();
	}

	// funcao ok
	public void depositar(Conta conta, double valorDepositar) {

		conta.setSaldo(conta.getSaldo() + valorDepositar);
	}

	// funcao ok
	public boolean saque(Conta conta, double valorSaque) {
		boolean retorno = false;

		if (validarSaldo(valorSaque, conta) == true) {
			conta.setSaldo(conta.getSaldo() - valorSaque);
			retorno = true;
		}

		return retorno;
	}

	// funcao ok
	public boolean transferencia(double valorTransferir, Conta contaEnviar, Conta contaReceber, boolean taxa) {
		boolean retorno = false;

		if (taxa == true) {
			if (contaEnviar.getSaldo() > (valorTransferir + 5.6)) {
				contaEnviar.setSaldo(contaEnviar.getSaldo() - valorTransferir);
				contaReceber.setSaldo(valorTransferir + contaReceber.getSaldo());
				taxaTransferencia(contaEnviar);
				retorno = true;
			}
		} else if (validarSaldo(valorTransferir, contaEnviar) == true) {
			contaEnviar.setSaldo(contaEnviar.getSaldo() - valorTransferir);
			contaReceber.setSaldo(valorTransferir + contaReceber.getSaldo());
			retorno = true;
		}

		return retorno;
	}

	// função ok
	public boolean validarSaldo(double valor, Conta conta) {
		boolean retorno = false;

		if (conta.getSaldo() > valor) {
			retorno = true;
		} else {
			retorno = false;
		}

		return retorno;
	}

	// função ok
	public void cadastrarConta(Cliente cliente) {

		ContaCorrenteBo contaCorrentebo = new ContaCorrenteBo();

		contaCorrentebo.cadastrarContaCorrente(cliente);
	}

	public void taxaTransferencia(Conta contaEnviar) {
		contaEnviar.setSaldo(contaEnviar.getSaldo() - 5.6);
	}


}