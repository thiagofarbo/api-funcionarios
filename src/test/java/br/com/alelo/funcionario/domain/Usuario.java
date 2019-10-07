package br.com.alelo.funcionario.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {
	
	private Long id;
	private Long userId;
	private String title;
	private Boolean completed;
	
	public Usuario getUsuario() {
		
		return Usuario.builder()
				.id(1L)
				.userId(2L)
				.title("delectus aut autem")
				.completed(Boolean.TRUE).build();
	}
}