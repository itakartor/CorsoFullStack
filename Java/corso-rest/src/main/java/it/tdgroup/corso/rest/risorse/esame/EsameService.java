package it.tdgroup.corso.rest.risorse.esame;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;
import java.util.List;

public interface EsameService {
    String Post(EsameDTO esameDTO) throws MapperException, ServiceException;

    void Delete(String denominazione) throws ServiceException;

    void Put(String denominazione, EsameDTO esameDTO) throws ServiceException;

    List<EsameDTO> findAll() throws MapperException;

    EsameDTO findByDenominazione(String denominazione) throws ServiceException;

}
