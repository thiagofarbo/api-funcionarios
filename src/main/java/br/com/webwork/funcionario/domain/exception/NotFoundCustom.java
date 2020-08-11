package br.com.webwork.funcionario.domain.exception;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.webwork.funcionario.exception.ExceptionCustom;

public class NotFoundCustom extends ExceptionCustom {
	
	private static final long serialVersionUID = -7331739769883331451L;

	public NotFoundCustom (final String message) {
		super(NOT_FOUND, message);
		
	}	
	
}
