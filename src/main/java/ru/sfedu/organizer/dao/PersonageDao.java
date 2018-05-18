package ru.sfedu.organizer.dao;

import ru.sfedu.organizer.entity.Personage;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

/**
 *
 * @author sterie
 */
public class PersonageDao extends Dao<Personage>{
    
    public PersonageDao() {
        super(Personage.class);
    }
    
    public Optional<Personage> getById(long id){
        return this.get(id);
    }
    
    public int count(){
        return this.countAll();
    }
    
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("name"));
    }
    
    public Optional<List> getByRange(int from, int to){
        return super.getByRange(from, to, Arrays.asList("name"));
    }
    
}
