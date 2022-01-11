package br.com.projetoBanco.beans;

public enum TipoCliente {

	COMUM(0), SUPER(1), PREMIUM(2);

private int id;
	
	private TipoCliente(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	}