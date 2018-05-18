/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.model.AriaModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
public class AriaBusiness {
    
    private static final AriaDao ariaDao = new AriaDao();

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
        Optional<List> o = ariaDao.getByRange(from, to);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Aria> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }
    
    public List<SearchResult> getAll(){
        return this.getByRange(1, this.count());
    }
    
    public int count(){
        return ariaDao.count();
    }
}
