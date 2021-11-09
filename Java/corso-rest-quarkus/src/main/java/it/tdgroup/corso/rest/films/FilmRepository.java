package it.tdgroup.corso.rest.films;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import it.tdgroup.corso.rest.films.dto.FilterFilmDTO;
import it.tdgroup.corso.rest.films.entity.Film;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FilmRepository implements PanacheMongoRepository<Film> {

    public PanacheQuery<Film> find(FilterFilmDTO filterDTO) {
        if (filterDTO != null) {
            QueryBuilder query;
            query = new QueryBuilder().and(criteriaNome(filterDTO.getNome()),
                        criteriaGenere(filterDTO.getGenere()),
                        criteriaNome(filterDTO.getNome()),
                        criteriaOscar(filterDTO.isOscar()),
                        criteriaAttoreNome(filterDTO.getAttoreNome()),
                        criteriaRegistaNome(filterDTO.getRegista().getNome()),
                        criteriaRegistaCognome(filterDTO.getRegista().getCognome()),
                        criteriaRegistaEta(filterDTO.getRegista().getEta()),
                        criteriaRegistaSesso(filterDTO.getRegista().getSesso()));
            return find(query.get().toString(), new Parameters());
        }
        return null;
    }


    /*private String nome;
    private String genere;
    private RegistaDTO regista;
    private boolean oscar;
    private List<AttoreDTO> attori; posso filtrare per il nome degli attori*/

    private DBObject criteriaNome(String nome)
    {
        if(nome != null)
        {
            return QueryBuilder.start("nome").is(nome).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaGenere(String genere)
    {
        if(genere != null)
        {
            return QueryBuilder.start("genere").is(genere).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaOscar(Boolean oscar)
    {
        if(oscar != null)
        {
            return QueryBuilder.start("oscar").is(oscar).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaRegistaNome(String nome)
    {
        if(nome != null)
        {
            return QueryBuilder.start("regista.nome").is(nome).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaRegistaCognome(String cognome)
    {
        if(cognome != null)
        {
            return QueryBuilder.start("regista.cognome").is(cognome).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaRegistaEta(Integer eta)
    {
        if(eta != null)
        {
            return QueryBuilder.start("regista.eta").is(eta).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaRegistaSesso(String sesso)
    {
        if(sesso != null)
        {
            return QueryBuilder.start("regista.sesso").is(sesso).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaAttoreNome(String nomeAttore)
    {
        if(nomeAttore != null)
        {
            return QueryBuilder.start("attori.nome").is(nomeAttore).get();
        }
        return new QueryBuilder().get();
    }
}
