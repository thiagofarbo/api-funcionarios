package br.com.webwork.funcionario.exception;

import static java.util.Collections.singletonList;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.webwork.funcionario.domain.exception.BadGatewayCustom;
import br.com.webwork.funcionario.domain.exception.BadRequestCustom;
import br.com.webwork.funcionario.domain.exception.NoContentCustom;
import br.com.webwork.funcionario.domain.exception.NotFoundCustom;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 public static final String MENSAGEM_GLOBAL_202 = "Atualizado";
	 public static final String MENSAGEM_GLOBAL_204 = "Nenhum conteúdo.";	
     public static final String MENSAGEM_GLOBAL_400 = "Requisição inválida.";
     public static final String MENSAGEM_GLOBAL_404 = "Recurso não encontrado.";
     public static final String MENSAGEM_GLOBAL_500 = "Erro interno do sistema.";

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Funcionário não existe.")
	@ExceptionHandler(NotFoundCustom.class)
	public @ResponseBody ErrorInfo handleTicketDoesNotExistException(HttpServletRequest request,NotFoundCustom exception) {
		return builderErrorInfo(request, exception, singletonList(exception.getMessage()));
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Erro interno no sistema.")
	@ExceptionHandler({ BadRequestCustom.class })
	public @ResponseBody ErrorInfo handlerBeenCheckedException(HttpServletRequest request, BadRequestCustom exception) {
		return builderErrorInfo(request, exception, singletonList(exception.getMessage()));
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Não existe funcionário para essa requisição.")
	@ExceptionHandler({ NoContentCustom.class })
	public @ResponseBody ErrorInfo handlerNotFoundException(HttpServletRequest request, NoContentCustom exception) {
		return builderErrorInfo(request, exception, singletonList(exception.getMessage()));
	}
	
	@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Erro ao processar a requisição")
	@ExceptionHandler({ BadGatewayCustom.class })
	public @ResponseBody ErrorInfo handlerNotFoundException(HttpServletRequest request, BadGatewayCustom exception) {
		return builderErrorInfo(request, exception, singletonList(exception.getMessage()));
	}

	private ErrorInfo builderErrorInfo(HttpServletRequest request, Exception exception, List<String> messages) {
		return ErrorInfo.builder().
				timestamp(LocalDateTime.now())
				.messages(messages)
				.exception(exception.getClass().getSimpleName())
				.path(request.getRequestURI())
				.build();
	}
}