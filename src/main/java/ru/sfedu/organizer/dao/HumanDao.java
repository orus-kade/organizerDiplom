
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.entity.Event;
import ru.sfedu.organizer.entity.Human;

/**
 *
 * @author sterie
 */
public class HumanDao extends Dao<Human>{
    
    public HumanDao(Session session) {
        super(Human.class);
    }
    
    public Optional<Human> getById(long id){
        return this.get(id);
    }
    
    public int count(){
        return this.countAll();
    }
    
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("surname", "name", "patronymic"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(page, Arrays.asList("surname", "name", "patronymic"));
    }
}
