package br.com.projetoBanco.beans;

public enum BandeiraCartao {

	VISA(0), MASTER (1), ELO (2);
	
	private int id;

	private BandeiraCartao(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	
}
