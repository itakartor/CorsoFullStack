package it.tdgroup.corso.rest.api;

import io.swagger.annotations.*;
import it.tdgroup.corso.rest.exception.ApplicationException;
import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.studente.StudenteDTO;
import it.tdgroup.corso.rest.studente.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.reflections.Reflections.log;

@Api(value = "Servizi per la gestione di un studente", tags = "Studente")
@RestController
@RequestMapping({"${app.context-path}/studenti"})
public class StudentiApi extends BaseApi{

    @Autowired
    StudenteService studenteService;

    @CrossOrigin
    @ApiOperation(value = "Creo Studente", produces = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Insermento corretto")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity creaStudente(@RequestBody StudenteDTO studenteDTO) throws ApplicationException {
        try {
            String idStudente = studenteService.Post(studenteDTO);
            log.info("Nuovo studente creato con id:" + idStudente);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isStudente}").buildAndExpand(idStudente).toUri();//è la uri dello studente appena creato
            return ResponseEntity.created(location).build();
        } catch (MapperException | ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recupero studenti dal nome", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero studenti avvenuto con successo")})
    @GetMapping(value = {"/nome"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaStudentiNome(@RequestParam("nome") String nome) throws ApplicationException {
        try {
            log.info("Recupero studente con nome:" + nome);
            List<StudenteDTO> studenteDTO = studenteService.findByNome(nome);
            Result result = new Result();
            result.setStudenti(studenteDTO);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }
    @CrossOrigin
    @ApiOperation(value = "Recupero tutti gli studenti", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero tutti gli studenti avvenuto con successo")})
    @GetMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaStudenti() throws ApplicationException {
        try {
            log.info("Recupero studenti");
            List<StudenteDTO> studenteDTO = studenteService.findAll();
            Result result = new Result();
            result.setStudenti(studenteDTO);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recupero gli studenti con matricola", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero gli studenti con matricola avvenuto con successo")})
    @GetMapping(value = {"/matricola"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaStudentiMatricola(@RequestParam("matricola") String matricola) throws ApplicationException {
        try {
            log.info("Recupero studenti matricola");
            StudenteDTO studenteDTO = studenteService.findByMatricola(matricola);
            return ResponseEntity.ok(studenteDTO);
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Aggiorna un Studente", consumes = MediaType.APPLICATION_JSON_VALUE, response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Aggiornamento avvenuto con successo")})
    @PutMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@ApiParam(name = "matricolaStudente", value = "matricolaStudente", required = true)
                                     @RequestParam("matricola") String matricola, @RequestBody StudenteDTO studenteDTO) throws ApplicationException {
        try {
            log.info("Aggiorno lo studente con matricola:" + matricola);
            studenteService.Put(matricola,studenteDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }
    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella una studente per matricola", notes = "Cancella una specifico studente data la matricola")
    @DeleteMapping(value = "")
    public ResponseEntity cancellaMatricola(@RequestParam("matricola") String matricola) throws ApplicationException {
        try {
            log.info("Cancella Studente con matricola:" + matricola);
            studenteService.Delete(matricola);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}
