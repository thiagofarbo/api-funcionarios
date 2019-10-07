package br.com.alelo.funcionario.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.funcionario.enums.StatusFuncionarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario implements Serializable{
	
		private static final long serialVersionUID = 7682266561520420887L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		private String nome;
		
		private String cargo;
		
		private String cpf;
		
		private BigDecimal salario;
		
		private LocalDate dataAdmissao;
		
		private LocalDate dataDemissao;
		
		private StatusFuncionarioEnum status;
}
