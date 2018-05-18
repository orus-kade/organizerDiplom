
package ru.sfedu.organizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */
public class OperaDao extends Dao<Opera>{
    
    public OperaDao() {
        super(Opera.class);
    }
    
    public Optional<Opera> getById(long id){
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
    
    public List<Human> getHumansByProfession(Opera opera, Professions profession){
        List<Human> list = new ArrayList<>();
        if (profession.equals(Professions.COMPOSER) || profession.equals(Professions.WRITER)){
            this.getSession();
            Transaction tran = this.session.beginTransaction();
            String tableName = "aria_" + profession.toString().toLowerCase();
            List<Long> ariaIds = new ArrayList<>();
            opera.getAries().stream().forEach(e -> ariaIds.add(e.getId()));
            list.addAll(session.
                    createSQLQuery("select distinct {h.*} from human as h join " + tableName 
                            + " as jt on h.human_id = jt." + profession.toString().toLowerCase() 
                            + "_id where jt.aria_id in :ariaIds ")
                    .addEntity("h", Human.class)
                    .setParameterList("ariaIds", ariaIds)
                    .list());
            tran.commit();
        }        
        return list;
    }
    
    public List<SingleEvent> getFutureEvents(List<Stage> list){
        List<SingleEvent> singleEvents = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            List<Long> stageIds = new ArrayList<>();
            stageIds.addAll(list.stream().collect(ArrayList<Long>::new,
                    (a, r) -> a.add(r.getId()),
                    (a1, a2) -> a1.addAll(a2))
            );
            long date = Utils.getCurrentDateWithoutTime();
            this.getSession();
            Transaction tran = session.beginTransaction();
            singleEvents.addAll(session.createCriteria(SingleEvent.class)
                    .add(Restrictions.gt("datetime", date))
                    .createCriteria("event", "event")
                    .add(Restrictions.in("event.id", stageIds))
                    .list());
            tran.commit();
        }
        return singleEvents;
    }
}
