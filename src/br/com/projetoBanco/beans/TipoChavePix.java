package br.com.projetoBanco.beans;

public enum TipoChavePix {

	CPF(0), EMAIL(1), TELEFONE(2), ALEATORIO(3);
	
	private int id;
	
	private TipoChavePix(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

}
