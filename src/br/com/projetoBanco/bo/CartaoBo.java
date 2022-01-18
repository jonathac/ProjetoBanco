package br.com.projetoBanco.bo;

import java.util.Random;
import java.util.UUID;

import br.com.projetoBanco.beans.BandeiraCartao;
import br.com.projetoBanco.beans.Cartao;
import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.TipoCartao;

public class CartaoBo {

	public Cartao cadastrarCartao(Conta conta, String senha, BandeiraCartao bandeiraCartao, TipoCartao tipoCartao) {
		Cartao cartao = new Cartao();

		Random r = new Random();
		String numeroCartao = "";
		for (int i = 0; numeroCartao.length() <= 16; i = r.nextInt(9)) {
			numeroCartao = numeroCartao.concat(String.valueOf(i));
		}

		cartao.setConta(conta);
		cartao.setId(UUID.randomUUID().toString());
		cartao.setNumero(numeroCartao);
		cartao.setSenha(senha);
		cartao.setBandeiraCartao(bandeiraCartao);
		cartao.setAtivo(true);

		if (tipoCartao.equals(TipoCartao.CREDITO)) {
			cartao.setCredito(true);
		} else if (tipoCartao.equals(TipoCartao.DEBITO)) {
			cartao.setDebito(true);
		}

		return cartao;
	}

	public boolean validarSenha(String senha) {
		boolean retorno = false;

		if (senha.length() == 4 && senha.matches("[0-9]*")) {
			retorno = true;
		}

		return retorno;
	}

	public BandeiraCartao selecaoBandeira(int bandeiraCartao) {
		BandeiraCartao bandeira;

		if (bandeiraCartao == 0) {
			bandeira = BandeiraCartao.VISA;
		} else if (bandeiraCartao == 1) {
			bandeira = BandeiraCartao.MASTER;
		} else {
			bandeira = BandeiraCartao.ELO;
		}

		return bandeira;

	}

	public TipoCartao selecaoTipo(int selecaoTipo) {
		TipoCartao retorno;

		if (selecaoTipo == 0) {
			retorno = TipoCartao.CREDITO;
		} else {
			retorno = TipoCartao.DEBITO;
		}

		return retorno;
	}

	public void bloquearCredito(Cartao cartao) {
		cartao.setCreditoBloqueado(true);
	}

	public void liberarCredito(Cartao cartao) {
		cartao.setCreditoBloqueado(false);
	}

	public void bloquearDebito(Cartao cartao) {
		cartao.setDebitoBloqueado(true);
	}

	public void liberarDebito(Cartao cartao) {
		cartao.setDebitoBloqueado(false);
	}

	public void ativarCartao(Cartao cartao, boolean status) {
		cartao.setAtivo(status);
	}

	public void adicionarCredito(Cartao cartao) {
		cartao.setCredito(true);
	}

	public void adicionarDebito(Cartao cartao) {
		cartao.setDebito(true);
	}
}
