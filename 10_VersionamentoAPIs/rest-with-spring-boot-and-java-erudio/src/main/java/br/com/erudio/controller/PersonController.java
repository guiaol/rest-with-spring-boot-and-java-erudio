package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.model.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	// com esta anotacao, o SpringBoot ira cuidar da instanciacao, faz um new PersonServices() de forma dinamica em tempo de execucao.
	// para injetar uma instancia com @Autowired é necessária que a classe a ser injetada esteja marcada com @Service ou alguma outra annotation que seja um alias para @Component, qu todas as anotations injetadas sao. 
	@Autowired
	private PersonServices service;	
	// private PersonServices services = new PersonServices();
			
	// pode ser chamado no browser ou no postman
	@GetMapping(value = "/{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		return service.findById(id);
	}
	
	// pode ser chamado no browser ou no postman
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	// pode ser chamado apenas no postman
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO create(
			// A anottation @RequestBody faz com que recebamos a app receba parametros via body (corpo da requisicao)
			@RequestBody PersonVO person) throws Exception {
		return service.create(person);
	}
	// pode ser chamado apenas no postman
	@PostMapping(value = "/v2",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV2 createV2(
			// A anottation @RequestBody faz com que recebamos a app receba parametros via body (corpo da requisicao)
			@RequestBody PersonVOV2 person) throws Exception {
		return service.createV2(person);
	}
	
	// pode ser chamado apenas no postman
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO update(
			// A anottation @RequestBody faz com que recebamos a app receba parametros via body (corpo da requisicao)
			@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}	

	// pode ser chamado apenas no postman
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}