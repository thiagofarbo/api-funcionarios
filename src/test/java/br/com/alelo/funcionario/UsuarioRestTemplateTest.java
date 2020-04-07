package br.com.alelo.funcionario;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.alelo.funcionario.domain.Usuario;


@RunWith(SpringRunner.class)
public class UsuarioRestTemplateTest {
	
	private Long id = 1L;
	private String API = "https://jsonplaceholder.typicode.com/todos/";
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private Usuario usuario = new Usuario();
	
    @Test
    public void usuarioMocking() {
       
    	Usuario user = new Usuario(1L, 2L, "delectus aut autem", Boolean.TRUE);
        Mockito.when(restTemplate.getForEntity(API+id, Usuario.class))
       .thenReturn(new ResponseEntity<Usuario>(user, HttpStatus.OK));   
          
        Usuario u = this.usuario.getUsuario();
    	
        assertEquals(user, u);
    }
}