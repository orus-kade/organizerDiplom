/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.dao.OperaDao;
import ru.sfedu.organizer.dao.PersonageDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.AriaModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
public class AriaBusiness {
    
    private AriaDao ariaDao = new AriaDao();
    
    private HumanDao humanDao = new HumanDao();
    
    @EJB 
    private HumanBusiness humanBusiness = new HumanBusiness();
    
    private OperaDao operaDao = new OperaDao();
    
    private PersonageDao personageDao = new PersonageDao();

    public AriaBusiness() {
    }
    
    public AriaModel getById(long id){
        Optional<Aria> o = ariaDao.getById(id);
        AriaModel ariaModel = null;
        if (o.isPresent()){
            Aria aria = o.get();
            ariaModel = new AriaModel(aria.getId(), aria.getTitle(), aria.getText(), aria.getPosition());   
            ariaModel.addOpera(aria.getOpera());
            ariaModel.addComposers(aria.getComposers());
            ariaModel.addWriters(aria.getWriters());
            ariaModel.addPersonages(aria.getPersonages());
        }
        return ariaModel;
    }

    public void delete(long id){
        Optional<Aria> o = ariaDao.getById(id);
        if (o.isPresent()){
            ariaDao.delete(o.get());
        }
    }   
    
    public List<SearchResult> getByRange(int from, int to){
        Optional<List> o;
        if (from == 0 && to == 0){
            o = ariaDao.getAll();
        }
        else{
            o = ariaDao.getByRange(from, to);
        }        
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Aria> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }
    
    public List<SearchResult> getAll(){
        return this.getByRange(0, 0);
    }
    
    public int count(){
        return ariaDao.count();
    }
    
    public List<SearchResult> search(String key){
        if (key == null || key.trim().length()==0){
            return this.getAll();
        }
        String keyNew = key.trim().toLowerCase();
        Optional<List> o = ariaDao.search(keyNew);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Aria> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }
    
    public long createOrSave (AriaModel ariaModel) throws ObjectNotFoundException{
        Aria aria;
        if (ariaModel.getId() <= 0)
            aria = new Aria();
        else{
            Optional<Aria> o = ariaDao.getById(ariaModel.getId());
            if (!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.ARIA, ariaModel.getId());
            aria = o.get();
        }
            aria.setPosition(ariaModel.getPosition());
            aria.setTitle(ariaModel.getTitle());
            aria.setText(ariaModel.getText());
            Optional<Opera> opera = operaDao.getById(ariaModel.getOperaId());
            if (!opera.isPresent()) throw new ObjectNotFoundException(ObjectTypes.OPERA, ariaModel.getOperaId());
            aria.setOpera(opera.get());
            if (ariaModel.getWritersId() != null){
                List<Human> writers = new ArrayList<>();
                ariaModel.getWritersId().stream().forEach(e -> {
                    Optional<Human> o = humanDao.getById(e);
                    if (o.isPresent())
                        writers.add(o.get());
                });
                writers.removeIf(e -> !humanBusiness.isHumanProfession(e, Professions.WRITER));
                aria.setWriters(writers);
            }
            if (ariaModel.getComposersId()!= null){
                List<Human> composers = new ArrayList<>();
                ariaModel.getComposersId().stream().forEach(e -> {
                    Optional<Human> o = humanDao.getById(e);
                    if (o.isPresent())
                        composers.add(o.get());
                });
                composers.removeIf(e -> !humanBusiness.isHumanProfession(e, Professions.COMPOSER));
                aria.setComposers(composers);
            }
            if (ariaModel.getPersonagesId() != null){
                List<Personage> personages = new ArrayList<>();
                ariaModel.getPersonagesId().stream().forEach(e -> {
                    Optional<Personage> o = personageDao.getById(e);
                    if (o.isPresent())
                        if (aria.getOpera().equals(o.get().getOpera()))
                            personages.add(o.get());
                });
                aria.setPersonages(personages);
            }           
        ariaDao.saveOrUpdate(aria);
        return aria.getId();
    }
}
