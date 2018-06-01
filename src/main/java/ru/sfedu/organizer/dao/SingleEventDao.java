
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */

public class SingleEventDao extends Dao<SingleEvent>{
    
    /**
     *
     */
    public SingleEventDao() {
        super(SingleEvent.class);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<SingleEvent> getById(long id){
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
    public int countFuture(){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(this.entityClass);
        criteria.add(Restrictions.gt("datetime", Utils.getCurrentDateWithoutTime()));
        criteria.setProjection(Projections.rowCount());
        int count = Integer.parseInt(criteria.uniqueResult().toString());
        tran.commit();
        this.closeSession();
        return count;
    }
    
    /**
     *
     * @return
     */
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("datetime"));
    }
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
    public Optional<List> getByRange(int from, int to){
        return super.getByRange(from, to, Arrays.asList("datetime"));
    }
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
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
        this.closeSession();
        return result;
    }
    
    public List<MediaLink> getMediaLinks(long id){
        return super.getLinks(id);
    }
    
    public void deleteLinks (List<MediaLink> list){
        super.deleteListLinks(list);
    }
    
    public void saveLinks (List<MediaLink> list){
        super.saveOrUpdateLinks(list);
    }
}
