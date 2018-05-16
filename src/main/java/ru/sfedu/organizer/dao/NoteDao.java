
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Note;

/**
 *
 * @author sterie
 */
public class NoteDao extends Dao<Note>{
    
    public NoteDao() {
        super(Note.class);
    }
    
    public Optional<Note> getById(long id){
        return this.get(id);
    }
    
//    public int count(){
//        return this.countAll(Aria.class);
//    }
//    
//    public Optional<List> getAll(){
//        return super.getAll(Aria.class, Arrays.asList("title"));
//    }
//    
//    public Optional<List> getAllByPage(int page){
//        return super.getAllByPage(Aria.class, page, Arrays.asList("title"));
//    }
    
}
