package br.com.webwork.funcionario.request;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.webwork.funcionario.enums.StatusFuncionarioEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "FuncionarioRequest", description = "Request para funcionário")
public class FuncionarioRequest implements Serializable{

	private static final long serialVersionUID = 4641788372072003805L;
	
	@ApiModelProperty(value = "Id Funcionário", notes = "Identidicação do Funcionário", required = true, example = "12", position = 1)
	private Long id;
	
	@ApiModelProperty(value = "Nome Funcionário", notes = "Nome do Funcionário", required = true, example = "João Pereira", position = 2)
	private String nome;
	
	@ApiModelProperty(value = "Cargo Funcionário", notes = "Cargo do Funcionário", required = true, example = "Gerente de Operações", position = 3)
	private String cargo;
	
	@ApiModelProperty(value = "CPF Funcionário", notes = "CPF do Funcionário", required = true, example = "20199876655", position = 4)
	private String cpf;
	
	@ApiModelProperty(value = "Salário Funcionário", notes = "Salário do Funcionário", required = true, example = "20.000", position = 5)
	private Double salario;
	
	@ApiModelProperty(value = "Data admissão Funcionário", notes = "Data de admissão do Funcionário", required = true, example = "30-09-2019", position = 6)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataAdmissao;
	
	@ApiModelProperty(value = "Data demissão Funcionário", notes = "Data de demissão do Funcionário",  example = "30-09-2020", position = 7)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataDemissao;
	
	@ApiModelProperty(value = "Status Funcionário", notes = "Status do Funcionário", required = true, example = "Ativo", position = 8)
	private StatusFuncionarioEnum status; 
	
}
