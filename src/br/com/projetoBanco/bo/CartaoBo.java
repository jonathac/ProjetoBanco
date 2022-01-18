package br.com.projetoBanco.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import br.com.projetoBanco.beans.BandeiraCartao;
import br.com.projetoBanco.beans.Cartao;
import br.com.projetoBanco.beans.CartaoCredito;
import br.com.projetoBanco.beans.CartaoDebito;
import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.TipoCartao;

public class CartaoBo {

	public Cartao cadastrarCartao(Conta conta, String senha, BandeiraCartao bandeiraCartao, TipoCartao tipoCartao) {

		Cartao cartao = new Cartao();
		CartaoCredito cartaoCredito = new CartaoCredito();
		CartaoDebito cartaoDebito = new CartaoDebito();

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
		cartao.setCartaoCredito(cartaoCredito);
		cartao.setCartaoDebito(cartaoDebito);
		cartao.getCartaoCredito().setLimite(limiteCartaoCreditoTotal(cartao));
		cartao.getCartaoCredito().setLimiteDisponivel(cartao.getCartaoCredito().getLimite());
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
		if (!status) {
		cartao.setCreditoBloqueado(status);
		cartao.setDebitoBloqueado(status);
		}
	}

	public void adicionarCredito(Cartao cartao, String vencimentoCartao) {
		cartao.setCredito(true);
		dataVencimento(cartao, vencimentoCartao);
	}

	public void adicionarDebito(Cartao cartao) {
		cartao.setDebito(true);
	}

	public void dataVencimento(Cartao cartao, String vencimento) {

		Date hoje = new Date();
		String dataFormatada;
		Date dataVencimento = null;
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat ano = new SimpleDateFormat("yyyy");
		SimpleDateFormat mes = new SimpleDateFormat("MM");
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

		vencimento = vencimento.concat("/").concat(mes.format(hoje));
		vencimento = vencimento.concat("/").concat(ano.format(hoje));

		try {
			cal.setTime(data.parse(vencimento));
			cal.add(Calendar.MONTH, 1);
			dataFormatada = cal.getTime().toString();
			dataVencimento = data.parse(dataFormatada);
		} catch (ParseException e) {

		}

		cartao.getCartaoCredito().setVencimentoFatura(dataVencimento);

	}

	public void alterarSenha(Cartao cartao, String novaSenha) {
		cartao.setSenha(novaSenha);
	}

	
	public double limiteCartaoCreditoTotal(Cartao cartao) {

		double limite = 0.0;

		limite = cartao.getConta().getSaldo() / 2;

		return limite;
	}

	public void alterarLimiteCartaoCredito(Cartao cartao) {
		double limiteTotal = cartao.getCartaoCredito().getLimite();
		double limiteDisponivel = limiteCartaoCreditoDisponivel(cartao);
		double limiteUtilizado = limiteTotal-limiteDisponivel;
		double novoLimiteTotal = limiteCartaoCreditoTotal(cartao);
		double novoLimiteDisponivel = novoLimiteTotal - limiteUtilizado;
		
		cartao.getCartaoCredito().setLimiteDisponivel(novoLimiteDisponivel);
		cartao.getCartaoCredito().setLimite(novoLimiteTotal);
	
	}

	public double limiteCartaoCreditoDisponivel(Cartao cartao) {
		double limite = cartao.getCartaoCredito().getLimiteDisponivel();
		return limite;
	}

	public void atualizarLimiteCartaoDisponivel(CartaoCredito cartaoCredito, Double valorCompra) {

		double limite = cartaoCredito.getLimiteDisponivel();
		cartaoCredito.setLimiteDisponivel(limite - valorCompra);
	}

	public boolean autorizarCompra(CartaoCredito cartaoCredito, Double valorCompra) {
		boolean retorno = false;

		if (cartaoCredito.getLimiteDisponivel() >= valorCompra && !cartaoCredito.isCreditoBloqueado() && cartaoCredito.isAtivo()) {
			retorno = true;
			atualizarLimiteCartaoDisponivel(cartaoCredito, valorCompra);
		}

		return retorno;
	}

}
