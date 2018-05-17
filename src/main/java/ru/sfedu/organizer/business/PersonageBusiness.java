/*
 */
package ru.sfedu.organizer.business;

import java.util.Optional;
import ru.sfedu.organizer.dao.PersonageDao;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.model.PersonageModel;

/**
 *
 * @author sterie
 */
public class PersonageBusiness {
    
    private static final PersonageDao personageDao = new PersonageDao();

    public PersonageBusiness() {
    }
     
    public PersonageModel getById(long id){
        Optional<Personage> o = personageDao.getById(id);
        PersonageModel personageModel = null;
        if (o.isPresent()){
            Personage personage = o.get();
            personageModel = new PersonageModel(personage.getId(), personage.getName(), personage.getOpera().getId(), personage.getOpera().getTitle(), personage.getDescription());   
            personageModel.addAries(personage.getAries());
        }
        return personageModel;
    } 
    
    public void delete(long id){
        Optional<Personage> o = personageDao.getById(id);
        if (o.isPresent()){
            personageDao.delete(o.get());
        }
    }
}
