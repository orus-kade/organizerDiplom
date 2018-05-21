/*
 */
package ru.sfedu.organizer.services;

import com.google.gson.Gson;
import java.util.List;
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
import ru.sfedu.organizer.business.SingleEventBusiness;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.model.SearchResult;
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

//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(SingleEvent entity) {
//        super.create(entity);
//    }

//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Long id, SingleEvent entity) {
//        super.edit(entity);
//    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        business.delete(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        SingleEventModel singleEventModel = business.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(singleEventModel);
        return Response.status(200).entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SingleEventInfo> list = business.getAll();        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SingleEventInfo> list = business.getByRange(from, to);        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }
    
    @GET
    @Path("future/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRangeFuture(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SingleEventInfo> list = business.getByRangeFuture(from, to);        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }
    
    @GET
    @Path("future")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAllFuture() {
        List<SingleEventInfo> list = business.getAllFuture();        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(business.count());
    }
    
    @GET
    @Path("future/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countFutureREST() {
        return String.valueOf(business.count());
    }

}
