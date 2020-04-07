package br.com.webwork.funcionario.rest;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.webwork.funcionario.response.FuncionarioResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FuncionarioAPIRestRepository {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Value("${app.funcionario.host}")
	private String url; 
	
	
	public FuncionarioResponse getExcelFuncionario() throws URISyntaxException {
		
		this.restTemplate = restTemplate();
		
		
		final FuncionarioResponse responseJson = restTemplate.getForObject(url+"/1", FuncionarioResponse.class);
	    log.info("Funcionario: {}, status: {}", url, responseJson.getStatus());
	    
	    return responseJson;
	
	}
}
