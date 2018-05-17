/*
 */
package ru.sfedu.organizer.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.Optional;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.sfedu.organizer.business.AriaBusiness;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.model.AriaModel;
import ru.sfedu.organizer.utils.HibernateUtil;

/**
 *
 * @author sterie
 */
@Stateless
@Path("/aria")
public class AriaConrtoller extends AbstractFacade<Aria> {
    
    static final Logger logger = LogManager.getLogger(AriaConrtoller.class);
    private static final AriaBusiness ariaBusiness = new AriaBusiness();

    @PersistenceContext(unitName = "ru.sfedu_organizer_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AriaConrtoller() {
        super(Aria.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Aria entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Aria entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        ariaBusiness.delete(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
////      return super.find(id);
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        logger.debug("olo");
//        AriaDao ariaDao = new AriaDao(session);
//        logger.debug("olo2");
//        Optional<Aria> o = ariaDao.getById(id);
//        logger.debug(o.isPresent());
//        Aria aria = o.get();    
//        logger.debug(aria);
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//        String json = gson.toJson(aria);  
//        //return aria;
//        session.close();
        AriaModel ariaModel = ariaBusiness.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(ariaModel); 
        return Response.status(200).entity(json).build();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Aria> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Aria> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
