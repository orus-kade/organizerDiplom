/*
 */
package ru.sfedu.organizer.business;

import java.util.Date;
import java.util.Optional;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.HumanModel;
import ru.sfedu.organizer.model.HumanWorks;

/**
 *
 * @author sterie
 */
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
        humanWorks.addComposerWorks(humanDao.getHumanWorksByProfession(id, Professions.COMPOSER));
        humanWorks.addWriterWorks(humanDao.getHumanWorksByProfession(id, Professions.WRITER));
        humanWorks.addLibrettos(humanDao.getHumanWorksLibretto(id));
        return humanWorks;
    }
}
