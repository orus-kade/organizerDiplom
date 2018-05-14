
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.entity.Concert;

/**
 *
 * @author sterie
 */
public class ConcertDao extends Dao<Concert>{
    
    public ConcertDao(Session session) {
        super(session);
    }
    
    public Optional<Concert> getById(long id){
        return this.get(id, Concert.class);
    }
    
    public int count(){
        return this.countAll(Concert.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(Concert.class, Arrays.asList("title"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(Concert.class, page, Arrays.asList("title"));
    }
    
}
