package br.com.webwork.funcionario.service;

import static br.com.webwork.funcionario.exception.ExceptionAlelo.checkThrow;
import static br.com.webwork.funcionario.exception.ExceptionsMessagesAleloEnum.GLOBAL_BAD_REQUEST;
import static br.com.webwork.funcionario.exception.ExceptionsMessagesAleloEnum.GLOBAL_NO_CONTENT;
import static br.com.webwork.funcionario.exception.ExceptionsMessagesAleloEnum.GLOBAL_RESOURCE_NOT_FOUND;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.google.gson.Gson;

import br.com.webwork.funcionario.domain.Funcionario;
import br.com.webwork.funcionario.enums.StatusFuncionarioEnum;
import br.com.webwork.funcionario.repository.FuncionarioRepository;
import br.com.webwork.funcionario.request.FuncionarioRequest;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdate;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdateParcial;
import br.com.webwork.funcionario.response.FuncionarioResponse;
import br.com.webwork.funcionario.rest.FuncionarioAPIRestRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioAPIRestRepository funcionarioAPIRestRepository;

	@Transactional
	public FuncionarioResponse salvarFuncionario(final FuncionarioRequest funcionarioRequest) {
		
		Funcionario funcionario = Funcionario.builder()
				.nome(funcionarioRequest.getNome())
				.cargo(funcionarioRequest.getCargo())
				.cpf(funcionarioRequest.getCpf())
				.salario(funcionarioRequest.getSalario())
				.dataAdmissao(funcionarioRequest.getDataAdmissao())
				.dataDemissao(funcionarioRequest.getDataDemissao())
				.status(funcionarioRequest.getStatus())
				.build();
		
		Funcionario funcionarioResponse = this.funcionarioRepository.save(funcionario);
		
		return builderFuncionarioResponse(funcionarioResponse);
		
	}

	public FuncionarioResponse consultarFuncionario(final Long id) {
		
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		
		checkThrow(!funcionario.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);
		
		return builderFuncionarioResponse(funcionario.get());
		
	}

	public Page<Funcionario> listarFuncionarios(final int page, final int size) {
		
		Page<Funcionario> funcionarios = this.funcionarioRepository.findAll(PageRequest.of(page, size));

		checkThrow(CollectionUtils.isEmpty(funcionarios.getContent()), GLOBAL_NO_CONTENT);
		
		return funcionarios;
	}
	
	@Transactional
	public ResponseEntity<Void> getExcel() throws Exception {
		
		FuncionarioResponse excelFuncionario = this.funcionarioAPIRestRepository.getExcelFuncionario();
			Gson gson = new Gson();
			String json = gson.toJson(excelFuncionario);
			
			JsonNode jsonTree = new ObjectMapper().readTree(json);
			
			Builder csvSchemaBuilder = CsvSchema.builder();
			JsonNode firstObject = jsonTree.elements().next();
			firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
			CsvSchema csvSchema = csvSchemaBuilder.build();
			
			CsvMapper csvMapper = new CsvMapper();
			csvMapper.writerFor(FuncionarioResponse.class)
			  .with(csvSchema)
			  .writeValue(new File("src/main/resources/orderLines.csv"), jsonTree);
			
		return null;
	}

	@Transactional
	public FuncionarioResponse atualizarFuncionario(final FuncionarioRequestUpdate funcionarioRequestUpdate, final Long id) {
		
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		
		checkThrow(!funcionario.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);

		funcionario.get().setNome(funcionarioRequestUpdate.getNome());
		funcionario.get().setCargo(funcionarioRequestUpdate.getCargo());
		funcionario.get().setCpf(funcionarioRequestUpdate.getCpf());
		funcionario.get().setSalario(funcionarioRequestUpdate.getSalario());
		funcionario.get().setDataAdmissao(funcionarioRequestUpdate.getDataAdmissao());
		funcionario.get().setDataDemissao(funcionarioRequestUpdate.getDataDemissao());
		funcionario.get().setStatus(funcionarioRequestUpdate.getStatus());
		
		Funcionario funcionarioResponse = this.funcionarioRepository.save(funcionario.get());
		
		return builderFuncionarioResponse(funcionarioResponse);
		
	}

	@Transactional
	public String excluirFuncionario(final Long id) {
		
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		
		checkThrow(!funcionario.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);
		
		this.funcionarioRepository.deleteById(id);
		
		return "Funcionário com o id "+id.toString() + " foi deletado com sucesso.";	
	}
	
	@Transactional
	public FuncionarioResponse atualizarFuncionarioParcial(final FuncionarioRequestUpdateParcial funcionarioRequestUpdate, final Long id) {
		
		checkThrow(funcionarioRequestUpdate.getStatus() == null, GLOBAL_BAD_REQUEST);
		
		StatusFuncionarioEnum status = mapStatusFuncionario(funcionarioRequestUpdate.getStatus());
		
		checkThrow(status == null, GLOBAL_BAD_REQUEST);
		
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		
		checkThrow(!funcionario.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);

		funcionario.get().setNome(funcionarioRequestUpdate.getNome());
		funcionario.get().setCargo(funcionarioRequestUpdate.getCargo());
		funcionario.get().setCpf(funcionarioRequestUpdate.getCpf());
		funcionario.get().setSalario(funcionarioRequestUpdate.getSalario());
		funcionario.get().setDataAdmissao(funcionarioRequestUpdate.getDataAdmissao());
		funcionario.get().setDataDemissao(funcionarioRequestUpdate.getDataDemissao());
		funcionario.get().setStatus(funcionarioRequestUpdate.getStatus());
		
		Funcionario funcionarioResponse = this.funcionarioRepository.save(funcionario.get());
		
		return builderFuncionarioResponse(funcionarioResponse);
				
	}
	
	private FuncionarioResponse builderFuncionarioResponse(Funcionario funcionario) {
		
		return FuncionarioResponse.builder()
				.id(funcionario.getId())
				.nome(funcionario.getNome())
				.cargo(funcionario.getCargo())
				.cpf(funcionario.getCpf())
				.salario(funcionario.getSalario())
				.dataAdmissao(funcionario.getDataAdmissao())
				.dataDemissao(funcionario.getDataDemissao())
				.status(funcionario.getStatus())
				.build();
		
	}
	
	private static StatusFuncionarioEnum mapStatusFuncionario(StatusFuncionarioEnum statusRequest) {
	    for (StatusFuncionarioEnum status : StatusFuncionarioEnum.values()) {
	        if (status.name().equals(statusRequest.name())) {
	        	return status;
	        }
	    }
	    return null;
	}
}