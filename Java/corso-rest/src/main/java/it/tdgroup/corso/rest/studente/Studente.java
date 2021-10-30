package it.tdgroup.corso.rest.studente;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "STUDENTE")
public class Studente {

    @Id
    private ObjectId id;
    private String nome;
    private String cognome;
    private String matricola;
    private int voto;
    private String email;
    private Date dataCreazione;
    private Date dataModifica;
}
