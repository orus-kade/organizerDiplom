/*
 */
package ru.sfedu.organizer.services;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.organizer.business.UserBusiness;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.model.NoteModel;
import ru.sfedu.organizer.model.UserModel;

/**
 *
 * @author sterie
 */
@Stateless
@Path("user")
public class UserService{
    
    static final Logger logger = LogManager.getLogger(UserService.class);
    
    @EJB
    private  UserBusiness userBusiness = new UserBusiness();

    /**
     *
     * @param login
     * @param pass
     * @return
     */
    @PermitAll
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("login") String login, @FormParam("pass") String pass){
        UserModel userModel = userBusiness.login(login, pass);
        if (userModel == null) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok().entity(userModel).build();
    }
    
    /**
     *
     * @param login
     * @param pass
     * @return
     */
    @PermitAll
    @POST
    @Path("registration")
    public Response registration(@FormParam("login") String login, @FormParam("pass") String pass){
        if (!userBusiness.registration(login, pass)) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @RolesAllowed("ADMIN")
    @PermitAll
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id){
       userBusiness.delete(id);
       return Response.ok().entity(id).build();
    }

    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("USER")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("userId") Long id) throws ObjectNotFoundException {
        UserModel userModel = userBusiness.getById(id);
        return Response.ok().entity(userModel).build();
    }
    
    /**
     *
     * @param id
     * @param noteModel
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("USER")
    @POST
    @Path("note")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOrSaveNote(@QueryParam("userId") Long id, NoteModel noteModel) throws ObjectNotFoundException{
        userBusiness.createOrSaveNote(noteModel, id);
        return Response.ok().build();
    }
    
    /**
     *
     * @param eventId
     * @param userId
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("USER")
    @GET
    @Path("add/event/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addEvent(@PathParam("id") long eventId, @QueryParam("userId") long userId) throws ObjectNotFoundException{
        userBusiness.addEvent(userId, eventId);
        return Response.ok().build();
    }
    
    /**
     *
     * @param eventId
     * @param userId
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("USER")
    @GET
    @Path("remove/event/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeEvent(@PathParam("id") long eventId, @QueryParam("userId") long userId) throws ObjectNotFoundException{
        userBusiness.removeEvent(userId, eventId);
        return Response.ok().build();
    }
    
    /**
     *
     * @param noteId
     * @param userId
     * @return
     * @throws ObjectNotFoundException
     */
    @RolesAllowed("USER")
    @GET
    @Path("remove/note/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeNote(@PathParam("id") long noteId, @QueryParam("userId") long userId) throws ObjectNotFoundException{
        userBusiness.removeNote(userId, noteId);
        return Response.ok().build();
    }
    
    /**
     *
     * @return
     */
    @RolesAllowed("ADMIN")
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gatAll() {
        List<UserModel> list = userBusiness.detAll(); 
        return Response.ok().entity(list).build();
    }
    
    /**
     *
     * @return
     */
    @RolesAllowed("ADMIN")
    @GET
    @Path("editroles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setRoles() {
        List<UserModel> list = userBusiness.detAll(); 
        return Response.ok().entity(list).build();
    }

    /**
     *
     * @return
     */
    @RolesAllowed("ADMIN")
    @PermitAll
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(userBusiness.count());
    } 
    
}
