package br.com.sci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sci.model.Pessoa;
import br.com.sci.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Pessoa salvar(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Pessoa alterar(@RequestBody Pessoa newPessoa, @PathVariable Long id) {
		return pessoaRepository.findById(id).map(pessoa -> {
			pessoa.setNome(newPessoa.getNome());
			pessoa.setCpf(newPessoa.getCpf());
			return pessoaRepository.save(pessoa);
		}).orElseGet(() -> {
			newPessoa.setId(id);
			return pessoaRepository.save(newPessoa);
		});
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteEmployee(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}
}
