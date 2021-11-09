package it.tdgroup.corso.rest.eventi.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.eventi.dto.EventoDTO;
import it.tdgroup.corso.rest.eventi.entity.Evento;
import it.tdgroup.corso.rest.eventi.partecipante.PartecipanteMapperImpl;
import it.tdgroup.corso.rest.eventi.utente.UtenteMapperImpl;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ApplicationScoped
public class EventoMapperImpl extends AbstractMapperComponent<EventoDTO, Evento> {

    @Inject
    StatoMapperImpl statoMapper;

    @Inject
    UtenteMapperImpl utenteMapper;

    @Inject
    PartecipanteMapperImpl partecipanteMapper;

    @Override
    public EventoDTO convertEntityToDto(Evento entity) throws MapperException {
        if(entity != null)
        {
            return EventoDTO.builder()
                    .stato(statoMapper.convertEntityToDto(entity.getStato()))
                    .dataFine(ZonedDateTime.ofInstant(entity.getDataFine().toInstant(), ZoneId.systemDefault())//bisogna usare questi comandi per controllare al meglio le date
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .dataInizio(ZonedDateTime.ofInstant(entity.getDataInizio().toInstant(), ZoneId.systemDefault())
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .descrizione(entity.getDescrizione())
                    .nome(entity.getNome())
                    .organizzatore(utenteMapper.convertEntityToDto(entity.getOrganizzatore()))
                    .partecipanti(partecipanteMapper.convertEntityToDto(entity.getPartecipanti()))
                    .build();
        }
        return null;
    }

    @Override
    public Evento convertDtoToEntity(EventoDTO dto) throws MapperException, ParseException {
        if(dto != null)
        {
            Evento evento = new Evento();
            evento.setDataInizio(Date.from(ZonedDateTime.parse(dto.getDataInizio(),
                    DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
            evento.setDataFine(Date.from(ZonedDateTime.parse(dto.getDataFine(),
                    DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
            evento.setDescrizione(dto.getDescrizione());
            evento.setNome(dto.getNome());
            evento.setOrganizzatore(utenteMapper.convertDtoToEntity(dto.getOrganizzatore()));
            evento.setStato(statoMapper.convertDtoToEntity(dto.getStato()));
            evento.setPartecipanti(partecipanteMapper.convertDtoToEntity(dto.getPartecipanti()));
            return evento;
        }
        return null;
    }
}


/*
{
        "dataFine": "11/11/1111",
        "dataInizio": "22/22/2222",
        "descrizione": "siamo nella pizzeria",
        "nome": "pizzata",
        "organizzatore": {
        "cognome": "gino",
        "nome": "freschi"
        },
        "partecipanteList": [
        {
        "cognome": "carmine",
        "email": "dsadsa",
        "nome": "dsadssaaa",
        "numCell": "342786438842",
        "quota": 10
        }
        ],
        "stato": "In lavorazione" <-- deve prendere un valore dell'enum per forza tra IN_LAVORAZIONE e PUBBLICATO
        }*/
