package it.tdgroup.corso.rest.studente;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;//in caso che la risorsa non esista mi ritorna null in automatico

@Repository(value = "studenteRepository")
public interface StudenteRepository extends MongoRepository<Studente, String> {

    List<Studente> findByNome(String nome);

    List<Studente> findAll();

    Optional<Studente> findByMatricola(String matricola);

}
