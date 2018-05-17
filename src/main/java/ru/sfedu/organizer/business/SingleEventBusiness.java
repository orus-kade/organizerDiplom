/*
 */
package ru.sfedu.organizer.business;

import java.util.Date;
import java.util.Optional;
import ru.sfedu.organizer.dao.SingleEventDao;
import ru.sfedu.organizer.entity.SingleEvent;
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
    
}
