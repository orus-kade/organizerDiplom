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
import ru.sfedu.organizer.business.StageBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.model.SearchResult;
import ru.sfedu.organizer.model.StageModel;

/**
 *
 * @author sterie
 */
@Stateless
@Path("stage")
public class StageService{

    @EJB
    private StageBusiness stageBusiness = new StageBusiness();

    /**
     *
     * @param stageModel
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(StageModel stageModel) throws ObjectNotFoundException {
        long id = stageBusiness.createOrSave(stageModel);
        return Response.ok().entity(id).build();
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
    public Response remove(@PathParam("id") Long id) throws ObjectNotFoundException {
        stageBusiness.delete(id);
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
        StageModel stageModel = stageBusiness.getById(id);
        return Response.status(200).entity(stageModel).build();
    }

    /**
     *
     * @return
     */
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<SearchResult> list = stageBusiness.getAll();        
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
        List<SearchResult> list = stageBusiness.getByRange(from, to);        
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
        return String.valueOf(stageBusiness.count());
    }  
}
