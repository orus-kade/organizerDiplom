/*
 */
package ru.sfedu.organizer.services;

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
import ru.sfedu.organizer.business.StageBusiness;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.model.ConcertModel;
import ru.sfedu.organizer.model.StageModel;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/stage")
public class StageController extends AbstractFacade<Stage> {

    @PersistenceContext(unitName = "ru.sfedu_organizer_war_1.0-SNAPSHOTPU") 
    private EntityManager em;
    
    private static final StageBusiness stageBusiness = new StageBusiness();

    public StageController() {
        super(Stage.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Stage entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Stage entity) {
        super.edit(entity);
    }

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
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Stage> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Stage> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
