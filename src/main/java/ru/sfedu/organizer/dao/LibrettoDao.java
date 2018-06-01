
package ru.sfedu.organizer.dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.Opera;

/**
 *
 * @author sterie
 */

public class LibrettoDao extends Dao<Libretto>{
    
    /**
     *
     */
    public LibrettoDao() {
        super(Libretto.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Optional<Libretto> getById(long id){
        return this.get(id);
    }
    
    /**
     *
     * @param libretto
     */
    @Override
    public void saveOrUpdate(Libretto libretto){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Opera opera = libretto.getOpera();
        opera.setLibretto(libretto);    
        session.saveOrUpdate(opera);
        tran.commit();
        this.closeSession();
    }
    
    public List<MediaLink> getMediaLinks(long id){
        return super.getLinks(id);
    }
    
    public void deleteLinks (List<MediaLink> list){
        super.deleteListLinks(list);
    }
    
    public void saveLinks (List<MediaLink> list){
        super.saveOrUpdateLinks(list);
    }
}
