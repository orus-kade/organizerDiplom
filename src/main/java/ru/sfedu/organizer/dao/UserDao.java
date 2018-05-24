
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.sfedu.organizer.entity.User;
import ru.sfedu.organizer.utils.InitialiseUtil;

/**
 *
 * @author sterie
 */

public class UserDao extends Dao<User>{
    
    /**
     *
     */
    public UserDao() {
        super(User.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Optional<User> getById(long id){
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
        return super.getAll(Arrays.asList("login", "createDate", "id"));
    }
    
    /**
     *
     * @param login
     * @return
     */
    public Optional<User> getByLogin(String login){
        this.getSession();
        Transaction tran = session.beginTransaction();
        Optional item = Optional.ofNullable(session.createCriteria(User.class)
                .add(Restrictions.eq("login", login))
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
