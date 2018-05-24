/*
 */
package ru.sfedu.organizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlaceDaoTest {
    
    private static SessionFactory sessionFactory; 
    private static HumanDao humanDao = new HumanDao();
    private static List<Human> humans = new ArrayList<>();
    private static PlaceDao dao = new PlaceDao();
    private static Place place = new Place();
    
    public PlaceDaoTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.setUpClass()");
        sessionFactory = HibernateUtil.getSessionFactory();
        Human human1 = new Human();
        human1.setBiography(MyGenerator.generateTitle());
        human1.setName(MyGenerator.generateTitle());
        human1.setSurname(MyGenerator.generateTitle());
        human1.setBirthDate(MyGenerator.generateDateLong(new GregorianCalendar(1930, 0, 1).getTimeInMillis(), new GregorianCalendar(1990, 11, 31).getTimeInMillis()));
        human1.setDeathDate(MyGenerator.generateDateLong(new GregorianCalendar(1991, 0, 1).getTimeInMillis(), new Date().getTime()));
        Human human2 = new Human();
        human2.setBiography(MyGenerator.generateTitle());
        human2.setName(MyGenerator.generateTitle());
        human2.setSurname(MyGenerator.generateTitle());
        human2.setBirthDate(MyGenerator.generateDateLong(new GregorianCalendar(1930, 0, 1).getTimeInMillis(), new GregorianCalendar(1990, 11, 31).getTimeInMillis()));
        human2.setDeathDate(MyGenerator.generateDateLong(new GregorianCalendar(1991, 0, 1).getTimeInMillis(), new Date().getTime()));
        humans.addAll(Arrays.asList(human1, human2));
        humanDao.saveOrUpdateList(humans);
        place.setDescription(MyGenerator.generateTitle());
        place.setLocation(MyGenerator.generateTitle());
        place.setTitle(MyGenerator.generateTitle());
        place.setPersons(Arrays.asList(humans.get(0))); 
    }
    
    @AfterClass
    public static void tearDownClass() {
        humanDao.deleteList(humans);
        sessionFactory.close();
    }

     @Test
    public void aTestAdd(){
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.aTestAdd()");
        System.out.println(place);
        System.out.println("Search place before save");
        Optional<Place> o = dao.getById(place.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(place);
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.aTestAdd() : OK");
    }
    
    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.btestGetById()");
        Optional<Place> o = dao.getById(place.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(place));
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.btestGetById() : OK");
    }


    @Test
    public void cTestEdit() {
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.cTestEdit()");
        Place newPlace = dao.getById(place.getId()).get();
        newPlace.setDescription(MyGenerator.generateTitle());
        newPlace.setLocation(MyGenerator.generateTitle());
        newPlace.setTitle(MyGenerator.generateTitle());
        newPlace.setPersons(Arrays.asList(humans.get(1)));
        System.out.println("Edited place: " + newPlace);
        dao.saveOrUpdate(newPlace);
        Place getNewPlace = dao.getById(place.getId()).get();
        assertTrue(newPlace.equals(getNewPlace));
        assertFalse(getNewPlace.equals(place));
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.cTestEdit() : OK");
    }

    @Test
    public void dTestDelete() {
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.dTestDelete()");
        dao.delete(place);
        Optional<Place> o = dao.getById(place.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.PlaceDaoTest.dTestDelete() : OK");
    }
    
}
