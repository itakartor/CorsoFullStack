package it.tdgroup.corso.rest.risorse.esame;

import it.tdgroup.corso.rest.exception.ServiceException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "esameRepository")
public interface EsameRepository extends MongoRepository<Esame, String> {
    Optional<Esame> findByDenominazione(String denominazione) throws ServiceException;

}
