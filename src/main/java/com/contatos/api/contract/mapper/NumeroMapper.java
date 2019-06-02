package com.contatos.api.contract.mapper;

import com.contatos.api.contract.model.NovoNumeroRequest;
import com.contatos.api.contract.model.NumeroResponse;
import com.contatos.api.impl.model.Numero;

public class NumeroMapper {

	public static Numero contractToImpl(NovoNumeroRequest novoNumeroRequest) {
		return Numero.builder().ddi(novoNumeroRequest.getDdi()).ddd(novoNumeroRequest.getDdd())
				.descricao(novoNumeroRequest.getDescricao()).build();
	}

	public static NumeroResponse implToContract(Numero numero) {
		NumeroResponse numeroResponse = new NumeroResponse();
		numeroResponse.setDdi(numero.getDdi());
		numeroResponse.setDdd(numero.getDdd());
		numeroResponse.setDescricao(numero.getDescricao());
		return numeroResponse;
	}
}
