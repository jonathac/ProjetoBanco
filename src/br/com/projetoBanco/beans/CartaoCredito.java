package br.com.projetoBanco.beans;

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
	
	


}
