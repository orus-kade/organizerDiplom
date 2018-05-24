package ru.sfedu.organizer.dao;

import ru.sfedu.organizer.entity.Personage;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sterie
 */

public class PersonageDao extends Dao<Personage>{
    
    /**
     *
     */
    public PersonageDao() {
        super(Personage.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Optional<Personage> getById(long id){
        return this.get(id);
    }
    
    /**
     *
     * @return
     */
    public int count(){
        return this.countAll();
    }
    
    /**
     *
     * @return
     */
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("name"));
    }
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
    public Optional<List> getByRange(int from, int to){
        return super.getByRange(from, to, Arrays.asList("name"));
    }
    
    /**
     *
     * @param key
     * @return
     */
    public Optional<List> search(String key){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(this.entityClass);
        criteria.add(Restrictions.ilike("name", key, MatchMode.ANYWHERE));
        criteria.addOrder(Order.asc("name"));
        criteria.addOrder(Order.asc("id"));
        Optional<List> result = Optional.ofNullable(criteria.list());
        tran.commit();
        this.closeSession();
        return result;
    } 
}
