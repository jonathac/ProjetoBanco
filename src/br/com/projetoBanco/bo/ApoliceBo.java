package br.com.projetoBanco.bo;

import java.util.UUID;

import br.com.projetoBanco.beans.Apolice;
import br.com.projetoBanco.beans.CartaoCredito;
import br.com.projetoBanco.beans.Seguro;
import br.com.projetoBanco.beans.TipoSeguro;

public class ApoliceBo {

	Apolice apolice = new Apolice();

	public void gerarApolice(Seguro seguro) {

		apolice.setId(UUID.randomUUID().toString());
		apolice.getSeguro().add(seguro);

	}

	public void adicionarSeguro(Seguro seguro) {
		apolice.getSeguro().add(seguro);
	}

	public boolean seguroContratado(CartaoCredito cartaoCredito, TipoSeguro tipoSeguro) {
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

	public void valorApolice() {
		for (Seguro obj : apolice.getSeguro()) {
			double valorApolice = apolice.getValorApolice();
			double valorSeguro = obj.getValorSeguro();
			apolice.setValorApolice(valorApolice + valorSeguro);
		}
	}

}
