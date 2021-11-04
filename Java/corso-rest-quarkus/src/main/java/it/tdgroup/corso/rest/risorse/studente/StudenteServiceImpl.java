package it.tdgroup.corso.rest.risorse.studente;

import io.quarkus.mongodb.panache.PanacheQuery;
import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.entity.Risorsa;
import lombok.extern.apachecommons.CommonsLog;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Model
@CommonsLog
public class StudenteServiceImpl implements StudenteService {

    @Inject
    StudenteRepository studenteRepository;

    @Inject
    StudenteMapperImpl studenteMapper;

    @Override
    public String crea(StudenteDTO studenteDTO) throws ServiceException {
        try {
            Studente studente = studenteMapper.convertDtoToEntity(studenteDTO);
            studenteRepository.persist(studente);
            //String id = risorsa.getId().toString();
            log.info("ID studente creato:" + studente.getId().toHexString());
            return studente.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public StudenteDTO findByMatricola(String matricola) throws ServiceException {
        try {
            Optional<Studente> studente = studenteRepository.findByMatricola(matricola);
            if (studente.isPresent()) {
                return studenteMapper.convertEntityToDto(studente.get());
            }
            return null;
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<StudenteDTO> find(FilterDTO filterDTO) throws ServiceException {
        try {
            PanacheQuery<Studente> studentes = studenteRepository.find(filterDTO);
            if (!studentes.list().isEmpty()) {
                return studenteMapper.convertEntityToDto(studentes.list());
            }
            return null;
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<StudenteDTO> elenco() throws ServiceException {
        try {
            PanacheQuery<Studente> list = studenteRepository.findAll();
            if (!list.list().isEmpty()) {
                return studenteMapper.convertEntityToDto(list.list());
            }
            return null;
        }
        catch (Exception e)
        {
            throw  new  ServiceException(e);
        }
    }
}
