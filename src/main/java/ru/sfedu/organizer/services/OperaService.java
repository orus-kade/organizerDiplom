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
import ru.sfedu.organizer.business.OperaBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.entity.Opera;
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

    @PermitAll
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(String json) throws ObjectNotFoundException {
        OperaModel operaModel = new Gson().fromJson(json, OperaModel.class);
        long id = operaBusiness.createOrSave(operaModel);
        return Response.ok().entity(id).build();
    }

    
    @RolesAllowed("ADMIN")
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        operaBusiness.delete(id);
        return Response.ok().build();
    }

    
    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        OperaModel operaModel = operaBusiness.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(operaModel); 
        return Response.status(200).entity(json).build();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SearchResult> list = operaBusiness.getAll();        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }

    @PermitAll
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SearchResult> list = operaBusiness.getByRange(from, to);        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }

    @PermitAll
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(operaBusiness.count());
    }  
}
