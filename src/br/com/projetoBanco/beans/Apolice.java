package br.com.projetoBanco.beans;

import java.util.ArrayList;

public class Apolice {

	private String id;
	private double valorApolice;
	private String descricaoCondicoes;
	private ArrayList<Seguro> seguro = new ArrayList<>();

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

	public ArrayList<Seguro> getSeguro() {
		return seguro;
	}

	public void setSeguro(ArrayList<Seguro> seguro) {
		this.seguro = seguro;
	}

}
