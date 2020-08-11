package br.com.webwork.funcionario.resource;

import static br.com.webwork.funcionario.exception.GlobalExceptionHandler.MENSAGEM_GLOBAL_400;
import static br.com.webwork.funcionario.exception.GlobalExceptionHandler.MENSAGEM_GLOBAL_404;
import static br.com.webwork.funcionario.exception.GlobalExceptionHandler.MENSAGEM_GLOBAL_500;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.webwork.funcionario.domain.Funcionario;
import br.com.webwork.funcionario.exception.ErrorInfo;
import br.com.webwork.funcionario.request.FuncionarioRequest;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdate;
import br.com.webwork.funcionario.request.FuncionarioRequestUpdateParcial;
import br.com.webwork.funcionario.response.FuncionarioResponse;
import br.com.webwork.funcionario.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "/funcionarios", tags = { "Funcionários" })
@ApiOperation(value = "funcionarios", notes = "API de Funcionários", response = FuncionarioResource.class)
@ApiResponses({ @ApiResponse(code = 400, message = MENSAGEM_GLOBAL_400, response = ErrorInfo.class),
		@ApiResponse(code = 404, message = MENSAGEM_GLOBAL_404, response = ErrorInfo.class),
		@ApiResponse(code = 500, message = MENSAGEM_GLOBAL_500, response = ErrorInfo.class) })
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FuncionarioResource {
	
	@Value("${csv.name}")
	private String filaName;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@ResponseBody
	@PostMapping("/funcionarios")
	@ApiOperation(value = "salvar funcionário")
	private ResponseEntity<FuncionarioResponse> salvar(@Valid @RequestBody final FuncionarioRequest funcionarioRequest){
		return ResponseEntity.ok(this.funcionarioService.salvarFuncionario(funcionarioRequest));
	}
	
	@GetMapping("/funcionarios/{id}")
	@ApiOperation(value = "Consultar funcionário")
	public ResponseEntity<FuncionarioResponse> consultarFuncionario(@PathVariable final Long id){
		return ResponseEntity.ok(this.funcionarioService.consultarFuncionario(id));
	}
	
	@GetMapping("/funcionarios")
	@ApiOperation(value = "Listagem de funcionários")
	public ResponseEntity<Page<Funcionario>> listar(@RequestParam(defaultValue = "0") final int page, @RequestParam(defaultValue = "50") final int size){
		return ResponseEntity.ok(this.funcionarioService.listarFuncionarios(page, size));
	}
	
	@PutMapping("/funcionarios/{id}")
	@ApiOperation(value = "Atualuzar funcionário")
	public ResponseEntity<FuncionarioResponse> atualizar(@Valid @RequestBody final FuncionarioRequestUpdate funcionarioRequest, @PathVariable final Long id){
		return ResponseEntity.ok(this.funcionarioService.atualizarFuncionario(funcionarioRequest, id));
	}
	
	@PatchMapping("/funcionarios/{id}")
	@ApiOperation(value = "Atualuzar dados do funcionário")
	public ResponseEntity<FuncionarioResponse> atualizarFuncionarioParcial(@Valid @RequestBody final FuncionarioRequestUpdateParcial funcionarioRequest, @PathVariable final Long id){
		return ResponseEntity.ok(this.funcionarioService.atualizarFuncionarioParcial(funcionarioRequest, id));
	}
	
	@DeleteMapping("/funcionarios/{id}")
	@ApiOperation(value = "Deletar funcionário")
	public ResponseEntity<String> deletar(@PathVariable final Long id){
		return ResponseEntity.ok(this.funcionarioService.excluirFuncionario(id));
	}
	
	@GetMapping("/excel/funcionarios")
	@ApiOperation(value = "Download csv")
	public ResponseEntity<Void> getCsv(HttpServletResponse response) {
		
		response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filaName + "\"");
		
		this.funcionarioService.generateCsv(response);
	    return new ResponseEntity<Void>(HttpStatus.OK);
	}
}