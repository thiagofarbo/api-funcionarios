
package br.com.webwork.funcionario.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import br.com.webwork.funcionario.domain.exception.BadRequestCustom;
import br.com.webwork.funcionario.domain.exception.NoContentCustom;
import br.com.webwork.funcionario.domain.exception.NotFoundCustom;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ExceptionsMessagesEnum {

	GLOBAL_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ExceptionCustom.class),
	GLOBAL_BAD_REQUEST(BAD_REQUEST.value(), "Erro interno no sistema.", BadRequestCustom.class),
	GLOBAL_NO_CONTENT(NO_CONTENT.value(), "Não existe conteudo para essa requisição.", NoContentCustom.class),
	GLOBAL_RESOURCE_NOT_FOUND(NOT_FOUND.value(), "Registro não encontrado", NotFoundCustom.class);

	private Integer code;

	@Setter
	private String message;

	ExceptionsMessagesEnum(int code, String message, Class<? extends ExceptionCustom> klass) {
		this.message = message;
		this.code = code;
	}

	public void raise() {

		if (BAD_REQUEST.value() == this.code) {

			throw new BadRequestCustom(this.message);

		} else if (NOT_FOUND.value() == this.code) {

			throw new NotFoundCustom(this.message);

		} else if (NO_CONTENT.value() == this.code) {
			
			throw new NoContentCustom(this.message);
		
		}else {
			throw new ExceptionCustom(INTERNAL_SERVER_ERROR, this.message);
		}
	}
}
