
package ru.sfedu.organizer.dao;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Opera;

/**
 *
 * @author sterie
 */

public class LibrettoDao extends Dao<Libretto>{
    
    public LibrettoDao() {
        super(Libretto.class);
    }
    
    public Optional<Libretto> getById(long id){
        return this.get(id);
    }
    
    @Override
    public void saveOrUpdate(Libretto libretto){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Opera opera = libretto.getOpera();
        opera.setLibretto(libretto);    
        session.saveOrUpdate(opera);
        tran.commit();
    }
}
