/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */

public class SingleEventDao extends Dao<SingleEvent>{
    
    public SingleEventDao() {
        super(SingleEvent.class);
    }
    public Optional<SingleEvent> getById(long id){
        return this.get(id);
    }
    
    public int count(){
        return this.countAll();
    }
    
    public int countFuture(){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(this.entityClass);
        criteria.add(Restrictions.gt("datetime", Utils.getCurrentDateWithoutTime()));
        criteria.setProjection(Projections.rowCount());
        int count = Integer.parseInt(criteria.uniqueResult().toString());
        tran.commit();
        return count;
    }
    
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("datetime"));
    }
    
    public Optional<List> getByRange(int from, int to){
        return super.getByRange(from, to, Arrays.asList("datetime"));
    }
    
    public Optional<List> getByRangeFuture(int from, int to){
        if (from > to){
            int foo = from;
            from = to;
            to = foo;
        }
        long date = Utils.getCurrentDateWithoutTime();
        this.getSession();
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(this.entityClass);
        criteria.add(Restrictions.gt("datetime", date));
        criteria.addOrder(Order.asc("datetime"));
        criteria.addOrder(Order.asc("id"));
        criteria.setFirstResult(from-1);
        criteria.setMaxResults(to-from+1);
        Optional<List> result = Optional.ofNullable(criteria.list());
        tran.commit();
        return result;
    }
}
