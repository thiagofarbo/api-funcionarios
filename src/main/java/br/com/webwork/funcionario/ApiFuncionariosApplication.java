package br.com.webwork.funcionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class ApiFuncionariosApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ApiFuncionariosApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		Logger logger = LoggerFactory.getLogger(ApiFuncionariosApplication.class);
//
//		logger.info("----------------------------------------");
//		logger.info("Configuration properties");
//		logger.info("   example.username is {}", vaultConfig.getUsername());
//		logger.info("   example.password is {}", vaultConfig.getPassword());
//		logger.info("----------------------------------------");
//
//	}
}