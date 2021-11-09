package it.tdgroup.corso.rest.eventi.partecipante;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = PartecipanteDTO.PartecipanteDTOBuilder.class)
@RegisterForReflection
public class PartecipanteDTO {

    private String nome;
    private String cognome;
    private String email;
    private String numCell;
    private Integer quota;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PartecipanteDTOBuilder {
    }
}
