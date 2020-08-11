package br.com.webwork.funcionario.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("funcionario")
public class Credentials {

	private String username;
	
	private String password;
	
}
