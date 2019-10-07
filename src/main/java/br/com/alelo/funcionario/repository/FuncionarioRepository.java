package br.com.alelo.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.funcionario.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
