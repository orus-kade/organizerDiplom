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
import ru.sfedu.organizer.business.HumanBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.model.HumanEvents;
import ru.sfedu.organizer.model.HumanModel;
import ru.sfedu.organizer.model.HumanWorks;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
@Path("human")
public class HumanService{

    @EJB
    private HumanBusiness humanBusiness = new HumanBusiness();

    /**
     *
     * @param humanModel
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(HumanModel humanModel) throws ObjectNotFoundException {
        long id = humanBusiness.createOrSave(humanModel);
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
        humanBusiness.delete(id);
        return Response.ok().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @PermitAll
    @GET
    @Path("events/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvents(@PathParam("id") Long id) {
        HumanEvents humanEvents = humanBusiness.getEventsById(id);
        return Response.status(200).entity(humanEvents).build();
    }
    
    /**
     *
     * @param id
     * @return
     */
    @PermitAll
    @GET
    @Path("works/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorks(@PathParam("id") Long id) {
        HumanWorks humanWorks = humanBusiness.getWorksById(id);
        return Response.status(200).entity(humanWorks).build();
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
        HumanModel humanModel = humanBusiness.getById(id);
        return Response.status(200).entity(humanModel).build();
    }
    
    /**
     *
     * @return
     */
    @PermitAll
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        List<SearchResult> list = humanBusiness.getAll();        
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
        List<SearchResult> list = humanBusiness.getByRange(from, to);        
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
        return String.valueOf(humanBusiness.count());
    }
}
