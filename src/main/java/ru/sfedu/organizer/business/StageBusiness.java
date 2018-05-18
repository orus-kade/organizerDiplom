/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.sfedu.organizer.dao.StageDao;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.model.SearchResult;
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
    
    public List<SearchResult> getByRange(int from, int to){
        Optional<List> o = stageDao.getByRange(from, to);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()){            
            List<Stage> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }
    
    public List<SearchResult> getAll(){
        return this.getByRange(1, this.count());
    }
    
    public int count(){
        return stageDao.count();
    }
}
