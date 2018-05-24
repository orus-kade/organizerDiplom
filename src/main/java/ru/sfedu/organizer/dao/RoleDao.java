
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Role;
import ru.sfedu.organizer.utils.InitialiseUtil;

/**
 *
 * @author sterie
 */

public class RoleDao extends Dao<Role>{
    
    /**
     *
     */
    public RoleDao() {
        super(Role.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Optional<Role> getById(long id){
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
        return super.getAll(Arrays.asList("personage"));
    } 
    
    /**
     *
     * @param persId
     * @param singerId
     * @return
     */
    public Optional<Role> getByPesonageAndSinger(long persId, long singerId){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Optional item = Optional.ofNullable(session.createCriteria(Role.class)
                .createAlias("personage", "personage")
                .add(Restrictions.eq("personage.id", persId))
                .createAlias("singer", "singer")
                .add(Restrictions.eq("singer.id", singerId))
                .uniqueResult()
        );        
        if (item.isPresent()){
            InitialiseUtil.forcedInitialise(item.get());
        } 
        tran.commit();
        this.closeSession();
        return item;
    }
}
