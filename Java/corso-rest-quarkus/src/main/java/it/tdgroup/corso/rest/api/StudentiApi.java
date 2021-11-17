package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.api.exception.ApplicationException;
import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.studente.FilterDTO;
import it.tdgroup.corso.rest.risorse.studente.StudenteDTO;
import it.tdgroup.corso.rest.risorse.studente.StudenteService;
import lombok.extern.apachecommons.CommonsLog;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.List;

@Path("/studenti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CommonsLog
public class StudentiApi {
    @Inject
    StudenteService studenteService;

    @POST//indica che operazione eseguiamo
    @Path("")//path per indicare l'operazione
    @APIResponses(//che cosa risponde
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Studente creato con successo")})
    @Operation(//serve per descrivere il servizio
            summary = "Endpoint per la creazione di uno studente",
            description = "Crea uno studente sul sistema")
    public javax.ws.rs.core.Response creaStudente(StudenteDTO studenteDTO) throws ApplicationException {
        try {
            var idStudente = studenteService.crea(studenteDTO);
            log.info("Nuovo studente creato con id:" + idStudente);
            return javax.ws.rs.core.Response.created(URI.create("/studenti/" + idStudente)).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "200",
                            description = "Studente recuperata con successo")})
    @Operation(summary = "Endpoint per il recupero di uno studente", description = "recupera uno studente sul sistema in base ad un id")
    @GET
    @Path("/{matricola}")
    public javax.ws.rs.core.Response recuperaStudenteMatricola(@PathParam("matricola") String matricola) throws ApplicationException {
        try {
            log.info("Recupero studente con matricola:" + matricola);
            StudenteDTO studenteDTO = studenteService.findByMatricola(matricola);
            return javax.ws.rs.core.Response.ok(studenteDTO).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @POST//indica che operazione eseguiamo
    @Path("/ricerca")//path per indicare l'operazione
    @APIResponses(//che cosa risponde
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "200",
                            description = "Studenti trovati con successo")})
    @Operation(//serve per descrivere il servizio
            summary = "Endpoint per la ricerca con un filtro di uno studente o piu studenti",
            description = "cerco degli studenti sul sistema")
    public javax.ws.rs.core.Response ricercaStudente(FilterDTO filterDTO) throws ApplicationException, ServiceException {

        if (filterDTO != null) {
            log.info("sto facendo la ricerca filtrata");
            List<StudenteDTO> studenteDTOList = studenteService.find(filterDTO);
            Response response = new Response();
            response.setStudenteDTOList(studenteDTOList);
            log.info("studenti trovati");
            return javax.ws.rs.core.Response.ok(response).build();
        }
        return javax.ws.rs.core.Response.serverError().build();
    }

    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "200",
                            description = "Studente recuperata con successo")})
    @Operation(summary = "Endpoint per il recupero di tutti gli studenti", description = "recupera tutti gli studenti")
    @GET
    @Path("")
    public javax.ws.rs.core.Response recuperaStudenti() throws ApplicationException {
        try {
            log.info("Recupero studenti");
            List<StudenteDTO> studenteDTO = studenteService.elenco();
            Response response = new Response();
            response.setStudenteDTOList(studenteDTO);
            return javax.ws.rs.core.Response.ok(response).build();
        } catch (ServiceException | MapperException ex) {
            throw new ApplicationException(ex);
        }
    }
}
