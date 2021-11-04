package it.tdgroup.corso.rest.risorse.studente;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.risorse.entity.Risorsa;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;



@ApplicationScoped
public class StudenteMapperImpl extends AbstractMapperComponent<StudenteDTO, Studente> {

    @Inject
    ResidenzaMapperImpl residenzaMapper;

    @Override
    public StudenteDTO convertEntityToDto(Studente entity) throws MapperException {
        if(entity != null)
        {
            StudenteDTO studenteDTO = StudenteDTO.builder()
                    .voto(entity.getVoto())
                    .cognome(entity.getCognome())
                    .email(entity.getEmail())
                    .matricola(entity.getMatricola())
                    .nome(entity.getNome())
                    .residenza(residenzaMapper.convertEntityToDto(entity.getResidenza()))
                    .build();

            return studenteDTO;
        }
        return null;
    }

    @Override
    public Studente convertDtoToEntity(StudenteDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Studente entity = new Studente();
                entity.setCognome(dto.getCognome());
                entity.setEmail(dto.getEmail());
                entity.setMatricola(dto.getMatricola());
                entity.setNome(dto.getNome());
                entity.setVoto(dto.getVoto());
                entity.setResidenza(residenzaMapper.convertDtoToEntity(dto.getResidenza()));
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Risorsa " + ex.getMessage());
        }
    }
}
