package br.com.projetoBanco.bo;

import java.util.UUID;

import br.com.projetoBanco.beans.Pix;
import br.com.projetoBanco.beans.TipoChavePix;

public class PixBo {

	public void cadastrarPix(TipoChavePix tipoChavePix, String conteudoChavepix) {
		
		Pix pix = new Pix();
		
		pix.setIdPix(UUID.randomUUID().toString());
		pix.setTipoChave(tipoChavePix);
		pix.setConteudoChave(conteudoChavepix);
		pix.setAtivado(true);
		
	}
	
}
