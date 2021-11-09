package it.tdgroup.corso.rest.eventi.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import it.tdgroup.corso.rest.eventi.partecipante.Partecipante;
import it.tdgroup.corso.rest.eventi.utente.Utente;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
@MongoEntity(collection="EVENTO")
public class Evento {

    private ObjectId id;
    private String nome;//pizzata
    private String descrizione;//mangiare una pizza tutti insieme
    private Date dataInizio;
    private Date dataFine;
    private Utente organizzatore;//nome + cognome + id
    private List<Partecipante> partecipanti;//nome + cognome + email + numCell + quota(int)
    private Stato stato;//bozza,pronto -> faccio un enum
}

//da fare post di ricerca con filtro ->nome,descrizione(anche parziale per esempio solo una parola),
// data inizio e data fine