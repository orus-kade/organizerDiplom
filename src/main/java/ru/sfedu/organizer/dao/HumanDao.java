
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
        super(session);
    }
    
    public Optional<Human> getById(long id){
        return this.get(id, Human.class);
    }
    
    public int count(){
        return this.countAll(Human.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(Human.class, Arrays.asList("surname", "name", "patronymic"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(Human.class, page, Arrays.asList("surname", "name", "patronymic"));
    }
}
