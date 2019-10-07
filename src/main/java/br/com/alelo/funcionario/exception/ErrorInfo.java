package br.com.alelo.funcionario.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime timestamp;

	private Integer code;

	private String exception;

	private List<String> messages;

	private String path;

}
