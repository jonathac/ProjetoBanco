package br.com.projetoBanco.bo;

import br.com.projetoBanco.beans.Endereco;

public class EnderecoBo {

	public Endereco cadastrarEndereco(String logradouro, String numero, String cep, String bairro, String cidade,
			String estado) {

		Endereco endereco = new Endereco();

		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setCep(cep);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);

		return endereco;
	}

	public void buscarEndereco(String cep) {

		Endereco endereco = new Endereco();

		// buscar
	}

	public boolean validarCep(String cep) {
		boolean retorno = false;

		if (!cep.matches("[0-9]*") || cep.length() != 8) {
			retorno = false;
		} else {
			retorno = true;
		}

		return retorno;
	}

}
