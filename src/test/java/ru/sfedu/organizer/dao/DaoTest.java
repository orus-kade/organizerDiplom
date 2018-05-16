/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.organizer.dao;

import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Concert;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.entity.Voices;
import ru.sfedu.organizer.entity.Role;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.entity.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.organizer.entity.Event;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
public class DaoTest {
    
    public DaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class Dao.
     */
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        long id = 0L;
//        Class cl = null;
//        Dao instance = null;
//        Optional expResult = null;
//        Optional result = instance.get(id, cl);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of delete method, of class Dao.
     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Dao instance = null;
//        instance.delete(null);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of deleteList method, of class Dao.
     */
//    @Test
//    public void testDeleteList() {
//        System.out.println("deleteList");
//        Optional<List> listItem = null;
//        Dao instance = null;
//        instance.deleteList(listItem);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of saveOrUpdate method, of class Dao.
     */
//    @Test
//    public void testSaveOrUpdate() {
//        System.out.println("saveOrUpdate");
//        Dao instance = null;
//        instance.saveOrUpdate(null);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of saveOrUpdateList method, of class Dao.
     */
//    @Test
//    public void testSaveOrUpdateList() {
//        System.out.println("saveOrUpdateList");
//        Optional<List> itemList = null;
//        Dao instance = null;
//        instance.saveOrUpdateList(itemList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getAll method, of class Dao.
     */
//    @Test
//    public void testGetAll() {
//        System.out.println("getAll");
//        Class cl = null;
//        Dao instance = null;
//        Optional<List> expResult = null;
//        Optional<List> result = instance.getAll(cl);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getAllByPage method, of class Dao.
     */
//    @Test
//    public void testGetByPage() {
//        System.out.println("getByPage");
//        Class cl = null;
//        int page = 0;
//        Dao instance = null;
//        Optional<List> expResult = null;
//        Optional<List> result = instance.getByPage(cl, page);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
//    @Test
//    public void getPage(){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Optional<List> result;
//        Dao<Human> dao = new Dao<>(session);
//        Human human = new Human();
//        human.setName("AAnameNew");
//        dao.saveOrUpdate(Optional.ofNullable(human));
//        int count = dao.countAll(Human.class);
//        System.out.println(count);
//        //result = dao.getAll(Human.class);
//        result = dao.getAllByPage(Human.class, 1, Arrays.asList("name"));
//        if (result.isPresent()){
//            List list = result.get();
//            System.out.println(list.isEmpty());
//            System.out.println(list); 
//        }        
//    }
    
    //@Test
    public void addData(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List list = new ArrayList<Human>();
        Human human;
        for (int i = 1; i<=10; i++){
            human = new Human();
            human.setBiography(MyGenerator.generateTitle() + " bio "+i);
            human.setName(MyGenerator.generateTitle()+" "+i);
            human.setSurname(MyGenerator.generateTitle()+" "+i);
            human.setPatronymic(MyGenerator.generateTitle()+""+i);
            human.setProfessions(Arrays.asList(Professions.values()));
            human.setVoice(Voices.values()[MyGenerator.generateInt(0, Voices.values().length-1)]);
            human.setBirthDate(MyGenerator.generateDateLong(new GregorianCalendar(1930, 0, 1).getTimeInMillis(), new GregorianCalendar(1990, 11, 31).getTimeInMillis()));
            human.setDeathDate(MyGenerator.generateDateLong(new GregorianCalendar(1991, 0, 1).getTimeInMillis(), new Date().getTime()));
            list.add(human);
        }
        HumanDao humanDao = new HumanDao();
        humanDao.saveOrUpdateList(Optional.ofNullable(list));
        
        List<Personage> chars;
        PersonageDao daoPers = new PersonageDao();
        OperaDao daoOpera = new OperaDao();
        List<Aria> aries;
        for (int i = 1; i<=10; i++){
            Opera opera = new Opera();
            opera.setTitle("TitleOpera "+i);
            opera.setDescription("some descr " + i);
            daoOpera.saveOrUpdate(Optional.ofNullable(opera));
            chars = new ArrayList<>();
            Personage ch1 = new Personage();
            ch1.setOpera(opera);
            ch1.setName("Character1 "+i);
            chars.add(ch1);
            
            Personage ch2 = new Personage();
            ch2.setOpera(opera);            
            ch2.setName("Character2 "+i);
            chars.add(ch2);
            
            Personage ch3 = new Personage();
            ch3.setOpera(opera);
            ch3.setName("Character3 "+i);            
            chars.add(ch3);
            
            opera.setPersonages(chars);
            
            daoOpera.saveOrUpdate(Optional.ofNullable(opera));
            
            aries = new ArrayList<>();
            for (int j = 1; j<=7; j++){
                Aria aria = new Aria();
                aria.setOpera(opera);
                aria.setPosition(j);
                aria.setText("some " +j+ " text " +j);
                aria.setTitle(" " + j + " Aria title  " + i);
                List<Personage> charsAria = new ArrayList<>();
                charsAria.add(daoPers.getById(MyGenerator.generateLong((i-1)*3+1, i*3-1)).get());                
                charsAria.add(daoPers.getById(i*3).get());
                aria.setPersonages(charsAria);
                List<Human> humans = new ArrayList<>();
                humans.add(humanDao.getById((long) MyGenerator.generateLong(1, 5)).get());
                humans.add(humanDao.getById((long) MyGenerator.generateLong(6, 10)).get());
                aria.setComposers(humans);
                humans = new ArrayList<>();
                humans.add(humanDao.getById((long) MyGenerator.generateLong(1, 5)).get());
                humans.add(humanDao.getById((long) MyGenerator.generateLong(6, 10)).get());
                aria.setWriters(humans);
                aries.add(aria);
            }
            Libretto libretto = new Libretto();
            libretto.setText(i+" some libretto text");
            List<Human> humans = new ArrayList<>();
            humans.add(humanDao.get((long) i).get());
            libretto.setWriters(humans);
            opera.setLibretto(libretto);
            opera.setAries(aries);
            daoOpera.saveOrUpdate(Optional.ofNullable(opera));
        } 
        PlaceDao placeDao = new PlaceDao();
        List<Place> places = new ArrayList<>();
        for(int i = 1; i<=10; i++){
            Place place = new Place();
            place.setTitle(MyGenerator.generateTitle()+ " " + i);
            List<Human> humans = new ArrayList<>();
            humans.add(humanDao.getById(MyGenerator.generateLong(1L, 5L)).get());
            humans.add(humanDao.getById(MyGenerator.generateLong(6L, 10L)).get());

            place.setPersons(humans);
            place.setLocation("location some " + i);
            places.add(place);
        }
        placeDao.saveOrUpdateList(Optional.ofNullable(places));
        AriaDao ariaDao = new AriaDao();
        ConcertDao concertDao = new ConcertDao();
        List<Concert> concerts = new ArrayList<>();
        for (int i=1; i<=10; i++){
            Concert concert = new Concert();
            aries = new ArrayList<>();
            for (int j=0; j<=5; j++){
                aries.add(ariaDao.getById(MyGenerator.generateLong(1, 70)).get());
            }
            concert.setAries(aries);
            concert.setDescription(MyGenerator.generateTitle() + " desc " + i);
            concert.setDirector(humanDao.getById(MyGenerator.generateLong(1, 10)).get());
            List<Human> humans = new ArrayList<>();
            humans.add(humanDao.getById(MyGenerator.generateLong(1L, 10L)).get());
            humans.add(humanDao.getById(MyGenerator.generateLong(1L, 10L)).get());
            humans.add(humanDao.getById(MyGenerator.generateLong(1L, 10L)).get());
            concert.setSingers(humans);
            concerts.add(concert);
        }
        concertDao.saveOrUpdateList(Optional.ofNullable(concerts));
        StageDao stageDao = new StageDao();
        List<Stage> stages = new ArrayList<>();
        for (int i=1; i<=10; i++){
            Stage stage = new Stage();
            aries = new ArrayList<>();
            for (int j=1; j<=5; j++){
                aries.add(ariaDao.getById(MyGenerator.generateLong(1, 70)).get());
            }
            stage.setOpera(daoOpera.getById(MyGenerator.generateLong(1, 10)).get());
            stage.setDescription(MyGenerator.generateTitle() + " desc " + i);
            stage.setDirector(humanDao.getById(MyGenerator.generateLong(1, 10)).get());
            stages.add(stage);
        }
        stageDao.saveOrUpdateList(Optional.ofNullable(stages));
        EventDao eventDao = new EventDao();
        SingleEventDao singleEventDao = new SingleEventDao();
        List<SingleEvent> singleEvents = new ArrayList<>();
        for (int i = 1; i<=15 ; i++){
            SingleEvent singleEvent = new SingleEvent();
            singleEvent.setDatetime(MyGenerator.generateDateLong(new GregorianCalendar(2015, 0, 1).getTimeInMillis(), new GregorianCalendar(2020, 11, 31).getTimeInMillis()));
            singleEvent.setPlace(placeDao.getById(MyGenerator.generateLong(1, 10)).get());
            singleEvent.setDescription(MyGenerator.generateTitle() + " desc " + i);
            singleEvent.setEvent(eventDao.getById(MyGenerator.generateLong(1, 20)).get());
            singleEvents.add(singleEvent);
        }
        singleEventDao.saveOrUpdateList(Optional.ofNullable(singleEvents));
        RoleDao roleDao = new RoleDao();
        List<Role> roles = new ArrayList<>();
        for (int i = 1; i<=30; i++){
            Role role = new Role();
            role.setSinger(humanDao.getById(MyGenerator.generateLong(1L, 10L)).get());
            role.setPersonage(daoPers.getById(i).get());
            roles.add(role);
        }
        roleDao.saveOrUpdateList(Optional.ofNullable(roles));
        
        stages = new ArrayList<>();
        for (int i = 11; i<=20; i++){
            Stage stage = stageDao.getById(i).get();
            long operaId = stage.getOpera().getId();
            List<Role> rs = new ArrayList<>();
            rs.add(roleDao.getById(operaId*3).get());
            rs.add(roleDao.getById(operaId*3-1).get());
            rs.add(roleDao.getById(operaId*3-2).get());
            stage.setRoles(rs);
            stages.add(stage);
        }
        stageDao.saveOrUpdateList(Optional.ofNullable(stages));

//        session.clear();    
//        System.out.println("olololololo");
//        PersonageDao daoPers = new PersonageDao(session);
//        Personage pers = daoPers.getById(1L).get();
//        System.out.println("olololololo");
//        System.out.println(pers.getOpera());
//        Aria a = new AriaDao(session).getById(1L).get();
//        System.out.println(a.getPersonages());
//        Opera o = daoOpera.getById(1L).get();
//        //System.out.println(a.getPersonages());
        session.close();
    }
    
    //@Test 
    public void anotherTest(){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        AriaDao ariaDao = new AriaDao();
//        Aria aria = ariaDao.getById(1L).get();
//        Event event = new Event();
//        EventDao eventDao = new EventDao();
//        event = eventDao.getById(1L).get();
//        System.out.println(event.getClass().getName());
//        System.out.println("olo");        
        //Hibernate.isInitialized(aria);
        //System.out.println(Hibernate.isPropertyInitialized(aria, "composers"));
//        session.close();  
          Set prof = new HashSet<Professions>();
          prof.add(Professions.COMPOSER);
          prof.add(Professions.COMPOSER);
          prof.add(Professions.COMPOSER);
    }
}
