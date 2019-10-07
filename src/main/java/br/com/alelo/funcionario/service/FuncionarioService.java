package br.com.alelo.funcionario.service;

import static br.com.alelo.funcionario.exception.ExceptionAlelo.checkThrow;
import static br.com.alelo.funcionario.exception.ExceptionsMessagesAleloEnum.GLOBAL_BAD_REQUEST;
import static br.com.alelo.funcionario.exception.ExceptionsMessagesAleloEnum.GLOBAL_NO_CONTENT;
import static br.com.alelo.funcionario.exception.ExceptionsMessagesAleloEnum.GLOBAL_RESOURCE_NOT_FOUND;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.alelo.funcionario.domain.Funcionario;
import br.com.alelo.funcionario.enums.StatusFuncionarioEnum;
import br.com.alelo.funcionario.repository.FuncionarioRepository;
import br.com.alelo.funcionario.request.FuncionarioRequest;
import br.com.alelo.funcionario.request.FuncionarioRequestUpdate;
import br.com.alelo.funcionario.request.FuncionarioRequestUpdateParcial;
import br.com.alelo.funcionario.response.FuncionarioResponse;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Transactional
	public FuncionarioResponse salvarFuncionario(final FuncionarioRequest funcionarioRequest) {

		Funcionario funcionario = Funcionario.builder()
				.id(funcionarioRequest.getId())
				.nome(funcionarioRequest.getNome())
				.cargo(funcionarioRequest.getCargo())
				.cpf(funcionarioRequest.getCpf())
				.salario(funcionarioRequest.getSalario())
				.dataAdmissao(funcionarioRequest.getDataAdmissao())
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