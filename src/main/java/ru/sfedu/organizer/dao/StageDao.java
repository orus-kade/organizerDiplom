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
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Stage;

/**
 *
 * @author sterie
 */
public class StageDao extends Dao<Stage>{
    
    public StageDao(Session session) {
        super(Stage.class);
    }
    
    public Optional<Stage> getById(long id){
        return this.get(id);
    }
    
    public int count(){
        return this.countAll();
    }
    
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("title"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(page, Arrays.asList("title"));
    }
    
}
