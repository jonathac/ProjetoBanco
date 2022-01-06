package br.com.projetoBanco.beans;

public enum TipoCliente {

	COMUM(0), SUPER(1), PREMIUM(2);
	
private final int valor;
	
	TipoCliente(int valorOpcao){
		valor = valorOpcao;
	}
	public int getValor(){
		return valor;
	}
	
}