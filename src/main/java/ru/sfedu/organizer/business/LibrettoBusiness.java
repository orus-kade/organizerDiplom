/*
 */
package ru.sfedu.organizer.business;

import java.util.Optional;
import javax.ejb.Stateless;
import ru.sfedu.organizer.dao.LibrettoDao;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.model.LibrettoModel;

/**
 *
 * @author sterie
 */
@Stateless
public class LibrettoBusiness {
    
    private static final LibrettoDao librettoDao = new LibrettoDao();

    public LibrettoBusiness() {
    }

    public LibrettoModel getById(long id){
        Optional<Libretto> o = librettoDao.getById(id);
        LibrettoModel librettoModel = null;
        if (o.isPresent()){
            Libretto libretto = o.get();
            librettoModel = new LibrettoModel(libretto.getId(), libretto.getOpera().getId(), libretto.getOpera().getTitle(), libretto.getText());   
            librettoModel.addWriters(libretto.getWriters());
        }
        return librettoModel;
    }    
    
    public void delete(long id){
        Optional<Libretto> o = librettoDao.getById(id);
        if (o.isPresent()){
            librettoDao.delete(o.get());
        }
    }
}
