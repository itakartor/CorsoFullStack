package it.tdgroup.corso.rest.api;

import io.swagger.annotations.*;
import it.tdgroup.corso.rest.exception.ApplicationException;
import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.risorse.esame.EsameDTO;
import it.tdgroup.corso.rest.risorse.topic.TopicDTO;
import it.tdgroup.corso.rest.risorse.topic.TopicService;
import it.tdgroup.corso.rest.studente.StudenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.reflections.Reflections.log;

@Api(value = "Servizi per la gestione di un topic", tags = "Topic")
@RestController
@RequestMapping({"${app.context-path}/topics"})

public class TopicsApi extends BaseApi{

    @Autowired
    TopicService topicService;

    @CrossOrigin
    @ApiOperation(value = "Creo Topic", produces = MediaType.APPLICATION_JSON_VALUE, response = TopicDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Inserimento corretto")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity Create(@RequestBody TopicDTO topicDTO) throws ApplicationException {
        try {
            String idTopic = topicService.Post(topicDTO);
            log.info("Nuovo topic creato con id:" + idTopic);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isTopic}").buildAndExpand(idTopic).toUri();
            return ResponseEntity.created(location).build();
        } catch (MapperException | ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella un topic per id", notes = "Cancella una specifico topic con id")
    @DeleteMapping(value = "")
    public ResponseEntity DeleteTopic(@RequestParam("id") String id) throws ApplicationException {
        try {
            log.info("Cancella Topic con id:" + id);
            topicService.Delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
    @CrossOrigin
    @ApiOperation(value = "Aggiorna un Topic", consumes = MediaType.APPLICATION_JSON_VALUE, response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Aggiornamento avvenuto con successo")})
    @PutMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity Update(@ApiParam(name = "topicId", value = "idTopic", required = true)
                                 @RequestParam("id") String id, @RequestBody TopicDTO topicDTO) throws ApplicationException {
        try {
            log.info("Aggiorno il topic con id:" + id);
            topicService.Update(id,topicDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }

    /*@CrossOrigin
    @ApiOperation(value = "Recupero tutti i topic", consumes = MediaType.APPLICATION_JSON_VALUE, response = TopicDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero tutti i topic avvenuto con successo")})
    @GetMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaTopic() throws ApplicationException {
        try {
            log.info("Recupero topic");
            List<TopicDTO> topicDTO = topicService.findAll();
            ResultTopic result = new ResultTopic();
            result.setTopics(topicDTO);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }*/

    @CrossOrigin
    @ApiOperation(value = "Recupero i topic", consumes = MediaType.APPLICATION_JSON_VALUE, response = TopicDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero i topic avvenuto con successo")})
    @GetMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaTopic(@RequestParam("camp") String camp, @RequestParam("value") String value) throws ApplicationException {
        try {
            log.info("Recupero Topics");
            List<TopicDTO> topicDTO = topicService.find(camp,value);
            ResultTopic result = new ResultTopic();
            result.setTopics(topicDTO);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }

   /* @CrossOrigin
    @ApiOperation(value = "Recupero i topic per titolo", consumes = MediaType.APPLICATION_JSON_VALUE, response = TopicDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero i topic avvenuto con successo")})
    @GetMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaTopicTitolo(@RequestParam("titolo") String titolo) throws ApplicationException {
        try {
            log.info("Recupero Topics");
            List<TopicDTO> topicDTO = topicService.findByTitolo(titolo);
            ResultTopic result = new ResultTopic();
            result.setTopics(topicDTO);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }*/
}
