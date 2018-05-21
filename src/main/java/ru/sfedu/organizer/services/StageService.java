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
import ru.sfedu.organizer.business.StageBusiness;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.model.ConcertModel;
import ru.sfedu.organizer.model.SearchResult;
import ru.sfedu.organizer.model.StageModel;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/stage")
public class StageService{

    @EJB
    private StageBusiness stageBusiness = new StageBusiness();

//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Stage entity) {
//        super.create(entity);
//    }

//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Long id, Stage entity) {
//        super.edit(entity);
//    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Long id) {
        stageBusiness.delete(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        StageModel stageModel = stageBusiness.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(stageModel);
        return Response.status(200).entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SearchResult> list = stageBusiness.getAll();        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<SearchResult> list = stageBusiness.getByRange(from, to);        
        Gson gson = new Gson();
        String json = gson.toJson(list); 
        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(stageBusiness.count());
    }  
}
