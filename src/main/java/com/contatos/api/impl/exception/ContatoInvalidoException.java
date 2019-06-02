package com.contatos.api.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContatoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 9138529139640819910L;

	public ContatoInvalidoException(String message) {
		super(message);
	}
}
