package com.contatos.api.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ListaDeContatosVaziaException extends RuntimeException {

	private static final long serialVersionUID = -458877770753353L;

	public ListaDeContatosVaziaException(String message) {
		super(message);
	}
}
