/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.dao.ConcertDao;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Concert;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.ConcertModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
public class ConcertBusiness {
    
    private static final ConcertDao concertDao = new ConcertDao();
    
    private AriaDao ariaDao = new AriaDao(); 
    
    private HumanDao humanDao = new HumanDao();
    
    @EJB 
    private HumanBusiness humanBusiness = new HumanBusiness();

    public ConcertBusiness() {
    }
    
    public ConcertModel getById(long id){
        Optional<Concert> o = concertDao.getById(id);
        ConcertModel consertModel = null;
        if (o.isPresent()){
            Concert concert = o.get();
            consertModel = new ConcertModel(concert.getId(), concert.getTitle(), concert.getDescription());   
            consertModel.addDirector(concert.getDirector());
            consertModel.addSingers(concert.getSingers());
            consertModel.addAries(concert.getAries());
            consertModel.addEvents(concert.getSingleEvents());
        }
        return consertModel;
    }
    
    public void delete(long id){
        Optional<Concert> o = concertDao.getById(id);
        if (o.isPresent()){
            concertDao.delete(o.get());
        }
    } 
    
    public List<SearchResult> getByRange(int from, int to){
        Optional<List> o;
        if (from == 0 && to == 0){
            o = concertDao.getAll();
        }
        else{
            o = concertDao.getByRange(from, to);
        }
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Concert> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }
    
    public List<SearchResult> getAll(){
        return this.getByRange(0, 0);
    }
    
    public int count(){
        return concertDao.count();
    }
    
    public List<SearchResult> search(String key){
        if (key == null || key.trim().length()==0){
            return this.getAll();
        }
        String keyNew = key.trim().toLowerCase();
        Optional<List> o = concertDao.search(keyNew);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Concert> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }    
    
    public long createOrSave (ConcertModel concertModel) throws ObjectNotFoundException{
        Concert concert;
        if (concertModel.getId() <= 0)
            concert = new Concert();
        else{
            Optional<Concert> o = concertDao.getById(concertModel.getId());
            if (!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.CONSERT, concertModel.getId());
            concert = o.get();
        }
        concert.setDescription(concertModel.getDescription());
        concert.setTitle(concertModel.getTitle());
        if (concertModel.getAries() != null || !concertModel.getAries().isEmpty()){
            List<Aria> list = new ArrayList();
            concertModel.getAries().stream().forEach(e -> {
                Optional<Aria> o = ariaDao.getById(e.getId());
                if (o.isPresent()){
                    list.add(o.get());
                }
            });
            concert.setAries(list);
        }
        if (concertModel.getSingersIds()!= null){
                List<Human> singers = new ArrayList<>();
                concertModel.getSingersIds().stream().forEach(e -> {
                    Optional<Human> o = humanDao.getById(e);
                    if (o.isPresent())
                        singers.add(o.get());
                });
                singers.removeIf(e -> !humanBusiness.isHumanProfession(e, Professions.SINGER));
                concert.setSingers(singers);
           }
        if (concertModel.getDirectorId() > 0){
            Optional<Human> o = humanDao.getById(concertModel.getDirectorId());
            if (o.isPresent())
                concert.setDirector(o.get());
        }        
        concertDao.saveOrUpdate(concert);
        return concert.getId();
    }
}
