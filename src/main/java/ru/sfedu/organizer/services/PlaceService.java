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
import ru.sfedu.organizer.business.PlaceBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.model.PlaceModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */

@Stateless
@Path("place")
public class PlaceService{
    
    @EJB
    private PlaceBusiness placeBusiness = new PlaceBusiness();

    /**
     *
     * @param placeModel
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(PlaceModel placeModel) throws ObjectNotFoundException {
        long id = placeBusiness.createOrSave(placeModel);
        return Response.ok().build();
    }

    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("ADMIN")
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) throws ObjectNotFoundException{
        placeBusiness.delete(id);
        return Response.ok().build();
    }

    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    @PermitAll
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) throws ObjectNotFoundException {
        PlaceModel placeModel = placeBusiness.getById(id);
        return Response.status(200).entity(placeModel).build();
    }

    /**
     *
     * @return
     */
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SearchResult> list = placeBusiness.getAll();        
        return Response.status(200).entity(list).build();
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @PermitAll
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SearchResult> list = placeBusiness.getByRange(from, to);        
        return Response.status(200).entity(list).build();
    }

    /**
     *
     * @return
     */
    @PermitAll
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(placeBusiness.count());
    }  
}
