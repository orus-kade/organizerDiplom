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
import ru.sfedu.organizer.business.SingleEventBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.model.SingleEventInfo;
import ru.sfedu.organizer.model.SingleEventModel;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/singleevent")
public class SingleEventService{
    
    @EJB
    private SingleEventBusiness business = new SingleEventBusiness();

    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response create(String json) throws ObjectNotFoundException {
        SingleEventModel eventModel = new Gson().fromJson(json, SingleEventModel.class);
        long id = business.createOrSave(eventModel);
        return Response.ok().entity(id).build();
    }


    @RolesAllowed("ADMIN")
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        business.delete(id);
        return Response.ok().build();
    }

    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        SingleEventModel singleEventModel = business.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(singleEventModel);
        return Response.status(200).entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SingleEventInfo> list = business.getAll();        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }

    @PermitAll
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SingleEventInfo> list = business.getByRange(from, to);        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }
    
    @PermitAll
    @GET
    @Path("future/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRangeFuture(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SingleEventInfo> list = business.getByRangeFuture(from, to);        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }
    
    @PermitAll
    @GET
    @Path("future")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAllFuture() {
        List<SingleEventInfo> list = business.getAllFuture();        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }

    @PermitAll
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(business.count());
    }
    
    @PermitAll
    @GET
    @Path("future/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countFutureREST() {
        return String.valueOf(business.count());
    }

}
