package br.com.webwork.funcionario.domain.exception;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;

import br.com.webwork.funcionario.exception.ExceptionCustom;

public class BadGatewayCustom extends ExceptionCustom {

	private static final long serialVersionUID = 4711372295703897008L;

	public BadGatewayCustom(final String message) {
		super(BAD_GATEWAY, message);
	}

}
