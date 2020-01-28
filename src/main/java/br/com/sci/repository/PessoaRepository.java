package br.com.sci.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.sci.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

}
