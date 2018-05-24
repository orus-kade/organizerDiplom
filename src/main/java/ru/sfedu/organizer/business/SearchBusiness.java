/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
public class SearchBusiness {
    
    @EJB
    private AriaBusiness ariaBusiness = new AriaBusiness();
    
    @EJB
    private HumanBusiness humanBusiness = new HumanBusiness();
    
    @EJB
    private OperaBusiness operaBusiness = new OperaBusiness();
    
    @EJB
    private PersonageBusiness personageBusiness = new PersonageBusiness();
    
    @EJB
    private PlaceBusiness placeBusiness = new PlaceBusiness();
    
    @EJB
    private ConcertBusiness concertBusiness = new ConcertBusiness();
    
    @EJB
    private StageBusiness stageBusiness = new StageBusiness();
    
    /**
     *
     * @param key
     * @param params
     * @return
     */
    public List<SearchResult> search(String key, List<String> params){
        List<SearchResult> results = new ArrayList<>();
        if (params.contains("aria")) results.addAll(ariaBusiness.search(key));
        if (params.contains("opera")) results.addAll(operaBusiness.search(key));
        if (params.contains("personage")) results.addAll(personageBusiness.search(key));
        if (params.contains("place")) results.addAll(placeBusiness.search(key));
        if (params.contains("stage")) results.addAll(stageBusiness.search(key));
        if (params.contains("concert")) results.addAll(concertBusiness.search(key));
        List<Professions> allProfs = Arrays.asList(Professions.values());
        List<Professions> searchProfs = new ArrayList<>();
        allProfs.stream().forEach(e ->{
            if (params.contains(e.toString().toLowerCase()))
                searchProfs.add(e);
        });
        if (!searchProfs.isEmpty())
            results.addAll(humanBusiness.search(key, searchProfs));
        if (!results.isEmpty()) results.sort(Comparator.comparing(SearchResult::getName));
        return results;
    }    
}
