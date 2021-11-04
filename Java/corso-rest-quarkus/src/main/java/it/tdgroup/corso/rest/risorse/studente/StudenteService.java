package it.tdgroup.corso.rest.risorse.studente;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.api.exception.ServiceException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface StudenteService {
    String crea(StudenteDTO studenteDTO) throws ServiceException;

    StudenteDTO findByMatricola(String matricola) throws ServiceException;

    List<StudenteDTO> find(FilterDTO filterDTO) throws ServiceException;

    List<StudenteDTO> elenco() throws MapperException, ServiceException;
}
