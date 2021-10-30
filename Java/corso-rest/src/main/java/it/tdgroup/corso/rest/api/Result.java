package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.risorse.esame.EsameDTO;
import it.tdgroup.corso.rest.studente.StudenteDTO;
import lombok.Data;

import java.util.List;
@Data
public class Result {
    List<StudenteDTO> studenti;
    List<EsameDTO> esami;

}
