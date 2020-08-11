package br.com.webwork.funcionario.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.webwork.funcionario.csv.model.FuncionarioModel;
import br.com.webwork.funcionario.domain.Funcionario;
import br.com.webwork.funcionario.request.FuncionarioRequest;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdate;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdateParcial;
import br.com.webwork.funcionario.response.FuncionarioResponse;

@Component
public class Mapper {
	
	public Funcionario mapToModel(final FuncionarioRequest request) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(request, Funcionario.class);
	}
	
	public FuncionarioResponse mapToModelResponse(final Funcionario funcionario) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(funcionario, FuncionarioResponse.class);
	}
	
	public Funcionario mapToModelUpdateParcial(final FuncionarioRequestUpdateParcial funcionario) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(funcionario, Funcionario.class);
	}
	
	public Funcionario mapToModelUpdate(final FuncionarioRequestUpdate funcionario) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(funcionario, Funcionario.class);
	}
	
	public List<FuncionarioModel> listToFuncionarioModel(final List<Funcionario> funcionarios) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		final List<FuncionarioModel> models = new ArrayList<>();
		
		funcionarios.stream().forEach(f ->{
			models.add(modelMapper.map(f, FuncionarioModel.class));
		});
		
		return models;
	}
	
//	public static <T> Page<T> toPageable(final List<T> list) {
//		
//		final Page<T> pageble = new PageImpl<>(list);	
//		
//		return pageble;
//	}

}
