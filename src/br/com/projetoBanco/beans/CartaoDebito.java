package br.com.projetoBanco.beans;

public class CartaoDebito extends Cartao {

	private double limiteTransacao = 1000;

	public double getLimiteTransacao() {
		return limiteTransacao;
	}

	public void setLimiteTransacao(double limiteTransacao) {
		this.limiteTransacao = limiteTransacao;
	}
	
	
	
}
