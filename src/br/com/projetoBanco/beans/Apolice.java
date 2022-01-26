package br.com.projetoBanco.beans;

import java.util.ArrayList;
import java.util.List;

public class Apolice {

	private String id = "";
	private double valorApolice = 0.0;
	private String descricaoCondicoes = "";
	private List<Seguro> seguro = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getValorApolice() {
		return valorApolice;
	}

	public void setValorApolice(double valorApolice) {
		this.valorApolice = valorApolice;
	}

	public String getDescricaoCondicoes() {
		return descricaoCondicoes;
	}

	public void setDescricaoCondicoes(String descricaoCondicoes) {
		this.descricaoCondicoes = descricaoCondicoes;
	}

	public List<Seguro> getSeguro() {
		return seguro;
	}

	public void setSeguro(List<Seguro> seguro) {
		this.seguro = seguro;
	}

	@Override
	public String toString() {
		return "Apolice [id=" + id + ", valorApolice=" + valorApolice + ", descricaoCondicoes=" + descricaoCondicoes
				+ ", seguro=" + seguro + "]";
	}

	
	
}
