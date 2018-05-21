/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.dao.OperaDao;
import ru.sfedu.organizer.dao.PersonageDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.model.PersonageModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
public class PersonageBusiness {
    
    private final PersonageDao personageDao = new PersonageDao();
    
    private final OperaDao operaDao = new OperaDao();
    
    private final AriaDao ariaDao =  new AriaDao();

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
    
    public List<SearchResult> search(String key){
        Optional<List> o;
        if (key == null || key.trim().length()==0){
            o = personageDao.getAll();
        }
        else{
            String keyNew = key.trim().toLowerCase();   
            o = personageDao.search(keyNew);
        }             
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Personage> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getName())));
        }
        return result;
    }
    
    public long createOrSave (PersonageModel personageModel) throws ObjectNotFoundException{
        Personage pers;
        if (personageModel.getId() <= 0)
            pers = new Personage();
        else{
            Optional<Personage> o = personageDao.getById(personageModel.getId());
            if (!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.PERSONAGE, personageModel.getId());
            pers = o.get();
        }
        pers.setDescription(personageModel.getDescription());
        pers.setName(personageModel.getName());
        Optional<Opera> opera = operaDao.getById(personageModel.getOperaId());
            if (!opera.isPresent()) throw new ObjectNotFoundException(ObjectTypes.OPERA, personageModel.getOperaId());
            pers.setOpera(opera.get());
            if (personageModel.getAriesId()!= null) {
            List<Aria> aries = new ArrayList<>();
            personageModel.getAriesId().stream().forEach(e -> {
                Optional<Aria> o = ariaDao.getById(e);
                if (o.isPresent()){
                    if (pers.getOpera().equals(o.get().getOpera()))
                    aries.add(o.get());
                }                
            });
            pers.setAries(aries);
        }
        personageDao.saveOrUpdate(pers);
        return pers.getId();
    }    
}
