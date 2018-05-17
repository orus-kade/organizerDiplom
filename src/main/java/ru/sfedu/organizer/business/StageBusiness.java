/*
 */
package ru.sfedu.organizer.business;

import java.util.Optional;
import ru.sfedu.organizer.dao.StageDao;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.model.StageModel;

/**
 *
 * @author sterie
 */
public class StageBusiness {
    
    private static final StageDao stageDao=  new StageDao();

    public StageBusiness() {
    }
    
    public StageModel getById(long id){
        Optional<Stage> o = stageDao.getById(id);
        StageModel stageModel = null;
        if (o.isPresent()){
            Stage stage = o.get();
            stageModel = new StageModel(stage.getId(), stage.getTitle(), stage.getDescription());   
            stageModel.addDirector(stage.getDirector());
            stageModel.addEvents(stage.getSingleEvents());
            stageModel.addOpera(stage.getOpera());
            stageModel.addRoles(stage.getRoles());
        }
        return stageModel;
    }
    
    public void delete(long id){
        Optional<Stage> o = stageDao.getById(id);
        if (o.isPresent()){
            stageDao.delete(o.get());
        }
    } 
    
    
}
