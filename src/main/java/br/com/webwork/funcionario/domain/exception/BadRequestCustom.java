package br.com.webwork.funcionario.domain.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import br.com.webwork.funcionario.exception.ExceptionCustom;

public class BadRequestCustom extends ExceptionCustom {

	private static final long serialVersionUID = 4711372295703897008L;

	public BadRequestCustom(final String message) {
		super(BAD_REQUEST, message);
	}

}
