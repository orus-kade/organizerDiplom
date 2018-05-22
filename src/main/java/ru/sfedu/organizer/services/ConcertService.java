/*
 */
package ru.sfedu.organizer.services;

import com.google.gson.Gson;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.organizer.business.ConcertBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.entity.Concert;
import ru.sfedu.organizer.model.ConcertModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/concert")
public class ConcertService{
    
    static final Logger logger = LogManager.getLogger(ConcertService.class);
    
    @EJB
    private ConcertBusiness concertBusiness = new ConcertBusiness();

    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(ConcertModel concertModel) throws ObjectNotFoundException {
        long id = concertBusiness.createOrSave(concertModel);
        return Response.ok().entity(id).build();
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) throws ObjectNotFoundException {
        concertBusiness.delete(id);
        return Response.ok().build();
    }

    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) throws ObjectNotFoundException {
        ConcertModel concertModel = concertBusiness.getById(id);
        return Response.status(200).entity(concertModel).build();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SearchResult> list = concertBusiness.getAll();        
        return Response.status(200).entity(list).build();
    }

    @PermitAll
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SearchResult> list = concertBusiness.getByRange(from, to);        
        return Response.status(200).entity(list).build();
    }

    @PermitAll
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(concertBusiness.count());
    }  
}
