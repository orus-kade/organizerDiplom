
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import ru.sfedu.organizer.entity.Concert;

/**
 *
 * @author sterie
 */
public class ConcertDao extends Dao<Concert>{
    
    public ConcertDao() {
        super(Concert.class);
    }
    
    public Optional<Concert> getById(long id){
        return this.get(id);
    }
    
    public int count(){
        return this.countAll();
    }
    
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("title"));
    }
    
    public Optional<List> getByRange(int from, int to){
        return super.getByRange(from, to, Arrays.asList("title"));
    }
    
    public Optional<List> search(String key){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(this.entityClass);
        criteria.add(Restrictions.ilike("title", key, MatchMode.ANYWHERE));
        criteria.addOrder(Order.asc("title"));
        criteria.addOrder(Order.asc("id"));
        Optional<List> result = Optional.ofNullable(criteria.list());
        tran.commit();
        return result;
    } 
    
}
