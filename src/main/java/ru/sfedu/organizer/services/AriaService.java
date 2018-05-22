/*
 */
package ru.sfedu.organizer.services;

import com.google.gson.Gson;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.organizer.business.AriaBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.model.AriaModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/aria")
public class AriaService{
    
    static final Logger logger = LogManager.getLogger(AriaService.class);
    
    @EJB
    private  AriaBusiness ariaBusiness = new AriaBusiness();

    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOrSave(AriaModel entity) throws ObjectNotFoundException {
        long id =  ariaBusiness.createOrSave(entity);
        return Response.ok().entity(id).build();
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) throws ObjectNotFoundException {
        ariaBusiness.delete(id);
        return Response.ok().build();
    }

    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) throws ObjectNotFoundException {
        AriaModel ariaModel = ariaBusiness.getById(id);
        return Response.status(200).entity(ariaModel).build();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SearchResult> list = ariaBusiness.getAll();        
        return Response.status(200).entity(list).build();
    }

    @PermitAll
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SearchResult> list = ariaBusiness.getByRange(from, to);        
        return Response.status(200).entity(list).build();
    }

    @PermitAll
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(ariaBusiness.count());
    }  
}
