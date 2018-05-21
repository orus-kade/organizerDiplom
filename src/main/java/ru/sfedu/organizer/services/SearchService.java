/*
 */
package ru.sfedu.organizer.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.security.DenyAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.sfedu.organizer.business.AriaBusiness;
import ru.sfedu.organizer.business.SearchBusiness;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.model.AriaModel;
import ru.sfedu.organizer.model.SearchResult;


/**
 *
 * @author sterie
 */
@Stateless
@Path("/")
public class SearchService{
    
    static final Logger logger = LogManager.getLogger(SearchService.class);
    
    @EJB
    private SearchBusiness searchBusiness = new SearchBusiness();
    
//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Aria entity) {
//        super.create(entity);
//    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findAll() {
//        return Response.ok().build();
//    }
    
    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response search(@FormParam("key") String key,
//            @FormParam("params[]") String[] params) {
//        List<String> listParams = new ArrayList<>(Arrays.asList(params));
//        List<SearchResult> list = searchBusiness.search(key, listParams);
//        Gson gson = new Gson();
//        String json = gson.toJson(list); 
//        return Response.status(200).entity(json).build();
//    }
    
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResult test (SearchResult string){
        logger.info(string);     
        return new SearchResult("oo", 4, "tyui");
    }

    
    //@DenyAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        //List<SearchResult> list = ariaBusiness.getByRange(from, to);        
        Gson gson = new Gson();
        String json = gson.toJson(45);        
        return Response.status(200).entity(json).build();
    }

//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//        return String.valueOf(super.count());
//    }
   
}
