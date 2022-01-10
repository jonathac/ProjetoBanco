package br.com.projetoBanco.bo;

import java.util.UUID;

import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.ContaCorrente;
import br.com.projetoBanco.beans.Pix;
import br.com.projetoBanco.beans.TipoChavePix;

public class PixBo {
	
	public Pix cadastrarPix(TipoChavePix tipoChavePix, String conteudoChavepix) {
		Pix pix = new Pix();
		pix.setIdPix(UUID.randomUUID().toString());
		pix.setTipoChave(tipoChavePix);
		pix.setConteudoChave(conteudoChavepix);
		pix.setAtivado(true);
				
		return pix;
	}

	public void addPixConta (ContaCorrente contaCorrente,Pix pix) {
		contaCorrente.setPix(pix);
	}
	
 	public boolean validarSaldo(double valor, Conta conta) {
		boolean retorno = false;
		
		if (conta.getSaldo() > valor) {
			retorno = true;
		} else {
			retorno = false;
		}

		return retorno;
	}
	
	
}
