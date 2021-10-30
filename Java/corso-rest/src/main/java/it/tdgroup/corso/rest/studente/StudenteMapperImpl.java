package it.tdgroup.corso.rest.studente;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.stereotype.Component;

@Component
public class StudenteMapperImpl extends AbstractMapperComponent<StudenteDTO,Studente> {


    @Override
    public StudenteDTO convertEntityToDto(Studente entity) throws MapperException {
        if(entity != null)
        {
            StudenteDTO studenteDTO = StudenteDTO.builder()
                    .nome(entity.getNome())
                    .cognome(entity.getCognome())
                    .matricola(entity.getMatricola())
                    .voto(entity.getVoto())
                    .email(entity.getEmail())
                    .build();
            return studenteDTO;
        }
        return null;
    }

    @Override
    public Studente convertDtoToEntity(StudenteDTO dto) throws MapperException {
        if(dto != null)
        {
            Studente entity = Studente.builder()
                    .cognome(dto.getCognome())
                    .matricola(dto.getMatricola())
                    .nome(dto.getNome())
                    .voto(dto.getVoto())
                    .email(dto.getEmail())
                    .build();
            //System.out.println(dto.getCognome() + dto.getMatricola() + dto.getNome() + dto.getVoto());
            return entity;
        }
        return null;
    }
}
