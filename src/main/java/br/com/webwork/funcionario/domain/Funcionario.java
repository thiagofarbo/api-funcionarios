package br.com.webwork.funcionario.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import br.com.webwork.funcionario.enums.StatusFuncionarioEnum;
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
//@EqualsAndHashCode(of = { "cpf" })
public class Funcionario implements Serializable{
	
		private static final long serialVersionUID = 7682266561520420887L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
		
		@NotBlank
		private String nome;
		
		@NotBlank
		private String cargo;
		
		@NotBlank
		private String cpf;
		
		@NotBlank
		private Double salario;
		
		@NotBlank
		private LocalDate dataAdmissao;
		
		private LocalDate dataDemissao;
		
		private StatusFuncionarioEnum status;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Funcionario other = (Funcionario) obj;
			if (cpf == null) {
				if (other.cpf != null)
					return false;
			} else if (!cpf.equals(other.cpf))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
}
