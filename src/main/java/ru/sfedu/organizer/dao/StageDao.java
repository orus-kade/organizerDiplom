/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.model.Aria;
import ru.sfedu.organizer.model.Stage;

/**
 *
 * @author sterie
 */
public class StageDao extends Dao<Stage>{
    
    public StageDao(Session session) {
        super(session);
    }
    
    public Optional<Stage> getById(long id){
        return this.get(id, Stage.class);
    }
    
    public int count(){
        return this.countAll(Stage.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(Stage.class, Arrays.asList("title"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(Stage.class, page, Arrays.asList("title"));
    }
    
}
