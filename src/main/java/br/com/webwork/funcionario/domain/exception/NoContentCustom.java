package br.com.webwork.funcionario.domain.exception;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import br.com.webwork.funcionario.exception.ExceptionCustom;

public class NoContentCustom extends ExceptionCustom {

	private static final long serialVersionUID = 6344045555428479067L;

	public NoContentCustom(final String message) {
		super(NO_CONTENT, message);

	}

}
