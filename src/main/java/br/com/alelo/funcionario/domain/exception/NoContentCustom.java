package br.com.alelo.funcionario.domain.exception;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import br.com.alelo.funcionario.exception.ExceptionAlelo;

public class NoContentCustom extends ExceptionAlelo {

	private static final long serialVersionUID = 6344045555428479067L;

	public NoContentCustom(final String message) {
		super(NO_CONTENT, message);

	}

}
