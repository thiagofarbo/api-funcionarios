package br.com.webwork.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webwork.funcionario.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
