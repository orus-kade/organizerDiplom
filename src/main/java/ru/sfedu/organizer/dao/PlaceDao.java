package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.model.Aria;
import ru.sfedu.organizer.model.Place;

/**
 *
 * @author sterie
 */
public class PlaceDao extends Dao<Place>{
    
    public PlaceDao(Session session) {
        super(session);
    }
    
    public Optional<Place> getById(long id){
        return this.get(id, Place.class);
    }
    
    public int count(){
        return this.countAll(Place.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(Place.class, Arrays.asList("title"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(Place.class, page, Arrays.asList("title"));
    }
}
