package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.api.exception.ApplicationException;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.eventi.dto.EventoDTO;
import it.tdgroup.corso.rest.eventi.dto.FilterEventoDTO;
import it.tdgroup.corso.rest.films.FilmService;
import it.tdgroup.corso.rest.films.dto.FilmDTO;
import it.tdgroup.corso.rest.films.dto.FilterFilmDTO;
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

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CommonsLog
public class FilmsApi {
    
    @Inject
    FilmService filmService;
    
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "200",
                            description = "Lista recuperata con successo")})
    @Operation(
            summary = "Endpoint per l'elenco delle risorse",
            description = "Elenco dei film")
    @GET
    public javax.ws.rs.core.Response elencoFilm() throws ApplicationException {
        try {
            log.info("Recupero elenco dei Film");
            List<FilmDTO> films = filmService.elenco();
            ResultEvento resultEvento = new ResultEvento();
            resultEvento.setFilms(films);
            return javax.ws.rs.core.Response.ok(resultEvento).build();
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
                            description = "Film aggiornato con successo")})
    @Operation(summary = "Endpoint per l'aggiornamento di una Film",
            description = "Aggiorna una Film sul sistema dato un id")
    @PUT
    @Path("/{id}")
    public javax.ws.rs.core.Response aggiornaFilm(@PathParam("id") String id, FilmDTO filmDTO) throws ApplicationException {
        try {
            log.info("Aggiorno Film con id:" + id);
            filmService.aggiornaFilm(id, filmDTO);
            return javax.ws.rs.core.Response.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @POST
    @Path("")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Film creato con successo")})
    @Operation(
            summary = "Endpoint per la creazione di una Film",
            description = "Crea un nuovo Film sul sistema")
    public javax.ws.rs.core.Response creaFilm(FilmDTO filmDTO) throws ApplicationException {
        try {
            log.info("sto creando il film");
            String idFilm = filmService.crea(filmDTO);
            log.info("Nuovo Film creato con id:" + idFilm);
            return javax.ws.rs.core.Response.created(URI.create("/films/" + idFilm)).build();
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
                            description = "Film cancellato con successo")})
    @Operation(summary = "Endpoint per la cancellazione di una Film",
            description = "Cancella una Film sul sistema dato un id")
    public javax.ws.rs.core.Response cancellaFilm(@PathParam("id") String idFilm) throws ApplicationException {
        try {
            log.info("Cancella Film con id:" + idFilm);
            filmService.cancella(idFilm);
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
                            description = "Films trovati con successo")})
    @Operation(//serve per descrivere il servizio
            summary = "Endpoint per la ricerca con un filtro di un film o piu films",
            description = "cerco i films sul sistema")
    public javax.ws.rs.core.Response ricercaFilm(FilterFilmDTO filterDTO) throws ApplicationException, ServiceException {

        if (filterDTO != null) {
            List<FilmDTO> films = filmService.find(filterDTO);
            if(films == null)
            {
                return javax.ws.rs.core.Response.serverError().build();
            }
            ResultEvento response = new ResultEvento();
            response.setFilms(films);
            log.info("films trovati");
            return javax.ws.rs.core.Response.ok(response).build();
        }
        return javax.ws.rs.core.Response.serverError().build();
    }
}
