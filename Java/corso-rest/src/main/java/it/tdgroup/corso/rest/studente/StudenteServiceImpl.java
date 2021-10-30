package it.tdgroup.corso.rest.studente;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CommonsLog
@Service
public class StudenteServiceImpl implements StudenteService{

    @Autowired
    StudenteRepository studenteRepository;

    @Autowired
    StudenteMapperImpl studenteMapperImpl;

    public String Post(StudenteDTO studenteDTO) throws ServiceException {
        try {
            Optional<Studente> studenteTrovato = studenteRepository.findByMatricola(studenteDTO.getMatricola());
            if (!studenteTrovato.isPresent()) {
                Studente studente = studenteMapperImpl.convertDtoToEntity(studenteDTO);
                studente.setDataCreazione(new Date());
                studente.setDataModifica(studente.getDataCreazione());
                studenteRepository.save(studente);
                log.info("Ho inserito lo studente con id: " + studente.getId());
                return studente.getId().toHexString();
            }
            throw new ServiceException("studente con matricola:" + studenteDTO.getMatricola() + " gia esistente");
        }
        catch (ServiceException | MapperException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void Delete(String matricola) throws ServiceException {
        try {
            Optional<Studente> studente = studenteRepository.findByMatricola(matricola);
            if (studente.isPresent()) {
                studenteRepository.delete(studente.get());
            } else {
                throw new ServiceException("Studente con matricola:" + matricola + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    public List<StudenteDTO> findAll() throws MapperException {
        try{
            List<Studente> students = studenteRepository.findAll();//predo tutti gli studenti
            if(!students.isEmpty())
                return studenteMapperImpl.convertEntityToDto(students);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StudenteDTO findByMatricola(String matricola) throws ServiceException {
        try {
            Optional<Studente> studente = studenteRepository.findByMatricola(matricola);
            if (studente.isPresent()) {
                return studenteMapperImpl.convertEntityToDto(studente.get());
            }
            throw new ServiceException("Risorsa con matricola:" + matricola + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    public List<StudenteDTO> findByNome(String nome){
        try{
            List<Studente> students = studenteRepository.findByNome(nome);
            if(!students.isEmpty())
                return studenteMapperImpl.convertEntityToDto(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void Put(String matricola, StudenteDTO studenteDTO) throws ServiceException {
        try {
            Optional<Studente> studente = studenteRepository.findByMatricola(matricola);
            if (studente.isPresent()) {
                log.info("Studente con matricola:" + matricola + "trovata sul DB.");
                Studente newStudente = studenteMapperImpl.convertDtoToEntity(studenteDTO);
                newStudente.setId(studente.get().getId());
                newStudente.setDataModifica(new Date()); //aggiorno la data di modifica
                newStudente.setDataCreazione(studente.get().getDataCreazione());
                if(newStudente.getEmail() == null)
                {
                    newStudente.setEmail(studente.get().getEmail());
                }
                if(newStudente.getCognome() == null)
                {
                    newStudente.setCognome(studente.get().getCognome());
                }
                if(newStudente.getMatricola() == null)
                {
                    newStudente.setMatricola(studente.get().getMatricola());
                }
                if(newStudente.getNome() == null)
                {
                    newStudente.setNome(studente.get().getNome());
                }
                //System.out.println("voto nuovo utente: "+newStudente.getVoto());
                if(newStudente.getVoto() == 0)//se non è definito nel body della put mi da 0 quindi lo setto
                                                // come in precedenza
                {
                    newStudente.setVoto(studente.get().getVoto());
                }
                studenteRepository.save(newStudente);
                return;
            }
            throw new ServiceException("studente con id:" + matricola + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
