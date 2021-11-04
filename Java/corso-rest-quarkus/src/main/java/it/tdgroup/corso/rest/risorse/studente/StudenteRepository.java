package it.tdgroup.corso.rest.risorse.studente;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class StudenteRepository  implements PanacheMongoRepository<Studente> {

    public Optional<Studente> findByMatricola(String matricola) {
        return find("matricola", matricola).firstResultOptional();
    }


    public PanacheQuery<Studente> find(FilterDTO filterDTO) {
        if (filterDTO != null) {
            QueryBuilder query;
            if(filterDTO.getResidenza() != null)
            {
                    query = new QueryBuilder().and(
                        criteriaMatricola(filterDTO.getMatricola()),
                        criteriaNome(filterDTO.getNome()),
                        criteriaCognome(filterDTO.getCognome()),
                        criteriaEmail(filterDTO.getEmail()),
                        criteriaVoto(filterDTO.getVoto()),
                        criteriaResidenzaCap(filterDTO.getResidenza()),
                        criteriaResidenzaProvincia(filterDTO.getResidenza()),
                        criteriaResidenzaVia(filterDTO.getResidenza()));

            }
            else
            {
                query = new QueryBuilder().and(
                        criteriaMatricola(filterDTO.getMatricola()),
                        criteriaNome(filterDTO.getNome()),
                        criteriaCognome(filterDTO.getCognome()),
                        criteriaEmail(filterDTO.getEmail()),
                        criteriaVoto(filterDTO.getVoto()));
            }
            return find(query.get().toString(), new Parameters());
        }
        return null;
    }
    private DBObject criteriaMatricola(String matricola)
    {
        if(matricola != null)
        {
            return QueryBuilder.start("matricola").is(matricola).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaNome(String nome)
    {
        if(nome != null)
        {
            return QueryBuilder.start("nome").is(nome).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaCognome(String cognome)
    {
        if(cognome != null)
        {
            return QueryBuilder.start("cognome").is(cognome).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaEmail(String email)
    {
        if(email != null)
        {
            return QueryBuilder.start("email").is(email).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaVoto(Integer voto)
    {
        if(voto != null)
        {
            return QueryBuilder.start("voto").is(voto).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaResidenzaCap(ResidenzaDTO residenza)
    {
        if(residenza.getCap() != null)
        {
            return QueryBuilder.start("residenza.cap").is(residenza.getCap()).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaResidenzaVia(ResidenzaDTO residenza)
    {
        if(residenza.getVia() != null)
        {
            return QueryBuilder.start("residenza.via").is(residenza.getVia()).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaResidenzaProvincia(ResidenzaDTO residenza)
    {
        if(residenza.getProvincia() != null)
        {
            return QueryBuilder.start("residenza.provincia").is(residenza.getProvincia()).get();
        }
        return new QueryBuilder().get();
    }
}


