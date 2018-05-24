
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import ru.sfedu.organizer.entity.Event;

/**
 *
 * @author sterie
 */

public class EventDao extends Dao<Event>{
    
    /**
     *
     */
    public EventDao() {
        super(Event.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Optional<Event> getById(long id){
        return this.get(id);
    }
    
    /**
     *
     * @return
     */
    public int count(){
        return this.countAll();
    }
    
    /**
     *
     * @return
     */
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("title"));
    }
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
    public Optional<List> getByRange(int from, int to){
        return super.getByRange(from, to, Arrays.asList("title"));
    }
}
