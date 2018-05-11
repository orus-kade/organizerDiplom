
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.model.Aria;
import ru.sfedu.organizer.model.Opera;

/**
 *
 * @author sterie
 */
public class OperaDao extends Dao<Opera>{
    
    public OperaDao(Session session) {
        super(session);
    }
    
    public Optional<Opera> getById(long id){
        return this.get(id, Opera.class);
    }
    
    public int count(){
        return this.countAll(Opera.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(Opera.class, Arrays.asList("title"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(Opera.class, page, Arrays.asList("title"));
    }
}
