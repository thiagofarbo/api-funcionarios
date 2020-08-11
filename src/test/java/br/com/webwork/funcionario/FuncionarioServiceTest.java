package br.com.webwork.funcionario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.webwork.funcionario.domain.Funcionario;
import br.com.webwork.funcionario.domain.exception.NoContentCustom;
import br.com.webwork.funcionario.domain.exception.NotFoundCustom;
import br.com.webwork.funcionario.enums.StatusFuncionarioEnum;
import br.com.webwork.funcionario.repository.FuncionarioRepository;
import br.com.webwork.funcionario.request.FuncionarioRequest;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdate;
import br.com.webwork.funcionario.response.FuncionarioResponse;
import br.com.webwork.funcionario.service.FuncionarioService;
import br.com.webwork.funcionario.util.Mapper;

@RunWith(MockitoJUnitRunner.class)
public class FuncionarioServiceTest {
	
	private final Integer PAGE = 1;

    private final Integer SIZE = 50;
     
    @Mock
 	private Mapper mapper;
	
	@Mock
	private FuncionarioRepository funcionarioRepository;

	@InjectMocks
	private FuncionarioService funcionarioService;

	@Test
	public void salvarFuncionarioTest() {
		
		when(mapper.mapToModelResponse(any())).thenReturn(builderFuncionarioResponse());
		
		FuncionarioResponse funcionarioResponse = this.funcionarioService.salvarFuncionario(builderFuncionarioRequest());
		
		assertNotNull(funcionarioResponse);
	}
	
	@Test
	public void consultarFuncionario() {
		
		when(mapper.mapToModelResponse(any())).thenReturn(builderFuncionarioResponse());
		
		when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(builderFuncionario()));
		
		FuncionarioResponse funcionario = this.funcionarioService.consultarFuncionario(1L);

		assertNotNull(funcionario);
	}
	
	@Test(expected = NotFoundCustom.class)
	public void consultarFuncionarioComIdFuncionarioInexistente() {
		
		FuncionarioResponse funcionario = this.funcionarioService.consultarFuncionario(null);

		assertNotNull(funcionario);
	}
	
	@Test
	public void listarFuncionarioTest() {
		
		Page<Funcionario> pageCartoes = new PageImpl<>(Arrays.asList(builderFuncionario()));
		
		when(funcionarioRepository.findAll(PageRequest.of(PAGE, SIZE))).thenReturn(pageCartoes);
		
		Page<Funcionario> funcionarios = this.funcionarioService.listarFuncionarios(PAGE,SIZE);
		
		assertFalse(funcionarios.isEmpty());

	}
	
	@Test(expected = NoContentCustom.class)
	public void listarFuncionariosComRetornoVazioTest() {
		
		Page<Funcionario> pageFuncionarios = new PageImpl<>(Arrays.asList());
		
		when(funcionarioRepository.findAll(PageRequest.of(PAGE, SIZE))).thenReturn(pageFuncionarios);
		
		Page<Funcionario> funcionarios = this.funcionarioService.listarFuncionarios(PAGE, SIZE);
		
		assertTrue(funcionarios.getContent().isEmpty());

	}
	
	@Test
	public void atualizarFuncionario() {
		
		FuncionarioRequestUpdate funcionarioRequest = this.builderFuncionarioRequestUpdate();
		
		Funcionario builderFuncionario = this.builderFuncionario();
		
		when(mapper.mapToModelUpdate(any(FuncionarioRequestUpdate.class))).thenReturn(builderFuncionario);
		
		when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(builderFuncionario));
		
		when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(builderFuncionario);
		
		funcionarioService.atualizarFuncionario(funcionarioRequest, 1L);

		verify(funcionarioRepository, times(1)).findById(anyLong());
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));

	}
	
	@Test(expected = NotFoundCustom.class)
	public void atualizarFuncionarioComIdFuncionarioInexistente() {
		
		FuncionarioRequestUpdate cartaoRequest = this.builderFuncionarioRequestUpdate();
		
		this.funcionarioService.atualizarFuncionario(cartaoRequest, null);
	}
	
	@Test
	public void excluirFuncionario() {
		
		Funcionario builderCartao = this.builderFuncionario();
		
		when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(builderCartao));
		
		String response = this.funcionarioService.excluirFuncionario(1L);
		
		Assert.assertNotNull(response);
	}
	
	@Test(expected = NotFoundCustom.class)
	public void excluirFuncionarioComIdFuncionarioInexistente() {
		
		Optional<Funcionario> funcionario = Optional.empty();
		
		when(funcionarioRepository.findById(anyLong())).thenReturn(funcionario);
		
		this.funcionarioService.excluirFuncionario(1L);
	}
	
	
	@Test(expected = NotFoundCustom.class)
	public void atualizarFuncionarioComIdNull() {
		
		FuncionarioRequestUpdate funcionarioRequest = FuncionarioRequestUpdate.builder()
			.nome("Jonh")
			.cargo("Engenheiro")
			.cpf("12344566677")
			.dataAdmissao(LocalDate.now())	
			.dataDemissao(LocalDate.now())
			.status(StatusFuncionarioEnum.INATIVO).build();
		
		this.funcionarioService.atualizarFuncionario(funcionarioRequest, null);
	}
	
	private Funcionario builderFuncionario() {
		
		return Funcionario.builder()
	    .id(1L)
		.nome("Jonh")	
		.cargo("Engenheiro")
		.cpf("12344566677")
		.dataAdmissao(LocalDate.now())	
		.dataDemissao(LocalDate.now())
		.status(StatusFuncionarioEnum.INATIVO).build();
	}
	
	private FuncionarioRequestUpdate builderFuncionarioRequestUpdate() {
		
		return FuncionarioRequestUpdate.builder()
			.nome("Jonh")
			.cpf("12344566677")
			.cargo("Engenheiro")
			.dataAdmissao(LocalDate.now())	
			.dataDemissao(LocalDate.now())
			.status(StatusFuncionarioEnum.ATIVO).build();
	}
	
	private FuncionarioRequest builderFuncionarioRequest() {
		
		return FuncionarioRequest.builder()
			.nome("Jonh")
			.cargo("Engenheiro")
			.cpf("12344566677")
			.dataAdmissao(LocalDate.now())	
			.dataDemissao(LocalDate.now())
			.status(StatusFuncionarioEnum.ATIVO).build();
	}
	
	private FuncionarioResponse builderFuncionarioResponse() {
		
		return FuncionarioResponse.builder()
			.nome("Jonh")
			.cargo("Engenheiro")
			.cpf("12344566677")
			.dataAdmissao(LocalDate.now())	
			.dataDemissao(LocalDate.now())
			.status(StatusFuncionarioEnum.ATIVO).build();
	}
}
