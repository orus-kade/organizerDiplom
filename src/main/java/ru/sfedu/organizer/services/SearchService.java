/*
 */
package ru.sfedu.organizer.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.organizer.business.SearchBusiness;
import ru.sfedu.organizer.model.SearchResult;


/**
 *
 * @author sterie
 */
@Stateless
@Path("")
public class SearchService{
    
    static final Logger logger = LogManager.getLogger(SearchService.class);
    
    @EJB
    private SearchBusiness searchBusiness = new SearchBusiness();
   
    /**
     *
     * @param key
     * @param params
     * @return
     */
    @PermitAll
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@FormParam("key") String key,
            @FormParam("params[]") String[] params) {
        List<String> listParams = new ArrayList<>(Arrays.asList(params));
        List<SearchResult> list = searchBusiness.search(key, listParams);
        return Response.status(200).entity(list).build();
    } 
}
