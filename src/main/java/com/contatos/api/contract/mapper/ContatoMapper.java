package com.contatos.api.contract.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.contatos.api.contract.model.ContatoResponse;
import com.contatos.api.contract.model.NovoContatoRequest;
import com.contatos.api.impl.model.Contato;

public class ContatoMapper {

	public static Contato contractToImpl(NovoContatoRequest novoContatoRequest) {
		return Contato.builder().nome(novoContatoRequest.getNome())
				.numero(NumeroMapper.contractToImpl(novoContatoRequest.getNumero())).build();
	}

	public static ContatoResponse implToContract(Contato contato) {
		ContatoResponse contatoResponse = new ContatoResponse();
		contatoResponse.setNome(contato.getNome());
		contatoResponse.setNumero(NumeroMapper.implToContract(contato.getNumero()));
		return contatoResponse;
	}

	public static List<ContatoResponse> contatoResponses(List<Contato> contatos) {
		return contatos.stream().map(contato -> implToContract(contato)).collect(Collectors.toList());
	}
}
