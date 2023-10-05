package br.com.erudio.model.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

// serve para que o SpringBoot encare o PersonServices como um objeto que sera injetado em runtime em outras classes.
// utilizado para que o objeto PersonServices seja injetado com Autowired
@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;

	public Person findById(Long id) {
		logger.info("Finding one person!");

		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}

	public List<Person> findAll() {
		logger.info("Finding all people!");

		return repository.findAll();
	}

	public Person create(Person person) {
		logger.info("Creating one person!");
		return repository.save(person);
	}

	public Person update(Person person) {
		logger.info("Updating one person!");

		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());

		return repository.save(entity);
	}

	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		repository.delete(entity);

		logger.info("Deleting one person!");
	}
}
