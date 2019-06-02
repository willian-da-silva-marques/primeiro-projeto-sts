package com.contatos.api.contract.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoResponse {

	private String nome;
	private NumeroResponse numero;
}
