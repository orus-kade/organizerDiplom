/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.sfedu.organizer.dao.PlaceDao;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.model.PlaceModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
public class PlaceBusiness {
    
    private static final PlaceDao placeDao = new PlaceDao();

    public PlaceBusiness() {
    }
    
    public PlaceModel getById(long id){
        Optional<Place> o = placeDao.getById(id);
        PlaceModel placeModel = null;
        if (o.isPresent()){
            Place place = o.get();
            placeModel = new PlaceModel(place.getId(), place.getTitle(), place.getLocation(), place.getDescription());
            placeModel.addEvents(place.getEvents());
            placeModel.addHumans(place.getPersons());
        }
        return placeModel;
    }
    
    public void delete(long id){
        Optional<Place> o = placeDao.getById(id);
        if (o.isPresent()){
            placeDao.delete(o.get());
        }
    }
    
    public List<SearchResult> getByRange(int from, int to){
        Optional<List> o = placeDao.getByRange(from, to);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Place> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }
    
    public List<SearchResult> getAll(){
        return this.getByRange(1, this.count());
    }
    
    public int count(){
        return placeDao.count();
    }
}
