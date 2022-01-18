package br.com.projetoBanco.beans;

import java.util.Date;

public class Compras {

	private String idCompras;
	private String estabelecimentoCompra;
	private double valorCompra;
	private Cartao cartao;
	private CartaoCredito cartaoCredito;
	private CartaoDebito cartaoDebito;
	private Date dataCompra;
	
	
	public String getIdCompras() {
		return idCompras;
	}
	public void setIdCompras(String idCompras) {
		this.idCompras = idCompras;
	}
	public String getEstabelecimentoCompra() {
		return estabelecimentoCompra;
	}
	public void setEstabelecimentoCompra(String estabelecimentoCompra) {
		this.estabelecimentoCompra = estabelecimentoCompra;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}
	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	public CartaoDebito getCartaoDebito() {
		return cartaoDebito;
	}
	public void setCartaoDebito(CartaoDebito cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	
	
}
