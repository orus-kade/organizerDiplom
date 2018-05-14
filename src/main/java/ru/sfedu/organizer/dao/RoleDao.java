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
import ru.sfedu.organizer.entity.Role;

/**
 *
 * @author sterie
 */
public class RoleDao extends Dao<Role>{
    
    public RoleDao(Session session) {
        super(session);
    }
    
    public Optional<Role> getById(long id){
        return this.get(id, Role.class);
    }
    
    public int count(){
        return this.countAll(Role.class);
    }
    
    public Optional<List> getAll(){
        return super.getAll(Role.class, Arrays.asList("personage"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(Role.class, page, Arrays.asList("personage"));
    }
    
}
