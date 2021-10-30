package it.tdgroup.corso.rest.risorse;

import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.risorse.entity.Risorsa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "risorsaRepository")
public interface RisorsaRepository extends MongoRepository<Risorsa, String> {

    @Query
    Optional<Risorsa> findByUsername(String username);

    @Query
    Optional<Risorsa> findByfirstName(String firstName);

    List<Risorsa> findBylastName(String lastName);

    List<Risorsa> findByLastNameEndingWithOrderByFirstName(String lastName) throws ServiceException;

}