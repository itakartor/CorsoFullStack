package it.tdgroup.corso.rest.api;

import io.swagger.annotations.*;
import it.tdgroup.corso.rest.exception.ApplicationException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.RisorseService;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

import static org.reflections.Reflections.log;

@Api(value = "Servizi per la gestione di una risorsa", tags = "Risorse")
@RestController
@RequestMapping({"${app.context-path}/risorse"})

public class RisorseApi extends BaseApi {
    @Autowired//mi va a cercare l'unico risorsa service come se la collegasse senza inizializzarla
            //se non specifico il tag @Qualifi("nomerisorsa") vuol dire che c'è una sola risorsa
    RisorseService risorseService;

    @CrossOrigin
    @ApiOperation(value = "Lista risorse", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ricerca avvenuta con successo")})
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity elencoRisorse() throws ApplicationException {
        try {
            log.info("Recupero elenco delle risorse");
            List<RisorsaDTO> risorse = risorseService.elenco();
            return ResponseEntity.ok(risorse);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Aggiorna una risorsa", consumes = MediaType.APPLICATION_JSON_VALUE, response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Aggiornamento avvenuto con successo")})
    @PutMapping(value = {"/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity aggiornaRisorsa(@ApiParam(name = "idRisorsa", value = "idRisorsa", required = true)
                                          @PathVariable("id") String id, @RequestBody RisorsaDTO risorsaDTO) throws ApplicationException {
        try {
            log.info("Aggiorno risorsa con id:" + id);
            risorseService.aggiornaRisorsa(id, risorsaDTO);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recupero risorsa", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero risorsa avvenuto con successo")})
    @GetMapping(value = {"/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaRisorsa(@PathVariable("id") String id) throws ApplicationException {
        try {
            log.info("Recupero risorsa con id:" + id);
            RisorsaDTO risorsaDTO = risorseService.findById(id);//findById(id)
            return ResponseEntity.ok(risorsaDTO);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "prendo username", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero risorsa avvenuto con successo per username")})
    @GetMapping(value = "username/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaRisorsaUsername(@PathVariable("username") String username) throws ApplicationException {
        try {
            log.info("Recupero risorsa con username:" + username);
            RisorsaDTO risorse = risorseService.findRisorsaByUsername(username);//findById(id)
            return ResponseEntity.ok(risorse);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recupera lastName", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero risorsa avvenuto con successo per username")})
    @GetMapping(value = {"/BylastName"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RisorsaDTO>> recuperaRisorsaLastName(@RequestParam("lastName") String lastName) throws ApplicationException {
        try {
            log.info("Recupero risorsa con lastName:" + lastName);
            List<RisorsaDTO> risorse = risorseService.findByLastName(lastName);
            return ResponseEntity.ok(risorse);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recupera lastName", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero risorsa avvenuto con successo per username")})
    @GetMapping(value = {"/BylastNameOrderBy"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RisorsaDTO>> recuperaRisorsaLastNameOrderBy(@RequestParam("lastName") String lastName) throws ApplicationException {
        try {
            log.info("Recupero risorsa con lastName:" + lastName);
            List<RisorsaDTO> risorse = risorseService.findByLastNameEndingWithOrderByFirstName(lastName);
            return ResponseEntity.ok(risorse);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Crea una nuova risorsa", consumes = MediaType.APPLICATION_JSON_VALUE, response = RisorsaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creazione risorsa avvenuta con successo")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity creaRisorsa(@RequestBody RisorsaDTO risorsaDTO) throws ApplicationException {
        try {
            String idRisorsa = risorseService.crea(risorsaDTO);
            log.info("Nuova risorsa creata con id:" + idRisorsa);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isRisorsa}").buildAndExpand(idRisorsa).toUri();
            return ResponseEntity.created(location).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella una risorsa per id", notes = "Cancella una specifica risorsa dato un id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancellaRisorsa(@PathVariable("id") String idRisorsa) throws ApplicationException {
        try {
            log.info("Cancella risorsa con id:" + idRisorsa);
            risorseService.cancella(idRisorsa);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}
