
package ru.sfedu.organizer.dao;

import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Libretto;

/**
 *
 * @author sterie
 */
public class LibrettoDao extends Dao<Libretto>{
    
    public LibrettoDao(Session session) {
        super(session);
    }
    
    public Optional<Libretto> getById(long id){
        return this.get(id, Libretto.class);
    }
}