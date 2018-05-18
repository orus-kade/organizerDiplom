
package ru.sfedu.organizer.dao;


import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import ru.sfedu.organizer.Constants;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.InitialiseUtil;


/**
 *
 * @author sterie
 */
public abstract class Dao<T> {    
    
    private Logger logger = LogManager.getRootLogger();
    
    protected Session session = null;
    
    protected Class<T> entityClass;
    
    public Dao(Class entityClass) {
        this.entityClass = entityClass;
    }
    
    protected void getSession(){
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    protected Optional<T> get(long id){ 
        this.getSession();
        Transaction tran = session.beginTransaction();
        Optional item = Optional.ofNullable(session.get(this.entityClass, id));
        if (item.isPresent()){
            InitialiseUtil.forcedInitialise(item.get());
        } 
        tran.commit();
        return item;
    }
    
    public void delete(T item){
        this.getSession();
        Transaction tran = session.beginTransaction();
        session.delete(item);
        tran.commit();
    }
    
    public void deleteList(List listItem){
            this.getSession();
            Transaction tran = session.beginTransaction();
            listItem.forEach(e ->{
                if (e != null){
                    session.delete(e);
                }
            });
            tran.commit();
    }
    
    public void saveOrUpdate(Optional<T> item){
        if (item.isPresent()){
            this.getSession();
            Transaction tran = session.beginTransaction();
            session.saveOrUpdate(item.get());
            tran.commit();
        }
    }
    
    public void saveOrUpdateList(Optional<List> itemList){
        if (itemList.isPresent()){
            this.getSession();
            Transaction tran = session.beginTransaction();
            itemList.get().forEach(e ->{
                if (e != null){
                    session.saveOrUpdate(e);
                }
            });            
            tran.commit();
        }
    }
    
    
    protected Optional<List> getAll(List<String> orderParametrs){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(this.entityClass);
        if (!orderParametrs.isEmpty()){
            orderParametrs.forEach(par ->{
                criteria.addOrder(Order.asc(par));
            });
        }
        Optional<List> result = Optional.ofNullable(criteria.list());
        tran.commit();
        return result;
    }
    
    protected Optional<List> getByRange(int from, int to, List<String> orderParametrs){
        if (from > to){
            int foo = from;
            from = to;
            to = foo;
        }
        this.getSession();
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(this.entityClass);
        if (!orderParametrs.isEmpty()){
            orderParametrs.forEach(par ->{
                criteria.addOrder(Order.asc(par));
            });
        }
        criteria.addOrder(Order.asc("id"));
        criteria.setFirstResult(from-1);
        criteria.setMaxResults(to-from+1);
        Optional<List> result = Optional.ofNullable(criteria.list());
        tran.commit();
        return result;
    }
        
    protected int countAll(){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Criteria criteria = session.createCriteria(this.entityClass);
        criteria.setProjection(Projections.rowCount());
        int count = Integer.parseInt(criteria.uniqueResult().toString());
        tran.commit();
        return count;        
    }
}
