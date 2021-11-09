package it.tdgroup.corso.rest.eventi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import it.tdgroup.corso.rest.eventi.partecipante.PartecipanteDTO;
import it.tdgroup.corso.rest.eventi.utente.UtenteDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder =EventoDTO.EventoDTOBuilder.class)
@RegisterForReflection
public class EventoDTO {

    private String nome;//pizzata
    private String descrizione;//mangiare una pizza tutti insieme
    private String dataInizio;
    private String dataFine;
    private UtenteDTO organizzatore;//nome + cognome + id
    private List<PartecipanteDTO> partecipanti;//nome + cognome + email + numCell + quota(int)
    private String stato;//bozza,pronto -> faccio un enum
    @JsonPOJOBuilder(withPrefix = "")
    public static class EventoDTOBuilder {
    }
}
