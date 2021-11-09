package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.api.exception.ApplicationException;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.eventi.EventoService;
import it.tdgroup.corso.rest.eventi.dto.EventoDTO;
import it.tdgroup.corso.rest.eventi.dto.FilterEventoDTO;
import it.tdgroup.corso.rest.eventi.dto.StatoDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/eventi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CommonsLog
public class EventiApi {
    @Inject
    EventoService eventoService;

    @POST
    @Path("/")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Evento creato con successo")})
    @Operation(
            summary = "Endpoint per la creazione di un vento",
            description = "Crea una nuovo evento sul sistema")
    public javax.ws.rs.core.Response creaEvento(EventoDTO eventoDTO) throws ApplicationException {
        try {
            eventoDTO.setStato(StatoDTO.IN_LAVORAZIONE.getDisplayName());//inizialmente l'evento è in bozza
            String idEvento = eventoService.crea(eventoDTO);
            log.info("Nuovo evento creato con id:" + idEvento);
            return javax.ws.rs.core.Response.created(URI.create("/eventi/" + idEvento)).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @DELETE
    @Path("/{id}")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "204",
                            description = "Evento cancellato con successo")})
    @Operation(summary = "Endpoint per la cancellazione di un evento",
            description = "Cancella un evento sul sistema dato un id")
    public javax.ws.rs.core.Response cancellaEvento(@PathParam("id") String idEvento) throws ApplicationException {
        try {
            log.info("Cancella evento con id:" + idEvento);
            eventoService.cancella(idEvento);
            return Response.noContent().build();
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
                            description = "Lista recuperata con successo")})
    @Operation(
            summary = "Endpoint per l'elenco degli eventi",
            description = "Elenco degli eventi")
    @GET
    public Response elencoEventi() throws ApplicationException {
        try {
            log.info("Recupero elenco degli eventi");
            List<EventoDTO> eventi = eventoService.elenco();
            ResultEvento response = new ResultEvento();
            response.setEventoDTOList(eventi);
            return Response.ok(response).build();
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
                            responseCode = "204",
                            description = "Evento aggiornato con successo")})
    @Operation(summary = "Endpoint per l'aggiornamento di un evento",
            description = "Aggiorna un evento sul sistema dato un id")
    @PUT
    @Path("/{id}")
    public Response aggiornaEvento(@PathParam("id") String id, EventoDTO eventoDTO) throws ApplicationException {
        try {
            log.info("Aggiorno evento con id:" + id);
            eventoService.aggiornaRisorsa(id, eventoDTO);
            return Response.noContent().build();
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
                            description = "Eventi trovati con successo")})
    @Operation(//serve per descrivere il servizio
            summary = "Endpoint per la ricerca con un filtro di un evento o piu eventi",
            description = "cerco degli eventi sul sistema")
    public javax.ws.rs.core.Response ricercaEvento(FilterEventoDTO filterDTO) throws ApplicationException, ServiceException {

        if (filterDTO != null) {
            List<EventoDTO> eventoDTOList = eventoService.find(filterDTO);
            if(eventoDTOList == null)
            {
                return javax.ws.rs.core.Response.serverError().build();
            }
            ResultEvento response = new ResultEvento();
            response.setEventoDTOList(eventoDTOList);
            log.info("eventi trovati");
            return javax.ws.rs.core.Response.ok(response).build();
        }
        return javax.ws.rs.core.Response.serverError().build();
    }
}
