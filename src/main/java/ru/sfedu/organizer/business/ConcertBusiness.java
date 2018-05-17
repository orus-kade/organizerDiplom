/*
 */
package ru.sfedu.organizer.business;

import java.util.Optional;
import javax.ejb.Stateless;
import ru.sfedu.organizer.dao.ConcertDao;
import ru.sfedu.organizer.entity.Concert;
import ru.sfedu.organizer.model.ConcertModel;

/**
 *
 * @author sterie
 */
@Stateless
public class ConcertBusiness {
    
    private static final ConcertDao consertDao = new ConcertDao();

    public ConcertBusiness() {
    }
    
    public ConcertModel getById(long id){
        Optional<Concert> o = consertDao.getById(id);
        ConcertModel consertModel = null;
        if (o.isPresent()){
            Concert concert = o.get();
            consertModel = new ConcertModel(concert.getId(), concert.getTitle(), concert.getDescription());   
            consertModel.addDirector(concert.getDirector());
            consertModel.addSingers(concert.getSingers());
            consertModel.addAries(concert.getAries());
            consertModel.addEvents(concert.getSingleEvents());
        }
        return consertModel;
    }
    
    public void delete(long id){
        Optional<Concert> o = consertDao.getById(id);
        if (o.isPresent()){
            consertDao.delete(o.get());
        }
    } 
}
