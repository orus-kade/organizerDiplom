/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.dao.PlaceDao;
import ru.sfedu.organizer.dao.SingleEventDao;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.model.PlaceModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
public class PlaceBusiness {
    
    private final PlaceDao placeDao = new PlaceDao();
    
    private final HumanDao humanDao = new HumanDao();
    
    private final SingleEventDao singleEventDao = new SingleEventDao();

    /**
     *
     */
    public PlaceBusiness() {
    }
    
    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    public PlaceModel getById(long id) throws ObjectNotFoundException{
        Optional<Place> o = placeDao.getById(id);
        PlaceModel placeModel = null;
        if (o.isPresent()){
            Place place = o.get();
            placeModel = new PlaceModel(place.getId(), place.getTitle(), place.getLocation(), place.getDescription());
            placeModel.addEvents(place.getEvents());
            placeModel.addHumans(place.getPersons());
            placeModel.setLinks(placeDao.getMediaLinks(id));
        }
        else throw new ObjectNotFoundException(ObjectTypes.PLACE, id);
        return placeModel;
    }
    
    /**
     *
     * @param id
     * @throws ObjectNotFoundException
     */
    public void delete(long id) throws ObjectNotFoundException{
        Optional<Place> o = placeDao.getById(id);
        if (o.isPresent()){
            placeDao.delete(o.get());
        }
        else throw new ObjectNotFoundException(ObjectTypes.PLACE, id);
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
            o = placeDao.getAll();
        }
        else{
            o = placeDao.getByRange(from, to);
        }
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Place> list = o.get();
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
        return placeDao.count();
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
        Optional<List> o = placeDao.search(keyNew);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Place> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }
    
    /**
     *
     * @param placeModel
     * @return
     * @throws ObjectNotFoundException
     */
    public long createOrSave (PlaceModel placeModel) throws ObjectNotFoundException{
        Place place;
        if (placeModel.getId() <= 0) {
            place = new Place();
        } else {
            Optional<Place> o = placeDao.getById(placeModel.getId());
            if (!o.isPresent()) {
                throw new ObjectNotFoundException(ObjectTypes.PLACE, placeModel.getId());
            }
            place = o.get();
        }
        place.setDescription(placeModel.getDescription());
        place.setLocation(placeModel.getLocation());
        place.setTitle(placeModel.getTitle());
        if (placeModel.getHumansId()!= null){
                List<Human> humans = new ArrayList<>();
                placeModel.getHumansId().stream().forEach(e -> {
                    Optional<Human> o = humanDao.getById(e);
                    if (o.isPresent())
                        humans.add(o.get());
                });
                place.setPersons(humans);
           }
        if (placeModel.getEvents()!= null){
                List<SingleEvent> list = new ArrayList<>();
                placeModel.getEvents().stream().forEach(e -> {
                    Optional<SingleEvent> o = singleEventDao.getById(e.getId());
                    if (o.isPresent())
                        list.add(o.get());
                });
                place.setEvents(list);
           }
        placeDao.saveOrUpdate(place);
        this.updateLinks(placeModel.getLinks(), place.getId());
        return place.getId();
    }
    
    private void updateLinks(List<MediaLink> list, long id) {
        List<MediaLink> oldList = new ArrayList<>();
        oldList.addAll(placeDao.getMediaLinks(id));
        if (list != null && !list.isEmpty()) {
            list.stream().forEach(e -> {
                e.setObjectId(id);
                e.setObjectType(ObjectTypes.PLACE);
            });
            List<Long> newIds = new ArrayList<>();
            newIds.addAll(list.stream().collect(ArrayList<Long>::new,
                    (a, r) -> a.add(r.getId()),
                    (a1, a2) -> a1.addAll(a2))
            );
            oldList.removeIf(e -> newIds.contains(e.getId()));
            placeDao.saveLinks(list);
        }        
        placeDao.deleteLinks(oldList);        
    }
    
}
