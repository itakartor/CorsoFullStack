package it.tdgroup.corso.rest.risorse.esame;


import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.studente.Studente;
import it.tdgroup.corso.rest.studente.StudenteDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CommonsLog
@Service
public class EsameServiceImpl implements EsameService {

    @Autowired
    EsameRepository esameRepository;

    @Autowired
    EsameMapperImpl esameMapperImpl;

    public String Post(EsameDTO esameDTO) throws ServiceException {
        try {
            String denominazioneTrovato = esameDTO.getDenominazione();
            Optional<Esame> esameTrovato = esameRepository.findByDenominazione(denominazioneTrovato);
            if (!esameTrovato.isPresent()) {
                Esame esame = esameMapperImpl.convertDtoToEntity(esameDTO);
                esame.setDataCreazione(new Date());
                esame.setDataModifica(esame.getDataCreazione());
                esameRepository.save(esame);
                log.info("Ho inserito lo esame con id: " + esame.getId());
                return esame.getId().toHexString();
            }
            throw new ServiceException("esame con denominazione:" + denominazioneTrovato + " gia esistente");
        }
        catch (MapperException | ServiceException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void Delete(String denominazione) throws ServiceException {
        try {
            Optional<Esame> esame = esameRepository.findByDenominazione(denominazione);
            if (esame.isPresent()) {
                esameRepository.delete(esame.get());
            } else {
                throw new ServiceException("Esame con denominazione:" + denominazione + " non trovata!");
            }
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void Put(String denominazione, EsameDTO esameDTO) throws ServiceException {
        try {
            Optional<Esame> esame = esameRepository.findByDenominazione(denominazione);
            if (esame.isPresent()) {
                log.info("Esame con denominazione:" + denominazione + "trovata sul DB.");
                Esame newEsame = esameMapperImpl.convertDtoToEntity(esameDTO);
                newEsame.setId(esame.get().getId());
                newEsame.setDataModifica(new Date()); //aggiorno la data di modifica
                newEsame.setDataCreazione(esame.get().getDataCreazione());
                if(newEsame.getDenominazione() == null)
                {
                    newEsame.setDenominazione(esame.get().getDenominazione());
                }
                if(newEsame.getDocente().getCognome() == null)
                {
                    newEsame.getDocente().setCognome(esame.get().getDocente().getCognome());
                }
                if(newEsame.getDocente().getEmail() == null)
                {
                    newEsame.getDocente().setEmail(esame.get().getDocente().getEmail());
                }
                if(newEsame.getDocente().getNome() == null)
                {
                    newEsame.getDocente().setNome(esame.get().getDocente().getNome());
                }
                //System.out.println("QUESTO è L'ARRAY DEGLI STUDENTI NUOVO: "+newEsame.getStudenti());
                if(newEsame.getStudenti().isEmpty())//andrebbe fatto un controllo più raffinato
                {                                   //per ora controlla solo se metto o meno gli studenti
                                                    //ma se metto anche solo un campo di uno studente perdo tutto il resto
                    newEsame.setStudenti(esame.get().getStudenti());
                }
                esameRepository.save(newEsame);
                return;
            }
            throw new ServiceException("esame con denominazione:" + denominazione + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<EsameDTO> findAll() throws MapperException {
        try{
            List<Esame> esami = esameRepository.findAll();//predo tutti gli studenti
            if(!esami.isEmpty())
                return esameMapperImpl.convertEntityToDto(esami);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EsameDTO findByDenominazione(String denominazione) throws ServiceException {
        try {
            Optional<Esame> esame = esameRepository.findByDenominazione(denominazione);
            if (esame.isPresent()) {
                return esameMapperImpl.convertEntityToDto(esame.get());
            }
            throw new ServiceException("Esame con denominazione:" + denominazione + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
