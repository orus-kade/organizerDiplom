/*
 */
package ru.sfedu.organizer.services;

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
import ru.sfedu.organizer.business.OperaBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.model.OperaModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/opera")
public class OperaService{

    @EJB
    private OperaBusiness operaBusiness = new OperaBusiness();

    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(OperaModel operaModel) throws ObjectNotFoundException {
        long id = operaBusiness.createOrSave(operaModel);
        return Response.ok().entity(id).build();
    }

    
    @RolesAllowed("ADMIN")
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) throws ObjectNotFoundException {
        operaBusiness.delete(id);
        return Response.ok().build();
    }

    
    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) throws ObjectNotFoundException {
        OperaModel operaModel = operaBusiness.getById(id);
        return Response.status(200).entity(operaModel).build();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SearchResult> list = operaBusiness.getAll();        
        return Response.status(200).entity(list).build();
    }

    @PermitAll
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SearchResult> list = operaBusiness.getByRange(from, to);        
        return Response.status(200).entity(list).build();
    }

    @PermitAll
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(operaBusiness.count());
    }  
}
