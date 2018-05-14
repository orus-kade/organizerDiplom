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
import ru.sfedu.organizer.entity.SingleEvent;

/**
 *
 * @author sterie
 */
public class SingleEventDao extends Dao<SingleEvent>{
    
    public SingleEventDao(Session session) {
        super(session);
    }
    public Optional<SingleEvent> getById(long id){
        return this.get(id, SingleEvent.class);
    }
    
    public int count(){
        return this.countAll(SingleEvent.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(SingleEvent.class, Arrays.asList("datetime"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(SingleEvent.class, page, Arrays.asList("datetime"));
    }
}
