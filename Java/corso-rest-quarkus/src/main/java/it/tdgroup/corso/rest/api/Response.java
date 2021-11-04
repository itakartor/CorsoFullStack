package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.risorse.studente.StudenteDTO;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    List<StudenteDTO> studenteDTOList;
}
