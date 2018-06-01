
package ru.sfedu.organizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.MediaLink;
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
    
    /**
     *
     */
    public OperaDao() {
        super(Opera.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Optional<Opera> getById(long id){
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
        return super.getAll(Arrays.asList("title"));
    }
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
    public Optional<List> getByRange(int from, int to){
        return super.getByRange(from, to, Arrays.asList("title"));
    }
    
    /**
     *
     * @param opera
     * @param profession
     * @return
     */
    public List<Human> getHumansByProfession(Opera opera, Professions profession){
        List<Human> list = new ArrayList<>();
        if ((profession.equals(Professions.COMPOSER) || profession.equals(Professions.WRITER)) && opera.getAries() != null && !opera.getAries().isEmpty()){
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
            this.closeSession();
        }        
        return list;
    }
    
    /**
     *
     * @param list
     * @return
     */
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
            this.closeSession();
        }
        return singleEvents;
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
        criteria.add(Restrictions.ilike("title", key, MatchMode.ANYWHERE));
        criteria.addOrder(Order.asc("title"));
        criteria.addOrder(Order.asc("id"));
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
