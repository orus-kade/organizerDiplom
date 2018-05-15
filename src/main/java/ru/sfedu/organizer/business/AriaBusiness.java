/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.model.AriaModel;

/**
 *
 * @author sterie
 */
public class AriaBusiness {
    
    private static AriaDao ariaDao = new AriaDao();

    public AriaBusiness() {
    }
    
    public AriaModel getById(long id){
        Optional<Aria> o = ariaDao.getById(id);
        AriaModel ariaModel = null;
        if (o.isPresent()){
            Aria aria = o.get();
            ariaModel = new AriaModel(aria.getId(), aria.getTitle(), aria.getText(), aria.getPosition());   
            ariaModel.addOpera(aria.getOpera());
            ariaModel.addComposers(new ArrayList<>(aria.getComposers()));
            ariaModel.addWriters(new ArrayList<>(aria.getWriters()));
            ariaModel.addPersonages(new ArrayList<>(aria.getPersonages()));
        }
        return ariaModel;
    }    
}
