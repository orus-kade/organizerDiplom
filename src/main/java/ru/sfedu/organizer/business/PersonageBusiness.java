/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.sfedu.organizer.dao.PersonageDao;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.model.PersonageModel;
import ru.sfedu.organizer.model.SearchResult;

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
    
    public void createOrSave (Personage item){
        personageDao.saveOrUpdate(item);
    }
    
}
