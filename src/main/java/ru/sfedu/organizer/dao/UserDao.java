/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.organizer.dao;

import java.util.Optional;
import org.hibernate.Session;
import ru.sfedu.organizer.model.Aria;
import ru.sfedu.organizer.model.User;

/**
 *
 * @author sterie
 */
public class UserDao extends Dao<User>{
    
    public UserDao(Session session) {
        super(session);
    }
    
    public Optional<User> getById(long id){
        return this.get(id, User.class);
    }
}
