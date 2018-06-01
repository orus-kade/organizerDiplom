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
import ru.sfedu.organizer.entity.MediaLink;
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
    
    private final ConcertDao concertDao = new ConcertDao();
    
    private final AriaDao ariaDao = new AriaDao(); 
    
    private final HumanDao humanDao = new HumanDao();
    
    @EJB 
    private final HumanBusiness humanBusiness = new HumanBusiness();

    /**
     *
     */
    public ConcertBusiness() {
    }
    
    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    public ConcertModel getById(long id) throws ObjectNotFoundException{
        Optional<Concert> o = concertDao.getById(id);
        ConcertModel concertModel = null;
        if (o.isPresent()){
            Concert concert = o.get();
            concertModel = new ConcertModel(concert.getId(), concert.getTitle(), concert.getDescription());   
            concertModel.addDirector(concert.getDirector());
            concertModel.addSingers(concert.getSingers());
            concertModel.addAries(concert.getAries());
            concertModel.addEvents(concert.getSingleEvents());
            concertModel.setLinks(concertDao.getMediaLinks(id));
        }
        else throw new ObjectNotFoundException(ObjectTypes.CONCERT, id);
        return concertModel;
    }
    
    /**
     *
     * @param id
     * @throws ObjectNotFoundException
     */
    public void delete(long id) throws ObjectNotFoundException{
        Optional<Concert> o = concertDao.getById(id);
        if (o.isPresent()){
            concertDao.delete(o.get());
        }
        else throw new ObjectNotFoundException(ObjectTypes.CONCERT, id);
    } 
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
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
    
    /**
     *
     * @return
     */
    public List<SearchResult> getAll(){
        return this.getByRange(0, 0);
    }
    
    /**
     *
     * @return
     */
    public int count(){
        return concertDao.count();
    }
    
    /**
     *
     * @param key
     * @return
     */
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
    
    /**
     *
     * @param concertModel
     * @return
     * @throws ObjectNotFoundException
     */
    public long createOrSave (ConcertModel concertModel) throws ObjectNotFoundException{
        Concert concert;
        if (concertModel.getId() <= 0)
            concert = new Concert();
        else{
            Optional<Concert> o = concertDao.getById(concertModel.getId());
            if (!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.CONCERT, concertModel.getId());
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
            if (o.isPresent()){
                Human h = o.get();
                if (humanBusiness.isHumanProfession(h, Professions.DIRECTOR))
                    concert.setDirector(h);
            }
        }        
        concertDao.saveOrUpdate(concert);
        this.updateLinks(concertModel.getLinks(), concert.getId());
        return concert.getId();
    }

    private void updateLinks(List<MediaLink> list, long id) {
        List<MediaLink> oldList = new ArrayList<>();
        oldList.addAll(concertDao.getMediaLinks(id));
        if (list != null && !list.isEmpty()) {
            list.stream().forEach(e -> {
                e.setObjectId(id);
                e.setObjectType(ObjectTypes.CONCERT);
            });
            List<Long> newIds = new ArrayList<>();
            newIds.addAll(list.stream().collect(ArrayList<Long>::new,
                    (a, r) -> a.add(r.getId()),
                    (a1, a2) -> a1.addAll(a2))
            );
            oldList.removeIf(e -> newIds.contains(e.getId()));
            concertDao.saveLinks(list);
        }        
        concertDao.deleteLinks(oldList);        
    }
}
