package it.tdgroup.corso.rest.eventi;

import io.quarkus.mongodb.panache.PanacheQuery;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.eventi.dto.EventoDTO;
import it.tdgroup.corso.rest.eventi.dto.FilterEventoDTO;
import it.tdgroup.corso.rest.eventi.entity.Evento;
import it.tdgroup.corso.rest.eventi.mapper.EventoMapperImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.bson.types.ObjectId;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Model
@CommonsLog
public class EventoServiceImpl implements  EventoService{
    @Inject
    EventoRepository eventoRepository;

    @Inject
    EventoMapperImpl eventoMapper;

    @Override
    public List<EventoDTO> elenco() throws ServiceException {
        try {
            List<Evento> eventos = eventoRepository.listAll();
            return eventoMapper.convertEntityToDto(eventos);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public String crea(EventoDTO eventoDTO) throws ServiceException {
        try {
            Evento evento = eventoMapper.convertDtoToEntity(eventoDTO);
            eventoRepository.persist(evento);
            log.info("ID Evento creato:" + evento.getId().toHexString());
            return evento.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void aggiornaRisorsa(String idEvento, EventoDTO eventoDTO) throws ServiceException {
        try {
            Optional<Evento> evento = eventoRepository.findByIdOptional(new ObjectId(idEvento));
            if (evento.isPresent()) {
                log.info("Evento con id:" + idEvento + " trovata sul DB.");
                Evento newEvento = eventoMapper.convertDtoToEntity(eventoDTO);
                newEvento.setId(evento.get().getId());
                eventoRepository.update(newEvento);
                return;
            }
            throw new ServiceException("Evento con id:" + idEvento + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void cancella(String idEvento) throws ServiceException {
        try {
            Optional<Evento> evento = eventoRepository.findByIdOptional(new ObjectId(idEvento));
            if (evento.isPresent()) {
                eventoRepository.delete(evento.get());
            } else {
                throw new ServiceException("Evento con id:" + idEvento + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<EventoDTO> find(FilterEventoDTO filterDTO) throws ServiceException {
        try {
            PanacheQuery<Evento> eventi = eventoRepository.find(filterDTO);
            if (!eventi.list().isEmpty()) {
                return eventoMapper.convertEntityToDto(eventi.list());
            }
            return null;
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
