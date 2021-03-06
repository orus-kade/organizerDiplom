/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.entity.Voices;
import ru.sfedu.organizer.model.HumanEvents;
import ru.sfedu.organizer.model.HumanModel;
import ru.sfedu.organizer.model.HumanWorks;
import ru.sfedu.organizer.model.SearchResult;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */
@Stateless
public class HumanBusiness {
    
    static final Logger logger = LogManager.getLogger(HumanBusiness.class);
    
    private final HumanDao humanDao = new HumanDao();

    /**
     *
     */
    public HumanBusiness() {
    }
    
    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    public HumanModel getById(long id) throws ObjectNotFoundException{
        Optional<Human> o = humanDao.getById(id);
        HumanModel humanModel = null;
        if (o.isPresent()){
            Human human = o.get();
            humanModel = new HumanModel(human.getId(), human.getName(), human.getSurname(), human.getPatronymic(), human.getBiography(), new Date(human.getBirthDate()), new Date(human.getDeathDate()));   
            humanModel.addPlaces(human.getPlaces());
            humanModel.addProfessions(human.getProfessions());
            if (human.getVoice() != null)
                humanModel.setVoice(human.getVoice().toString());
            humanModel.setLinks(humanDao.getMediaLinks(id));
        }
        else throw new ObjectNotFoundException(ObjectTypes.HUMAN, id);
        return humanModel;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public HumanWorks getWorksById(long id){
        HumanWorks humanWorks = new HumanWorks();
        humanWorks.addComposerWorks(humanDao.getWorksByProfession(id, Professions.COMPOSER));
        humanWorks.addWriterWorks(humanDao.getWorksByProfession(id, Professions.WRITER));
        humanWorks.addLibrettos(humanDao.getWorksLibretto(id));
        return humanWorks;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public HumanEvents getEventsById(long id){
        HumanEvents humanEvents = new HumanEvents();
        humanEvents.addFutureEvents(humanDao.getFutureEvents(id));
        humanEvents.addDirectorEvents(humanDao.getDirectorEvents(id));
        humanEvents.addSingerEvents(humanDao.getSingerEvents(id));
        return humanEvents;
    }
    
    /**
     *
     * @param id
     * @throws ObjectNotFoundException
     */
    public void delete(long id) throws ObjectNotFoundException{
        Optional<Human> o = humanDao.getById(id);
        if (o.isPresent()){
            humanDao.delete(o.get());
        }
        else throw new ObjectNotFoundException(ObjectTypes.HUMAN, id);
    }
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
    public List<SearchResult> getByRange(int from, int to){
        Optional<List> o;
        if (from == 0 && to == 0){
            o = humanDao.getAll();
        }
        else{
            o = humanDao.getByRange(from, to);
        }
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Human> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), Utils.getHumanName(e))));
        }
        return result;
    }
    
    /**
     *
     * @return
     */
    public List<SearchResult> getAll(){
        return this.getByRange(0, 0);
    }
    
    /**
     *
     * @return
     */
    public int count(){
        return humanDao.count();
    }
    
    /**
     *
     * @param key
     * @param professions
     * @return
     */
    public List<SearchResult> search(String key, List<Professions> professions){
        Optional<List> o;
        if (key == null || key.trim().length()==0){
            o = humanDao.getAll();
        }
        String keyNew = key.trim().toLowerCase();
        o = humanDao.search(keyNew);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Human> list = o.get();
            if (professions != null && !professions.isEmpty()){
                list.removeIf(e -> !this.isHumanProfession(e, professions));
            }
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), Utils.getHumanName(e))));
        }        
        return result;
    }
    
    /**
     *
     * @param human
     * @param prof
     * @return
     */
    public boolean isHumanProfession(Human human, Professions prof){
        return human.getProfessions().contains(prof);
    }
    
    /**
     *
     * @param human
     * @param profs
     * @return
     */
    public boolean isHumanProfession(Human human, List<Professions> profs){
        return human.getProfessions().containsAll(profs);
    }
    
    /**
     *
     * @param humanModel
     * @return
     * @throws ObjectNotFoundException
     */
    public long createOrSave (HumanModel humanModel) throws ObjectNotFoundException{
        Human human;
        if (humanModel.getId() <= 0)
            human = new Human();
        else{
            Optional<Human> o = humanDao.getById(humanModel.getId());
            if (!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.HUMAN, humanModel.getId());
            human = o.get();
        }
        human.setBiography(humanModel.getBiography());
        if (humanModel.getBirthDate() != null)
            human.setBirthDate(humanModel.getBirthDate().getTime());
        if (humanModel.getDeathDate()!= null)
            human.setDeathDate(humanModel.getDeathDate().getTime());
        human.setName(humanModel.getName());
        human.setSurname(humanModel.getSurname());
        human.setPatronymic(humanModel.getPatronymic());
        
        try{       
            human.setVoice(Voices.valueOf(humanModel.getVoice()));
        }
        catch (IllegalArgumentException ex){
            logger.error(ex.getMessage());
        }
            if (humanModel.getProfessions() != null || !humanModel.getProfessions().isEmpty()){
                List<Professions> list = new ArrayList<>();
                humanModel.getProfessions().stream().forEach(e ->{
                    try {
                        list.add(Professions.valueOf(e));
                    } catch (IllegalArgumentException ex){
                        logger.error(ex.getMessage());
                    }                   
                });
                human.setProfessions(list);
            }        
        humanDao.saveOrUpdate(human);
        this.updateLinks(humanModel.getLinks(), human.getId());
        return human.getId();
    }
   
    private void updateLinks(List<MediaLink> list, long id) {
        List<MediaLink> oldList = new ArrayList<>();
        oldList.addAll(humanDao.getMediaLinks(id));
        if (list != null && !list.isEmpty()) {
            list.stream().forEach(e -> {
                e.setObjectId(id);
                e.setObjectType(ObjectTypes.HUMAN);
            });
            List<Long> newIds = new ArrayList<>();
            newIds.addAll(list.stream().collect(ArrayList<Long>::new,
                    (a, r) -> a.add(r.getId()),
                    (a1, a2) -> a1.addAll(a2))
            );
            oldList.removeIf(e -> newIds.contains(e.getId()));
            humanDao.saveLinks(list);
        }        
        humanDao.deleteLinks(oldList);        
    }
}
