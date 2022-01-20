package br.com.projetoBanco.beans;

import java.util.Date;

public class Seguro {

	private String id;
	private String nome;
	private String[] regras;
	private Date dataAssinatura;
	private Date dataCarencia;
	private double valorSeguro;
	private TipoSeguro tipoSeguro;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String[] getRegras() {
		return regras;
	}

	public void setRegras(String[] regras) {
		this.regras = regras;
	}

	public Date getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(Date dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public Date getDataCarencia() {
		return dataCarencia;
	}

	public void setDataCarencia(Date dataCarencia) {
		this.dataCarencia = dataCarencia;
	}

	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

}
