package it.tdgroup.corso.rest.eventi;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import it.tdgroup.corso.rest.eventi.dto.FilterEventoDTO;
import it.tdgroup.corso.rest.eventi.entity.Evento;

import javax.enterprise.context.ApplicationScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

@ApplicationScoped
public class EventoRepository implements PanacheMongoRepository<Evento> {

    public PanacheQuery<Evento> find(FilterEventoDTO filterDTO) throws ParseException {
        if (filterDTO != null) {
            QueryBuilder query;
            query = new QueryBuilder().and(criteriaNome(filterDTO.getNome()),
                    criteriaDataFine(filterDTO.getDataFine()),
                    criteriaDataInizio(filterDTO.getDataInizio()),
                    criteriaDescrizione(filterDTO.getDescrizione()));
            return find(query.get().toString(), new Parameters());
        }
        return null;
    }
    private DBObject criteriaNome(String nome)
    {
        if(nome != null)
        {
            return QueryBuilder.start("nome").is(nome).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaDescrizione(String descrizione)
    {
        if(descrizione != null)
        {
            return QueryBuilder.start("descrizione").regex(Pattern.compile(descrizione)).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaDataInizio(String dataInizio) throws ParseException {
        if(dataInizio != null)
        {
            return QueryBuilder.start("dataInizio").is(new SimpleDateFormat("dd/MM/yyyy").parse(dataInizio)).get();
        }
        return new QueryBuilder().get();
    }
    private DBObject criteriaDataFine(String dataFine) throws ParseException {
        if(dataFine != null)
        {
            return QueryBuilder.start("dataFine").is(new SimpleDateFormat("dd/MM/yyyy").parse(dataFine)).get();
        }
        return new QueryBuilder().get();
    }
}
