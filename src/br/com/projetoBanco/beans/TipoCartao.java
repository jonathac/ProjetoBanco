package br.com.projetoBanco.beans;

public enum TipoCartao {
	CREDITO(0), DEBITO (1);
	
	private int id;

	private TipoCartao(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}
