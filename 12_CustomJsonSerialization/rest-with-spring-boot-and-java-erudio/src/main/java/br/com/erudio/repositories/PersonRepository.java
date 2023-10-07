package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.Person;

/*
 * Spring @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
 * Ao estender o JpaRepository fornece um crud basico.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
