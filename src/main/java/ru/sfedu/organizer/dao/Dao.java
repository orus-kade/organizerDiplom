
package ru.sfedu.organizer.dao;


import java.util.List;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import ru.sfedu.organizer.Constants;

/**
 *
 * @author sterie
 */
public  class Dao<T> {    
    
    protected Session session;
    
    public Dao(Session session) {
        this.session = session;
    }
    
    protected Optional<T> get(long id, Class cl){ 
        Transaction tran = session.beginTransaction();
        Optional item = Optional.ofNullable(session.get(cl, id));
        tran.commit();
        return item;
    }
    
    public void delete(Optional<T> item){
        if(item.isPresent()){
            Transaction tran = session.beginTransaction();
            session.delete(item);
            tran.commit();
        }
    }
    
    public void deleteList(Optional<List> listItem){
        if(listItem.isPresent()){
            Transaction tran = session.beginTransaction();
            listItem.get().forEach(e ->{
                if (e != null){
                    session.delete(e);
                }
            });
            tran.commit();
        }
    }
    
    public void saveOrUpdate(Optional<T> item){
        if (item.isPresent()){
            Transaction tran = session.beginTransaction();
            session.saveOrUpdate(item.get());
            tran.commit();
        }
    }
    
    public void saveOrUpdateList(Optional<List> itemList){
        if (itemList.isPresent()){
            Transaction tran = session.beginTransaction();
            itemList.get().forEach(e ->{
                if (e != null){
                    session.saveOrUpdate(e);
                }
            });            
            tran.commit();
        }
    }
    
    
    protected Optional<List> getAll(Class cl, List<String> orderParametrs){
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(cl);
        if (!orderParametrs.isEmpty()){
            orderParametrs.forEach(par ->{
                criteria.addOrder(Order.asc(par));
            });
        }
        Optional<List> result = Optional.ofNullable(criteria.list());
        tran.commit();
        return result;
    }
    
    protected Optional<List> getAllByPage(Class cl, int page, List<String> orderParametrs){
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(cl);
        if (!orderParametrs.isEmpty()){
            orderParametrs.forEach(par ->{
                criteria.addOrder(Order.asc(par));
            });
        }
        criteria.setFirstResult(Constants.ITEMS_ON_PAGE * (page-1));
        criteria.setMaxResults(Constants.ITEMS_ON_PAGE);
        Optional<List> result = Optional.ofNullable(criteria.list());
        tran.commit();
        return result;
    }
        
    protected int countAll(Class cl){
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(cl);
        criteria.setProjection(Projections.rowCount());
        int count = Integer.parseInt(criteria.uniqueResult().toString());
        tran.commit();
        return count;        
    }
}
