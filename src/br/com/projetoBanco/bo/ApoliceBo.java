package br.com.projetoBanco.bo;

import java.util.Date;
import java.util.UUID;

import br.com.projetoBanco.beans.Apolice;
import br.com.projetoBanco.beans.CartaoCredito;
import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.Seguro;
import br.com.projetoBanco.beans.TipoSeguro;

public class ApoliceBo {

	
	Apolice apolice = new Apolice();

	public Apolice gerarApolice(Seguro seguro) {
		
		apolice.setId(UUID.randomUUID().toString());
		apolice.getSeguro().add(seguro);
		valorApolice();

		
		
		return apolice;
	}

	public Apolice adicionarSeguro(Seguro seguro) {
		
		apolice.getSeguro().add(seguro);
		valorApolice();

		return apolice;
	}

	public boolean seguroContratado(CartaoCredito cartaoCredito, TipoSeguro tipoSeguro) {
		apolice = cartaoCredito.getApolice();
		boolean retorno = false;
		try {
			for (Seguro obj : apolice.getSeguro()) {
				if (obj.getTipoSeguro().equals(tipoSeguro)) {
					retorno = true;
				}
			}
		} catch (Exception e) {

		}

		return retorno;
	}

	public void resgatarApolice (Conta conta, Seguro seguro) {
		
		double idenizacao = seguro.getBonusIdenizacao();
		double saldoConta = conta.getSaldo();
		
		conta.setSaldo(saldoConta+idenizacao);
		
	}
	
	public boolean validarDataResgateSeguro (Seguro seguro, Date dataTeste) {
		boolean retorno = false;
		Date hoje = new Date ();
		
		//if (seguro.getDataCarencia().compareTo(hoje)<0) {
		if (seguro.getDataCarencia().compareTo(dataTeste)<0) {//para testes a data sendo solicitada no terminal
			retorno = true;
		}
		
		return retorno;
	}
	
	public void valorApolice( ) {
		
		try {
			for (Seguro obj : apolice.getSeguro()) {
				double valorApolice = apolice.getValorApolice();
				double bonusIdenizacao = obj.getBonusIdenizacao();
				apolice.setValorApolice(valorApolice + bonusIdenizacao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
