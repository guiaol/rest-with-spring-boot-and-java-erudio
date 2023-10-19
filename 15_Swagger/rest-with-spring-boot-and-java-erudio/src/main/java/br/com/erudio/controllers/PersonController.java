package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

	// com esta anotacao, o SpringBoot ira cuidar da instanciacao, faz um new PersonServices() de forma dinamica em tempo de execucao.
	// para injetar uma instancia com @Autowired é necessária que a classe a ser injetada esteja marcada com @Service ou alguma outra annotation que seja um alias para @Component, qu todas as anotations injetadas sao. 
	@Autowired
	private PersonServices service;	
	// private PersonServices services = new PersonServices();
			
	// pode ser chamado no browser ou no postman
	@GetMapping(value = "/{id}", 
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Find a Person", description = "Find a Person", tags = {"People"}, 
	responses = {
			@ApiResponse(description = "Success", 
					responseCode = "200", 
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		return service.findById(id);
	}
	
	// pode ser chamado no browser ou no postman
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Find All People", description = "Find All People", tags = {"People"}, 
		responses = {
				@ApiResponse(description = "Success", 
						responseCode = "200", 
						content = {
								@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
						}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	// pode ser chamado apenas no postman
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a new Person", description = "Adds a new Person by passing in a JSON, XML or YML representation of the person", tags = {"People"}, 
	responses = {
			@ApiResponse(description = "Success", 
					responseCode = "200", 
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO create(
			// A anottation @RequestBody faz com que recebamos a app receba parametros via body (corpo da requisicao)
			@RequestBody PersonVO person) throws Exception {
		return service.create(person);
	}
	
	// pode ser chamado apenas no postman
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a Person", description = "Updates a Person by passing in a JSON, XML or YML representation of the person", tags = {"People"}, 
	responses = {
			@ApiResponse(description = "Updated", 
					responseCode = "200", 
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO update(
			// A anottation @RequestBody faz com que recebamos a app receba parametros via body (corpo da requisicao)
			@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}	

	// pode ser chamado apenas no postman
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Person", description = "Deletes a Person by passing in a JSON, XML or YML representation of the person", tags = {"People"}, 
	responses = {
			@ApiResponse(description = "No Contend", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}