package br.com.webwork.funcionario.swagger;

import java.io.FileReader;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.webwork.funcionario")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}
	
	public ApiInfo apiInfo(){
		
		Model model = this.getInfoApplication();
		return new ApiInfoBuilder().title("Swagger API")
				.description(model.getDescription()).version(model.getVersion()).build();
	}
	
	public Model getInfoApplication() {
		Model model = new Model();
		MavenXpp3Reader reader = new MavenXpp3Reader();
		try {
			model = reader.read(new FileReader("pom.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return model;
	}
}