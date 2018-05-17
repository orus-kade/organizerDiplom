/*
 */
package ru.sfedu.organizer.business;

import java.util.Optional;
import ru.sfedu.organizer.dao.PlaceDao;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.model.PlaceModel;

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
}
