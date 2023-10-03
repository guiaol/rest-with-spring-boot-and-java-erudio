package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
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
	@RequestMapping(value = "/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") String id) throws Exception {
		return service.findById(id);
	}
	
	// pode ser chamado no browser ou no postman
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() {
		return service.findAll();
	}
	
	// pode ser chamado apenas no postman
	@RequestMapping(method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person create(
			// A anottation @RequestBody faz com que recebamos a app receba parametros via body (corpo da requisicao)
			@RequestBody Person person) throws Exception {
		return service.create(person);
	}
	
	// pode ser chamado apenas no postman
	@RequestMapping(method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person update(
			// A anottation @RequestBody faz com que recebamos a app receba parametros via body (corpo da requisicao)
			@RequestBody Person person) throws Exception {
		return service.update(person);
	}	

	// pode ser chamado apenas no postman
	@RequestMapping(value = "/{id}", 
			method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") String id) {
		service.delete(id);
	}
}