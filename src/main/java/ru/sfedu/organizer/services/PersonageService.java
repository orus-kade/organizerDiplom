/*
 */
package ru.sfedu.organizer.services;

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
import ru.sfedu.organizer.business.PersonageBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
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

    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(PersonageModel personageModel) throws ObjectNotFoundException {
        long id = personageBusiness.createOrSave(personageModel);
        return Response.ok().entity(id).build();
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) throws ObjectNotFoundException {
        personageBusiness.delete(id);
        return Response.ok().build();
    }

    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) throws ObjectNotFoundException {
        PersonageModel personageModel = personageBusiness.getById(id);
        return Response.status(200).entity(personageModel).build();
    }
}
