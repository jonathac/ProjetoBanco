package br.com.projetoBanco.bo;

import java.util.UUID;

import br.com.projetoBanco.beans.Cliente;
import br.com.projetoBanco.beans.Conta;
import br.com.projetoBanco.beans.Endereco;
import br.com.projetoBanco.beans.TipoCliente;

public class ClienteBo {

	// cadastrar metodos

	public Cliente cadastrarDados(String cpf, String nome, String dataNascimento, 
			Endereco endereco) {

		Cliente cliente = new Cliente();

		cliente.setIdCliente(UUID.randomUUID().toString());
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTipoCliente(TipoCliente.COMUM);// como passar ?
		cliente.setEndereco(endereco);
		cliente.setDataNascimento(dataNascimento);

		return cliente;
	}

	public boolean validacaoCpf(String cpf) {

		boolean retorno = false;

		if (!cpf.matches("[0-9]*") || cpf.length() != 11) {
			retorno = false;
		} else {
			retorno = true;
		}

		return retorno;
	}

	
	//nebuloso, vamos testar
	public String tipoCliente (Conta conta, Cliente cliente) {
				
		if (conta.getSaldo() <= 5000) {
			cliente.setTipoCliente(TipoCliente.COMUM);
		} else if (conta.getSaldo() > 5000 && conta.getSaldo() <= 14999) {
			cliente.setTipoCliente(TipoCliente.SUPER);
		} else {
			cliente.setTipoCliente(TipoCliente.PREMIUM);
		}
		
		return cliente.getTipoCliente().toString();
	}
}
