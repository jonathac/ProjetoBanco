package br.com.projetoBanco.beans;

public class Cartao {

	private String id;
	private String numero;
	private String senha;
	private boolean ativo;
	private BandeiraCartao bandeiraCartao;
	private boolean credito;
	private boolean debito;
	private Conta conta;
	private boolean creditoBloqueado;
	private boolean debitoBloqueado;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public BandeiraCartao getBandeiraCartao() {
		return bandeiraCartao;
	}
	public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}
	public boolean isCredito() {
		return credito;
	}
	public void setCredito(boolean credito) {
		this.credito = credito;
	}
	public boolean isDebito() {
		return debito;
	}
	public void setDebito(boolean debito) {
		this.debito = debito;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public boolean isCreditoBloqueado() {
		return creditoBloqueado;
	}
	public void setCreditoBloqueado(boolean creditoBloqueado) {
		this.creditoBloqueado = creditoBloqueado;
	}
	public boolean isDebitoBloqueado() {
		return debitoBloqueado;
	}
	public void setDebitoBloqueado(boolean debitoBloqueado) {
		this.debitoBloqueado = debitoBloqueado;
	}
	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numero=" + numero + ", senha=" + senha + ", ativo=" + ativo + ", bandeiraCartao="
				+ bandeiraCartao + ", credito=" + credito + ", debito=" + debito + "]";
	}

	
	
	
	
}
