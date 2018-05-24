/*
 */
package ru.sfedu.organizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import ru.sfedu.organizer.entity.Concert;
import ru.sfedu.organizer.entity.Event;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SingleEventDaoTest {
    
    private static SessionFactory sessionFactory; 
    private static PlaceDao placeDao = new PlaceDao();
    private static List<Place> places = new ArrayList<>();
    private static SingleEventDao dao = new SingleEventDao();
    private static SingleEvent event = new SingleEvent();
    private static EventDao eventDao = new EventDao();
    private static List<Event> events = new ArrayList<>();
    
    public SingleEventDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.setUpClass()");
        sessionFactory = HibernateUtil.getSessionFactory();
        Place place1 = new Place();
        place1.setDescription(MyGenerator.generateTitle());
        place1.setLocation(MyGenerator.generateTitle());
        place1.setTitle(MyGenerator.generateTitle());
        Place place2 = new Place();
        place2.setDescription(MyGenerator.generateTitle());
        place2.setLocation(MyGenerator.generateTitle());
        place2.setTitle(MyGenerator.generateTitle());
        places.addAll(Arrays.asList(place1, place2));  
        placeDao.saveOrUpdateList(places);
        Concert concert = new Concert();
        concert.setDescription(MyGenerator.generateTitle());
        concert.setTitle(MyGenerator.generateTitle());     
        Stage stage = new Stage();
        stage.setDescription(MyGenerator.generateTitle());
        stage.setTitle(MyGenerator.generateTitle());
        events.addAll(Arrays.asList(concert, stage));
        eventDao.saveOrUpdateList(events);
        event.setDescription(MyGenerator.generateTitle());
        event.setEvent(events.get(0));
        event.setPlace(places.get(0));
        event.setDatetime((MyGenerator.generateDateLong()));
    }
    
    @AfterClass
    public static void tearDownClass() {
        eventDao.deleteList(events);
        placeDao.deleteList(places);
        sessionFactory.close();
    }

    @Test
    public void aTestAdd(){
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.aTestAdd()");
        System.out.println(event);
        System.out.println("Search singleEvent before save");
        Optional<SingleEvent> o = dao.getById(event.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(event);
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.aTestAdd() : OK");
    }
    
    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.bTestGetById()");
        Optional<SingleEvent> o = dao.getById(event.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(event));
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.bTestGetById() : OK");
    }


    @Test
    public void cTestEdit() {
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.cTestEdit()");
        SingleEvent newEvent = dao.getById(event.getId()).get();
        newEvent.setDescription(MyGenerator.generateTitle());
        newEvent.setEvent(events.get(1));
        newEvent.setPlace(places.get(1));
        newEvent.setDatetime((MyGenerator.generateDateLong()));
        System.out.println("Edited singleEvent: " + newEvent);
        dao.saveOrUpdate(newEvent);
        SingleEvent getNewEvent = dao.getById(event.getId()).get();
        assertTrue(newEvent.equals(getNewEvent));
        assertFalse(getNewEvent.equals(event));
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.cTestEdit() : OK");
    }

    @Test
    public void dTestDelete() {
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.dTestDelete()");
        dao.delete(event);
        Optional<SingleEvent> o = dao.getById(event.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.SingleEventDaoTest.dTestDelete() : OK");
    }
    
}
