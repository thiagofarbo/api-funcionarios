package br.com.webwork.funcionario.vault.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class VaultConfig {
	
	private String excited;
	
	private String foo;
	
}
