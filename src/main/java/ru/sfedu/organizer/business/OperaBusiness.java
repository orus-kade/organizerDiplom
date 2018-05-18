/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import ru.sfedu.organizer.dao.OperaDao;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.model.OperaModel;
import ru.sfedu.organizer.model.SearchResult;

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
            operaModel = new OperaModel(opera.getId(), opera.getTitle(), opera.getDescription());   
            operaModel.addAries(opera.getAries());
            if (opera.getLibretto() != null)
                operaModel.setLibrettoId(opera.getLibretto().getId());
            operaModel.addPersonages(opera.getPersonages());            
            operaModel.addComposers(operaDao.getHumansByProfession(opera, Professions.COMPOSER));
            operaModel.addWriters(operaDao.getHumansByProfession(opera, Professions.WRITER));
            operaModel.addStages(opera.getStages());
            operaModel.addFutureEvents(operaDao.getFutureEvents(opera.getStages()));            
        }
        return operaModel;
    }  
    
    public void delete(long id){
        Optional<Opera> o = operaDao.getById(id);
        if (o.isPresent()){
            operaDao.delete(o.get());
        }
    }
    
    public List<SearchResult> getByRange(int from, int to){
        Optional<List> o = operaDao.getByRange(from, to);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Opera> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }
    
    public List<SearchResult> getAll(){
        return this.getByRange(1, this.count());
    }
    
    public int count(){
        return operaDao.count();
    }
}
