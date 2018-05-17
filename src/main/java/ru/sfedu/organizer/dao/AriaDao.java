package ru.sfedu.organizer.dao;

import ru.sfedu.organizer.entity.Aria;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;

/**
 *
 * @author sterie
 */

public class AriaDao extends Dao<Aria>{

    public AriaDao() {
        super(Aria.class);
    }
  
    public Optional<Aria> getById(long id){
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
    
//    public Optional<List> getByTitle(String title, int page){
//        Transaction transaction = session.beginTransaction();
//        Criteria criteria = session.createCriteria(Aria.class);
//        
//    }
    
//    public void delete(Aria aria){
//        this.getSession();
//        Transaction tran = session.beginTransaction();
////        session.createSQLQuery("delete from aria_concert where aria_id = :id")
////                .setParameter("id", aria.getId())
////                .executeUpdate();
//        session.delete(aria);
//        tran.commit();
//    }
}
