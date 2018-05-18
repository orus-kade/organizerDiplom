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
import ru.sfedu.organizer.business.LibrettoBusiness;
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
public class LibrettoController{

    private static final LibrettoBusiness librettoBusiness = new LibrettoBusiness();
    
//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Libretto entity) {
//        super.create(entity);
//    }

//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Long id, Libretto entity) {
//        super.edit(entity);
//    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        librettoBusiness.delete(id);
        return Response.ok().build();
    }

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
