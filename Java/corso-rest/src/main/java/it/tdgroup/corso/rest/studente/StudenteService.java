package it.tdgroup.corso.rest.studente;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;

import java.util.List;

public interface StudenteService {

    String Post(StudenteDTO studenteDTO) throws MapperException, ServiceException;

    void Delete(String matricola) throws ServiceException;

    List<StudenteDTO> findByNome(String nome);

    List<StudenteDTO> findAll() throws MapperException;

    StudenteDTO findByMatricola(String matricola) throws ServiceException;

    void Put(String idStudente, StudenteDTO studenteDTO) throws ServiceException;
}
