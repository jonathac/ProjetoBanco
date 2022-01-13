package br.com.projetoBanco.beans;

public class Pix {

	private String idPix;
	private TipoChavePix tipoChave;
	private double valor;
	private String data;
	private String conteudoChave;
	private boolean isAtivado = false;
	private Conta conta;

	public String getIdPix() {
		return idPix;
	}

	public void setIdPix(String idPix) {
		this.idPix = idPix;
	}

	public TipoChavePix getTipoChave() {
		return tipoChave;
	}

	public void setTipoChave(TipoChavePix tipoChave) {
		this.tipoChave = tipoChave;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getConteudoChave() {
		return conteudoChave;
	}

	public void setConteudoChave(String conteudoChave) {
		this.conteudoChave = conteudoChave;
	}

	public boolean isAtivado() {
		return isAtivado;
	}

	public void setAtivado(boolean isAtivado) {
		this.isAtivado = isAtivado;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
