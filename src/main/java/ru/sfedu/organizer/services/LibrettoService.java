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
import ru.sfedu.organizer.business.LibrettoBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.model.HumanModel;
import ru.sfedu.organizer.model.LibrettoModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/libretto")
public class LibrettoService{
    
    @EJB
    private LibrettoBusiness librettoBusiness = new LibrettoBusiness();
    
    @RolesAllowed("ADMIN")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response create(String json) throws ObjectNotFoundException {
        LibrettoModel librettoModel = new Gson().fromJson(json, LibrettoModel.class);
        long id = librettoBusiness.createOrSave(librettoModel);
        return Response.ok().entity(id).build();
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        librettoBusiness.delete(id);
        return Response.ok().build();
    }

    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        LibrettoModel librettoModel = librettoBusiness.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(librettoModel); 
        return Response.status(200).entity(json).build();
    }
    
}
