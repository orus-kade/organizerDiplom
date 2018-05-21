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
import ru.sfedu.organizer.business.PersonageBusiness;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.model.PersonageModel;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/personage")
public class PersonageService{
    
    @EJB
    private PersonageBusiness personageBusiness = new PersonageBusiness();

//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Personage entity) {
//        super.create(entity);
//    }

//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Long id, Personage entity) {
//        super.edit(entity);
//    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        personageBusiness.delete(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces( MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        PersonageModel personageModel = personageBusiness.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(personageModel); 
        return Response.status(200).entity(json).build();
    }

//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//        return String.valueOf(super.count());
//    } 
}
