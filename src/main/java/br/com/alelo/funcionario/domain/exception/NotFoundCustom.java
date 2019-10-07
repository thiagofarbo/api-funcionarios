package br.com.alelo.funcionario.domain.exception;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.alelo.funcionario.exception.ExceptionAlelo;

public class NotFoundCustom extends ExceptionAlelo {
	
	private static final long serialVersionUID = -7331739769883331451L;

	public NotFoundCustom (final String message) {
		super(NOT_FOUND, message);
		
	}	
	
}
