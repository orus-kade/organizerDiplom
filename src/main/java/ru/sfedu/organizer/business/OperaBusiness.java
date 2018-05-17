/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.Optional;
import javax.ejb.Stateless;
import org.hibernate.Session;
import ru.sfedu.organizer.dao.OperaDao;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.OperaModel;

/**
 *
 * @author sterie
 */
@Stateless
public class OperaBusiness {
    
    private static OperaDao operaDao = new OperaDao();

    public OperaBusiness() {
    }
    
    public OperaModel getById(long id){
        Optional<Opera> o = operaDao.getById(id);
        OperaModel operaModel = null;
        if (o.isPresent()){
            Opera opera = o.get();
            operaModel = new OperaModel(opera.getId(), opera.getTitle(), opera.getDescription(), opera.getLibretto().getId());   
            operaModel.addAries(opera.getAries());
            operaModel.addPersonages(opera.getPersonages());            
            operaModel.addComposers(new ArrayList<>(operaDao.getHumansByProfession(opera, Professions.COMPOSER)));
            operaModel.addWriters(new ArrayList<>(operaDao.getHumansByProfession(opera, Professions.WRITER)));
        }
        return operaModel;
    }  
    
    public void delete(long id){
        Optional<Opera> o = operaDao.getById(id);
        if (o.isPresent()){
            operaDao.delete(o.get());
        }
    }
}
