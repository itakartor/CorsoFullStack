package it.tdgroup.corso.rest.risorse.esame;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import it.tdgroup.corso.rest.risorse.esame.docente.DocenteDTO;
import it.tdgroup.corso.rest.studente.StudenteDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = EsameDTO.EsameDTOBuilder.class)
public class EsameDTO {

    private String denominazione;
    private DocenteDTO docente;
    private List<StudenteDTO> studenti;

    @JsonPOJOBuilder(withPrefix = "")
    public static class EsameDTOBuilder {
    }
}
