package it.tdgroup.corso.rest.risorse;

import com.mongodb.client.MongoCollection;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.tdgroup.corso.rest.risorse.entity.Risorsa;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.risorse.mapper.RisorsaMapperImpl;
import it.tdgroup.corso.rest.risorse.RisorsaRepository;
import it.tdgroup.corso.rest.risorse.RisorseService;
import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.apachecommons.CommonsLog;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class RisorseServiceImpl implements RisorseService {

    @Autowired
    RisorsaRepository risorsaRepository;//accesso al db

    @Autowired
    RisorsaMapperImpl risorsaMapper;//convertitore da dto a risorsa e viceversa

    /* Abilitare per utilizzo kafka
    @Inject
    @Channel("risorsa-channel-out")
    Emitter<RisorsaDTO> risorsaEmitter;
    */

    @Override
    public RisorsaDTO findRisorsaByUsername(String username) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findByUsername(username);
            if (risorsa.isPresent()) {
                return risorsaMapper.convertEntityToDto(risorsa.get());
            }
            return null;
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public RisorsaDTO findById(String id) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findById(id);
            if (risorsa.isPresent()) {
                return risorsaMapper.convertEntityToDto(risorsa.get());
            }
            throw new ServiceException("Risorsa con id:" + id + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public RisorsaDTO findByfirstName(String firstName) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findById(firstName);
            if (risorsa.isPresent()) {
                return risorsaMapper.convertEntityToDto(risorsa.get());
            }
            throw new ServiceException("Risorsa con firstName:" + firstName + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
    @Override
    public List<RisorsaDTO> findByLastName(String lastName) throws ServiceException {
        try {
            List<Risorsa> risorsa = risorsaRepository.findBylastName(lastName);
            if (!risorsa.isEmpty()) {
                return risorsaMapper.convertEntityToDto(risorsa);
            }
            throw new ServiceException("Risorsa con lastName:" + lastName + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void aggiornaRisorsa(String idRisorsa, RisorsaDTO risorsaDTO) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findById(idRisorsa);
            if (risorsa.isPresent()) {
                log.info("Risorsa con id:" + idRisorsa + "trovata sul DB.");
                Risorsa newStudente = risorsaMapper.convertDtoToEntity(risorsaDTO);
                newStudente.setId(risorsa.get().getId());
                if(newStudente.getEmail() == null)
                {
                    newStudente.setEmail(risorsa.get().getEmail());
                }
                if(newStudente.getFirstName() == null)
                {
                    newStudente.setFirstName(risorsa.get().getFirstName());
                }
                if(newStudente.getPassword() == null)
                {
                    newStudente.setPassword(risorsa.get().getPassword());
                }
                if(newStudente.getLastName() == null)
                {
                    newStudente.setLastName(risorsa.get().getLastName());
                }
                if(newStudente.getUsername() == null)
                {
                    newStudente.setUsername(risorsa.get().getUsername());
                }
                if(newStudente.getState() == null)
                {
                    newStudente.setState(risorsa.get().getState());
                }
                risorsaRepository.save(newStudente);
                return;
            }
            throw new ServiceException("Risorsa con id:" + idRisorsa + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<RisorsaDTO> findByLastNameEndingWithOrderByFirstName(String lastName) throws ServiceException {
        List<Risorsa> risorsa = risorsaRepository.findByLastNameEndingWithOrderByFirstName(lastName);
        try {
            return risorsaMapper.convertEntityToDto(risorsa);
        } catch (MapperException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public List<RisorsaDTO> elenco() throws ServiceException {
        try {
            List<Risorsa> risorsa = risorsaRepository.findAll();
            return risorsaMapper.convertEntityToDto(risorsa);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public String crea(RisorsaDTO risorsaDTO) throws ServiceException {
        try {
            Risorsa risorsa = risorsaMapper.convertDtoToEntity(risorsaDTO);
            risorsaRepository.save(risorsa);
            //String id = risorsa.getId().toString();
            log.info("ID risorsa creato:" + risorsa.getId().toHexString());
            return risorsa.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void cancella(String id) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findById(id);
            if (risorsa.isPresent()) {
                risorsaRepository.delete(risorsa.get());
            } else {
                throw new ServiceException("Risorsa con id:" + id + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public void creaRisorsaAsync(RisorsaDTO risorsaDTO) throws ServiceException {
        try {
            //Abilitare per utilizzo kafka
            //risorsaEmitter.send(risorsaDTO);
            log.info("Risorsa:"+risorsaDTO+" inviata correttamente su coda KAFKA");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}


