/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import ru.sfedu.organizer.dao.SingleEventDao;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.model.SearchResult;
import ru.sfedu.organizer.model.SingleEventInfo;
import ru.sfedu.organizer.model.SingleEventModel;

/**
 *
 * @author sterie
 */
public class SingleEventBusiness {
    
    private static final SingleEventDao dao = new SingleEventDao();

    public SingleEventBusiness() {
    }
    
    public SingleEventModel getById(long id){
        Optional<SingleEvent> o = dao.getById(id);
        SingleEventModel singleEventModel = null;
        if (o.isPresent()){
            SingleEvent singleEvent = o.get();
            singleEventModel = new SingleEventModel(
                    singleEvent.getId(), 
                    singleEvent.getEvent().getTitle(), 
                    singleEvent.getEvent().getClass().getSimpleName().toLowerCase(), 
                    singleEvent.getEvent().getId(),
                    singleEvent.getDescription(),
                    new Date(singleEvent.getDatetime()),
                    singleEvent.getPlace().getId(),
                    singleEvent.getPlace().getTitle(),
                    singleEvent.getPlace().getLocation()
            );
        }
        return singleEventModel;
    }
    
    public void delete(long id){
        Optional<SingleEvent> o = dao.getById(id);
        if (o.isPresent()){
            dao.delete(o.get());
        }
    }
    
    public List<SingleEventInfo> getByRange(int from, int to){
        Optional<List> o;
        if (from == 0 && to == 0){
            o = dao.getAll();
        }
        else{
            o = dao.getByRange(from, to);
        }
        List<SingleEventInfo> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<SingleEvent> list = o.get();
            list.stream().forEach(e -> result.add(new SingleEventInfo(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
        }
        return result;
    }
    
    public List<SingleEventInfo> getByRangeFuture(int from, int to){
        Optional<List> o;
        if (from == 0 && to == 0){
            o = dao.getAll();
        }
        else{
            o = dao.getByRange(from, to);
        }
        List<SingleEventInfo> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<SingleEvent> list = o.get();
            list.stream().forEach(e -> result.add(new SingleEventInfo(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
        }
        return result;
    }
    
    public List<SingleEventInfo> getAll(){
        return this.getByRange(0, 0);
    }
    
    public List<SingleEventInfo> getAllFuture(){
        return this.getByRange(0, 0);
    }
    
    public int count(){
        return dao.count();
    }
    
    public int countFuture(){
        return dao.countFuture();
    }
    
    public void createOrSave (SingleEvent item){
        dao.saveOrUpdate(item);
    }
}
