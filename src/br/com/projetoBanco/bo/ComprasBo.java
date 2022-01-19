package br.com.projetoBanco.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import br.com.projetoBanco.beans.CartaoCredito;
import br.com.projetoBanco.beans.CartaoDebito;
import br.com.projetoBanco.beans.Compras;

public class ComprasBo {
	
	
	public Compras cadastrarComprasCredito (String estabelecimentoCompra, double valorCompra, CartaoCredito cartaoCredito) {
		Compras compras = new Compras ();
		Date dataCompra = new Date();
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		
		compras.setIdCompras(UUID.randomUUID().toString());
		compras.setEstabelecimentoCompra(estabelecimentoCompra);
		compras.setValorCompra(valorCompra);
		compras.setCartaoCredito(cartaoCredito);
		try {
			compras.setDataCompra(data.parse(data.format(dataCompra)));
		} catch (ParseException e) {
			
		}
		return compras;
	}
	
	public Compras cadastrarComprasDebito (String estabelecimentoCompra, double valorCompra, CartaoDebito cartaoDebito) {
		Compras compras = new Compras ();
		Date dataCompra = new Date();
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		
		compras.setIdCompras(UUID.randomUUID().toString());
		compras.setEstabelecimentoCompra(estabelecimentoCompra);
		compras.setValorCompra(valorCompra);
		compras.setCartaoDebito(cartaoDebito);
		try {
			compras.setDataCompra(data.parse(data.format(dataCompra)));
		} catch (ParseException e) {
			
		}
		return compras;
	}
}
