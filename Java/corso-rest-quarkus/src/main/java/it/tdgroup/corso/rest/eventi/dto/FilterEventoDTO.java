package it.tdgroup.corso.rest.eventi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = FilterEventoDTO.FilterEventoDTOBuilder.class)
@RegisterForReflection
public class FilterEventoDTO {

    private String nome;//pizzata
    private String descrizione;//mangiare una pizza tutti insieme
    private String dataInizio;
    private String dataFine;
    @JsonPOJOBuilder(withPrefix = "")
    public static class FilterEventoDTOBuilder {
    }
}
