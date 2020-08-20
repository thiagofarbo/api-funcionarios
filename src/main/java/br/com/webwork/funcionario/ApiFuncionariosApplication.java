package br.com.webwork.funcionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "*")
@SpringBootApplication
public class ApiFuncionariosApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ApiFuncionariosApplication.class, args);
	}
}