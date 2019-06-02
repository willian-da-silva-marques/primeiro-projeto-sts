package com.contatos.api.contract.model;

import lombok.Getter;

@Getter
public class NovoContatoRequest {

	private String nome;
	private NovoNumeroRequest numero;
	
}
