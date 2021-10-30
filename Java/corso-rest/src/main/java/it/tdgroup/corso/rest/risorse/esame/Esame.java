package it.tdgroup.corso.rest.risorse.esame;

import it.tdgroup.corso.rest.risorse.docente.Docente;
import it.tdgroup.corso.rest.studente.Studente;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Document(collection = "ESAME")
public class Esame {
    @Id
    private ObjectId id;
    private String denominazione;
    private Docente docente;
    private List<Studente> studenti;
    private Date dataCreazione;
    private Date dataModifica;
}
