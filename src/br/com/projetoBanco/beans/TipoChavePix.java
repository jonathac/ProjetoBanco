package br.com.projetoBanco.beans;

public enum TipoChavePix {

	CPF (0), EMAIL (1), TELEFONE (2), ALEATORIO (3);

	private final int valor;
	
	TipoChavePix(int valorOpcao){
		valor = valorOpcao;
	}
	public int getValor(){
		return valor;
	}

}
