package br.com.projetoBanco.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartaoCredito extends Cartao {

	private double limite;
	private double valorFatura;
	private Date vencimentoFatura;

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Date getVencimentoFatura() {
		return vencimentoFatura;
	}

	public void setVencimentoFatura(Date vencimentoFatura) {
		this.vencimentoFatura = vencimentoFatura;
	}

	public CartaoCredito() {
		this.limite = 0.0;
		this.valorFatura = 0.0;

		try {
			this.vencimentoFatura = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
		} catch (ParseException e) {
		}

	}

}
