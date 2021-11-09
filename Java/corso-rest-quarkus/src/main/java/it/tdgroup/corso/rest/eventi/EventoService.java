package it.tdgroup.corso.rest.eventi;

import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.eventi.dto.EventoDTO;
import it.tdgroup.corso.rest.eventi.dto.FilterEventoDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface EventoService {

    List<EventoDTO> elenco() throws ServiceException;

    String crea(EventoDTO eventoDTO) throws ServiceException;

    void aggiornaRisorsa(String idEvento, EventoDTO eventoDTO) throws ServiceException;

    void cancella(String id) throws ServiceException;

    List<EventoDTO> find(FilterEventoDTO filterDTO) throws ServiceException;

}
