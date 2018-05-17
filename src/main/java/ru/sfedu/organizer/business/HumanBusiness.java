/*
 */
package ru.sfedu.organizer.business;

import java.util.Date;
import java.util.Optional;
import javax.ejb.Stateless;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.HumanEvents;
import ru.sfedu.organizer.model.HumanModel;
import ru.sfedu.organizer.model.HumanWorks;

/**
 *
 * @author sterie
 */
@Stateless
public class HumanBusiness {
    private static final HumanDao humanDao = new HumanDao();

    public HumanBusiness() {
    }
    
    public HumanModel getById(long id){
        Optional<Human> o = humanDao.getById(id);
        HumanModel humanModel = null;
        if (o.isPresent()){
            Human human = o.get();
            humanModel = new HumanModel(human.getId(), human.getName(), human.getSurname(), human.getPatronymic(), human.getBiography(), new Date(human.getBirthDate()), new Date(human.getDeathDate()));   
            humanModel.addPlaces(human.getPlaces());
            humanModel.addProfessions(human.getProfessions());
        }
        return humanModel;
    }
    
    public HumanWorks getWorksById(long id){
        HumanWorks humanWorks = new HumanWorks();
        humanWorks.addComposerWorks(humanDao.getWorksByProfession(id, Professions.COMPOSER));
        humanWorks.addWriterWorks(humanDao.getWorksByProfession(id, Professions.WRITER));
        humanWorks.addLibrettos(humanDao.getWorksLibretto(id));
        return humanWorks;
    }
    
    public HumanEvents getEventsById(long id){
        HumanEvents humanEvents = new HumanEvents();
        humanEvents.addFutureEvents(humanDao.getFutureEvents(id));
        humanEvents.addDirectorEvents(humanDao.getDirectorEvents(id));
        humanEvents.addSingerEvents(humanDao.getSingerEvents(id));
        return humanEvents;
    }
    
    public void delete(long id){
        Optional<Human> o = humanDao.getById(id);
        if (o.isPresent()){
            humanDao.delete(o.get());
        }
    }
}
