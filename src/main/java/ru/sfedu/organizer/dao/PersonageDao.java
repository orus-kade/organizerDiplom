package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.model.*;

/**
 *
 * @author sterie
 */
public class PersonageDao extends Dao<Personage>{
    
    public PersonageDao(Session session) {
        super(session);
    }
    
    public Optional<Personage> getById(long id){
        return this.get(id, Personage.class);
    }
    
    public int count(){
        return this.countAll(Personage.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(Personage.class, Arrays.asList("name"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(Personage.class, page, Arrays.asList("name"));
    }
    
}
