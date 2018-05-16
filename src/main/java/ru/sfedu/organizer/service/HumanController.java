/*
 */
package ru.sfedu.organizer.service;

import com.google.gson.Gson;
import java.util.List;
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
import ru.sfedu.organizer.business.HumanBusiness;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.model.HumanModel;
import ru.sfedu.organizer.model.HumanWorks;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/human")
public class HumanController extends AbstractFacade<Human> {

    @PersistenceContext(unitName = "ru.sfedu_organizer_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    private static final HumanBusiness humanBusiness = new HumanBusiness();

    public HumanController() {
        super(Human.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Human entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Human entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/works/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorks(@PathParam("id") Long id) {
        HumanWorks humanWorks = humanBusiness.getWorksById(id);
        Gson gson = new Gson();
        String json = gson.toJson(humanWorks); 
        return Response.status(200).entity(json).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        HumanModel humanModel = humanBusiness.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(humanModel); 
        return Response.status(200).entity(json).build();
    }
    
    
    
//    @GET
//    @Path("/{id}/events")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getEvents(@PathParam("id") Long id) {
//        HumanModel humanModel = humanBusiness.getById(id);
//        Gson gson = new Gson();
//        String json = gson.toJson(humanModel); 
//        return Response.status(200).entity(json).build();
//    }
    
    

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Human> findAll() {
        return super.findAll();
    }

    @GET
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Path("/99999{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Human> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
