
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.entity.Concert;
import ru.sfedu.organizer.entity.Event;

/**
 *
 * @author sterie
 */
public class EventDao extends Dao<Event>{
    
    public EventDao(Session session) {
        super(Event.class);
    }
    
    public Optional<Event> getById(long id){
        return this.get(id);
    }
    
    public int count(){
        return this.countAll();
    }
    
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("title"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(page, Arrays.asList("title"));
    }
}
