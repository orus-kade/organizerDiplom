
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Opera;

/**
 *
 * @author sterie
 */
public class OperaDao extends Dao<Opera>{
    
    public OperaDao(Session session) {
        super(Opera.class);
    }
    
    public Optional<Opera> getById(long id){
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
