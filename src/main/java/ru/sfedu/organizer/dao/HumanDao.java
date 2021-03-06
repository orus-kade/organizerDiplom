
package ru.sfedu.organizer.dao;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Event;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */

public class HumanDao extends Dao<Human>{
    
    /**
     *
     */
    public HumanDao() {
        super(Human.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Optional<Human> getById(long id){
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
        return super.getAll(Arrays.asList("surname", "name", "patronymic"));
    }
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
    public Optional<List> getByRange(int from, int to){
        return super.getByRange(from, to, Arrays.asList("surname", "name", "patronymic"));
    }
    
    /**
     *
     * @param humanId
     * @param profession
     * @return
     */
    public List<Aria> getWorksByProfession(long humanId, Professions profession){
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
            this.closeSession();
        }        
        return list;
    }
    
    /**
     *
     * @param humanId
     * @return
     */
    public List<Libretto> getWorksLibretto(long humanId){
        List<Libretto> list = new ArrayList<>();
            this.getSession();
            Transaction tran = this.session.beginTransaction();
            list.addAll(session.
                    createSQLQuery("select distinct {l.*} from libretto as l join libretto_writer as jt on l.libretto_id = jt.libretto_id where jt.writer_id  = :humanId")
                    .addEntity("l", Libretto.class)
                    .setParameter("humanId", humanId)
                    .list());
            tran.commit();
            this.closeSession();
        return list;
    }
    
    /**
     *
     * @param humanId
     * @return
     */
    public List<Event> getDirectorEvents(long humanId){
        List<Event> list = new ArrayList<>();
        this.getSession();
        Transaction tran = this.session.beginTransaction();
        list.addAll(session.createCriteria(Event.class)
                .add(Restrictions.sqlRestriction(" event_director_id = " + humanId))
                .list());
        tran.commit();
        this.closeSession();
        return list;
    }
    
    /**
     *
     * @param humanId
     * @return
     */
    public List<Event> getSingerEvents(long humanId){
        List<Event> list = new ArrayList<>();
        this.getSession();
        Transaction tran = this.session.beginTransaction();       
        //select {e.*} from event e where id in 
        //(select stage_id from role_stage rs join "role" r on rs.role_id = r.role_id where r.singer_id = 6
        //union
        //select consert_id from concert_singer where singer_id = 7
        //)
        List<Long> listEventsIds = new ArrayList<>();
        listEventsIds.addAll(session.createSQLQuery("select stage_id as id from role_stage rs join \"role\" r "
                + "on rs.role_id = r.role_id where r.singer_id = :humanId "
                + "union "
                + "select concert_id as id from concert_singer where singer_id = :humanId "
                + "union "
                + "select e.id as id from event e where event_director_id = :humanId ")
                .setParameter("humanId", humanId)
                .addScalar("id", LongType.INSTANCE)
                .list());
        if (! listEventsIds.isEmpty()) {
            list.addAll(session.createCriteria(Event.class)
                   .add(Restrictions.in("id", listEventsIds))
                   .list());
        }
        tran.commit();
        this.closeSession();
        return list;
    }    
//    
//    select * from single_event where event_id in (
//      select e.id from event e where id in 
//      (select stage_id from role_stage rs join "role" r on rs.role_id = r.role_id where r.singer_id = 6
//      union
//      select consert_id from concert_singer where singer_id = 6
//      )
//    )  + datetime ограничение   

    /**
     *
     * @param humanId
     * @return
     */
    public List<SingleEvent> getFutureEvents(long humanId){
        List<SingleEvent> list = new ArrayList<>();
        this.getSession();
        Transaction tran = session.beginTransaction();        
        long date = Utils.getCurrentDateWithoutTime();
        list.addAll(session.createSQLQuery(" select {se.*} from single_event se where event_id in ("
                + "select e.id from event e where id in "
                + "(select stage_id from role_stage rs join \"role\" r on rs.role_id = r.role_id where r.singer_id = :humanId "
                + "union "
                + "select concert_id from concert_singer where singer_id = :humanId) "
                + ") and single_event_datetime >= :date")
                .addEntity("se", SingleEvent.class)
                .setParameter("humanId", humanId)
                .setParameter("date", date)
                .list());
        tran.commit();
        this.closeSession();
        return list;
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
        criteria.add(Restrictions.or(Restrictions.ilike("name", key, MatchMode.ANYWHERE),
                                     Restrictions.ilike("surname", key, MatchMode.ANYWHERE),
                                     Restrictions.or(Restrictions.isNull("patronymic"),
                                                     Restrictions.ilike("patronymic", key, MatchMode.ANYWHERE))
                                    ));
        criteria.addOrder(Order.asc("surname"));
        criteria.addOrder(Order.asc("name"));
        criteria.addOrder(Order.asc("patronymic"));
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
