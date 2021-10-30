package it.tdgroup.corso.rest.api;

import io.swagger.annotations.*;
import it.tdgroup.corso.rest.api.BaseApi;
import it.tdgroup.corso.rest.exception.ApplicationException;
import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.risorse.esame.EsameDTO;
import it.tdgroup.corso.rest.risorse.esame.EsameService;
import it.tdgroup.corso.rest.studente.StudenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.reflections.Reflections.log;

@Api(value = "Servizi per la gestione di un esame", tags = "Esame")
@RestController
@RequestMapping({"${app.context-path}/esami"})
public class EsamiApi extends BaseApi {

    @Autowired
    EsameService esameService;

    @CrossOrigin
    @ApiOperation(value = "Creo Esame", produces = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Inserimento corretto")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity creaEsame(@RequestBody EsameDTO esameDTO) throws ApplicationException {
        try {
            String idEsame = esameService.Post(esameDTO);
            log.info("Nuovo esame creato con id:" + idEsame);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isEsame}").buildAndExpand(idEsame).toUri();
            return ResponseEntity.created(location).build();
        } catch (MapperException | ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Aggiorna un Esame", consumes = MediaType.APPLICATION_JSON_VALUE, response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Aggiornamento avvenuto con successo")})
    @PutMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@ApiParam(name = "esameDenominazione", value = "denominazioneEsame", required = true)
                                 @RequestParam("denominazione") String denominazione, @RequestBody EsameDTO esameDTO) throws ApplicationException {
        try {
            log.info("Aggiorno l'esame con denominazione:" + denominazione);
            esameService.Put(denominazione,esameDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }
    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella un esame per denominazione", notes = "Cancella una specifico esame data la denominazione")
    @DeleteMapping(value = "")
    public ResponseEntity cancellaDenominazione(@RequestParam("denominazione") String denominazione) throws ApplicationException {
        try {
            log.info("Cancella Esame con denominazione:" + denominazione);
            esameService.Delete(denominazione);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recupero tutti gli esami", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero tutti gli esami avvenuto con successo")})
    @GetMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaEsami() throws ApplicationException {
        try {
            log.info("Recupero esami");
            List<EsameDTO> esameDTO = esameService.findAll();
            Result result = new Result();
            result.setEsami(esameDTO);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }
}
