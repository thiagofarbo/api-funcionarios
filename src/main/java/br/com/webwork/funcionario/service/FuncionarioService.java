package br.com.webwork.funcionario.service;

import static br.com.webwork.funcionario.exception.ExceptionCustom.checkThrow;
import static br.com.webwork.funcionario.exception.ExceptionsMessagesEnum.GLOBAL_BAD_REQUEST;
import static br.com.webwork.funcionario.exception.ExceptionsMessagesEnum.GLOBAL_NO_CONTENT;
import static br.com.webwork.funcionario.exception.ExceptionsMessagesEnum.GLOBAL_RESOURCE_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import br.com.webwork.funcionario.csv.model.FuncionarioModel;
import br.com.webwork.funcionario.domain.Funcionario;
import br.com.webwork.funcionario.enums.StatusFuncionarioEnum;
import br.com.webwork.funcionario.repository.FuncionarioRepository;
import br.com.webwork.funcionario.request.FuncionarioRequest;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdate;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdateParcial;
import br.com.webwork.funcionario.response.FuncionarioResponse;
import br.com.webwork.funcionario.util.Mapper;

@Service
public class FuncionarioService {
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Transactional
	public FuncionarioResponse salvarFuncionario(final FuncionarioRequest funcionarioRequest) {
		
		Funcionario funcionario = mapper.mapToModel(funcionarioRequest);
		
		Funcionario response = this.funcionarioRepository.save(funcionario);
		
		return mapper.mapToModelResponse(response);
	}

	public FuncionarioResponse consultarFuncionario(final Long id) {
		
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		
		checkThrow(!funcionario.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);
		
		return mapper.mapToModelResponse(funcionario.get());
	}

	public Page<Funcionario> listarFuncionarios(final int page, final int size) {
		
		Page<Funcionario> funcionarios = this.funcionarioRepository.findAll(PageRequest.of(page, size));

		checkThrow(CollectionUtils.isEmpty(funcionarios.getContent()), GLOBAL_NO_CONTENT);
		
		return funcionarios;
	}
	
	@Transactional
	public void generateCsv(HttpServletResponse response)  {
		
		try {
			
			StatefulBeanToCsv<FuncionarioModel> writer = new StatefulBeanToCsvBuilder<FuncionarioModel>(response.getWriter())
	                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
	                .withOrderedResults(false)
	                .build();	
			
		final List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
			
		final List<FuncionarioModel> models = mapper.listToFuncionarioModel(funcionarios);
		
		writer.write(models);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public FuncionarioResponse atualizarFuncionario(final FuncionarioRequestUpdate funcionarioRequestUpdate, final Long id) {
		
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		
		checkThrow(!funcionario.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);

		Funcionario response = mapper.mapToModelUpdate(funcionarioRequestUpdate);
		
		Funcionario funcionarioResponse = this.funcionarioRepository.save(response);
		
		return mapper.mapToModelResponse(funcionarioResponse);
		
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

		Funcionario response = mapper.mapToModelUpdateParcial(funcionarioRequestUpdate);
		
		Funcionario funcionarioResponse = this.funcionarioRepository.save(response);
		
		return mapper.mapToModelResponse(funcionarioResponse);
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