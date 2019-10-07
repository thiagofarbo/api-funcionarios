package br.com.alelo.funcionario.domain.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import br.com.alelo.funcionario.exception.ExceptionAlelo;

public class BadRequestCustom extends ExceptionAlelo {

	private static final long serialVersionUID = 4711372295703897008L;

	public BadRequestCustom(final String message) {
		super(BAD_REQUEST, message);
	}

}
