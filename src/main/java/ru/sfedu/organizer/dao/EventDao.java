
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.model.Concert;
import ru.sfedu.organizer.model.Event;

/**
 *
 * @author sterie
 */
public class EventDao extends Dao<Event>{
    
    public EventDao(Session session) {
        super(session);
    }
    
    public Optional<Event> getById(long id){
        return this.get(id, Event.class);
    }
    
    public int count(){
        return this.countAll(Event.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(Event.class, Arrays.asList("title"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(Event.class, page, Arrays.asList("title"));
    }
}
