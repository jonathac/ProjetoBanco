package br.com.projetoBanco.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import br.com.projetoBanco.beans.Seguro;
import br.com.projetoBanco.beans.TipoSeguro;

public class SeguroBo {

	Seguro seguro = new Seguro ();
	
	public Seguro cadastrarSeguro (String nomeSeguro, String[] regras, TipoSeguro tipoSeguro) {
		
		Date hoje = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(hoje);
		cal.add(Calendar.DAY_OF_MONTH, 15);
		
		seguro.setId(UUID.randomUUID().toString());
		seguro.setNome(nomeSeguro);
		seguro.setRegras(regras);
		seguro.setDataAssinatura(hoje);
		seguro.setDataCarencia(cal.getTime());
		seguro.setTipoSeguro(tipoSeguro);
		
		
		return seguro;
	}
	
	public String[] regasSeguro (TipoSeguro tipoSeguro) {
		String[] regras;
		
		if (tipoSeguro.equals(TipoSeguro.MORTE)) {
			String[] vetorMorte = new String[4];
			
			vetorMorte[0] = "i. Indenização por despesas médico-hospitalares, ou por perda parcial ou total do funcionamento dos membros ou órgãos";
			vetorMorte[1] = "ii. Reembolso de custos em diagnóstico de doenças graves, como infarto, acidente vascular cerebral e câncer;";
			vetorMorte[2] = "iii. Assistência funeral, para você e a sua família.";
			vetorMorte[3] = "iv. O valor do seguro individual é de R$36,00 ao ano.";
			
			regras = vetorMorte;
		} 
		else if (tipoSeguro.equals(TipoSeguro.INVALIDEZ)) {
			String[] vetorInvalidez = new String[3];
			
			vetorInvalidez[0] = "i. Invalidez parcial: ocorre quando há uma perda parcial das funções. Por exemplo, uma pessoa que sofre um acidente e perda a visão em um só dos olhos.";
			vetorInvalidez[1] = "ii. Invalidez total: ocorre quando há uma perda total das funções. Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um acidente e perde a visão em ambos os olhos.";
			vetorInvalidez[2] = "iii. O valor do seguro individual é de R$26,00 ao ano.";
			
			regras = vetorInvalidez;
		}
		else {
			String[] vetorDesemprego = new String[4];
			
			vetorDesemprego[0] = "i. Necessário trabalhar com registro CLT, com o tempo mínimo de estabilidade de 12 meses.";
			vetorDesemprego[1] = "ii. Válido apenas para desligamento involuntários e sem justa causa.";
			vetorDesemprego[2] = "iii. Não é valido em caso de demissão em massa (10% ou mais de demissões simultâneas) ou falência/encerramento das atividades.";
			vetorDesemprego[3] = "iv. O valor do seguro individual é de R$16,00 ao ano.";
			
			regras = vetorDesemprego;
		}
		
		
		return regras;
	}
}
