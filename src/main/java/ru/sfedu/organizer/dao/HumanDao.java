
package ru.sfedu.organizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Event;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Professions;

/**
 *
 * @author sterie
 */
public class HumanDao extends Dao<Human>{
    
    public HumanDao() {
        super(Human.class);
    }
    
    public Optional<Human> getById(long id){
        return this.get(id);
    }
    
    public int count(){
        return this.countAll();
    }
    
    public Optional<List> getAll(){
        return super.getAll(Arrays.asList("surname", "name", "patronymic"));
    }
    
    public Optional<List> getAllByPage(int page){
        return super.getAllByPage(page, Arrays.asList("surname", "name", "patronymic"));
    }
    
    public List<Aria> getHumanWorksByProfession(long humanId, Professions profession){
        List<Aria> list = new ArrayList<>();
        if (profession.equals(Professions.COMPOSER) || profession.equals(Professions.WRITER)){
            this.getSession();
            Transaction tran = this.session.beginTransaction();
            String tableName = "aria_" + profession.toString().toLowerCase();
            list.addAll(session.
                    createSQLQuery("select distinct {a.*} from aria as a join " + tableName 
                            + " as jt on a.aria_id = jt.aria_id where jt." + profession.toString().toLowerCase() + "_id  = :humanId")
                    .addEntity("a", Aria.class)
                    .setParameter("humanId", humanId)
                    .list());
            tran.commit();
        }        
        return list;
    }
    
    public List<Libretto> getHumanWorksLibretto(long humanId){
        List<Libretto> list = new ArrayList<>();
            this.getSession();
            Transaction tran = this.session.beginTransaction();
            list.addAll(session.
                    createSQLQuery("select distinct {l.*} from libretto as l join libretto_writer as jt on l.libretto_id = jt.libretto_id where jt.writer_id  = :humanId")
                    .addEntity("l", Libretto.class)
                    .setParameter("humanId", humanId)
                    .list());
            tran.commit();
        return list;
    }
}
