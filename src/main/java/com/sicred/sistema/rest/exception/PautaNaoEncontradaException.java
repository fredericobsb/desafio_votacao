package com.sicred.sistema.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PautaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PautaNaoEncontradaException(String exception) {
        super(exception);
    }
}