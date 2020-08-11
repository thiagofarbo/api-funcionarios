package br.com.webwork.funcionario.csv.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByPosition;

import br.com.webwork.funcionario.enums.StatusFuncionarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FuncionarioModel {
	
	@CsvBindByPosition(position = 0)
	private Long id;
	
	@CsvBindByPosition(position = 1)
	private String nome;
	
	@CsvBindByPosition(position = 2)
	private String cargo;
	
	@CsvBindByPosition(position = 3)
	private String cpf;
	
	@CsvBindByPosition(position = 4)
	private Double salario;
	
	@CsvBindByPosition(position = 5)
	private LocalDate dataAdmissao;
	
	@CsvBindByPosition(position = 6)
	private LocalDate dataDemissao;
	
	@CsvBindByPosition(position = 7)
	private StatusFuncionarioEnum status;
}