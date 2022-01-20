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
			
			vetorMorte[0] = "i. Indeniza��o por despesas m�dico-hospitalares, ou por perda parcial ou total do funcionamento dos membros ou �rg�os";
			vetorMorte[1] = "ii. Reembolso de custos em diagn�stico de doen�as graves, como infarto, acidente vascular cerebral e c�ncer;";
			vetorMorte[2] = "iii. Assist�ncia funeral, para voc� e a sua fam�lia.";
			vetorMorte[3] = "iv. O valor do seguro individual � de R$36,00 ao ano.";
			
			regras = vetorMorte;
		} 
		else if (tipoSeguro.equals(TipoSeguro.INVALIDEZ)) {
			String[] vetorInvalidez = new String[3];
			
			vetorInvalidez[0] = "i. Invalidez parcial: ocorre quando h� uma perda parcial das fun��es. Por exemplo, uma pessoa que sofre um acidente e perda a vis�o em um s� dos olhos.";
			vetorInvalidez[1] = "ii. Invalidez total: ocorre quando h� uma perda total das fun��es. Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um acidente e perde a vis�o em ambos os olhos.";
			vetorInvalidez[2] = "iii. O valor do seguro individual � de R$26,00 ao ano.";
			
			regras = vetorInvalidez;
		}
		else {
			String[] vetorDesemprego = new String[4];
			
			vetorDesemprego[0] = "i. Necess�rio trabalhar com registro CLT, com o tempo m�nimo de estabilidade de 12 meses.";
			vetorDesemprego[1] = "ii. V�lido apenas para desligamento involunt�rios e sem justa causa.";
			vetorDesemprego[2] = "iii. N�o � valido em caso de demiss�o em massa (10% ou mais de demiss�es simult�neas) ou fal�ncia/encerramento das atividades.";
			vetorDesemprego[3] = "iv. O valor do seguro individual � de R$16,00 ao ano.";
			
			regras = vetorDesemprego;
		}
		
		
		return regras;
	}
}
