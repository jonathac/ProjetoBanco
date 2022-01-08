package br.com.projetoBanco.beans;

public class ContaCorrente extends Conta {

	private double taxaManutencao;

	public double getTaxaManutencao() {
		return taxaManutencao;
	}

	public void setTaxaManutencao(double taxaManutencao) {
		this.taxaManutencao = taxaManutencao;
	}

	@Override
	public String toString() {
		return "ContaCorrente [taxaManutencao=" + taxaManutencao + ", getIdConta()=" + getIdConta()
				+ ", getNumeroConta()=" + getNumeroConta() + ", getSaldo()=" + getSaldo() + ", getCliente()="
				+ getCliente() + "]";
	}


	
}
