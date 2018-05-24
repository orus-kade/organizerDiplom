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
import ru.sfedu.organizer.entity.Concert;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConcertDaoTest {
    
    private static SessionFactory sessionFactory; 
    private static ConcertDao dao = new ConcertDao();
    private static Concert concert = new Concert();
    private static HumanDao humanDao = new HumanDao();
  //  private static AriaDao ariaDao = new AriaDao();
    private static List<Human> humans = new ArrayList<>();
    private static List<Aria> aries = new ArrayList<>();
    private static OperaDao operaDao = new OperaDao();
    private static Opera opera = new Opera();
    
    
    public ConcertDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.setUpClass()");
        sessionFactory = HibernateUtil.getSessionFactory();
        Human human1 = new Human();
        human1.setBiography(MyGenerator.generateTitle());
        human1.setName(MyGenerator.generateTitle());
        human1.setSurname(MyGenerator.generateTitle());
        human1.setBirthDate(MyGenerator.generateDateLong(new GregorianCalendar(1930, 0, 1).getTimeInMillis(), new GregorianCalendar(1990, 11, 31).getTimeInMillis()));
        human1.setDeathDate(MyGenerator.generateDateLong(new GregorianCalendar(1991, 0, 1).getTimeInMillis(), new Date().getTime()));
        human1.setProfessions(Arrays.asList(Professions.SINGER, Professions.DIRECTOR));
        Human human2 = new Human();
        human2.setBiography(MyGenerator.generateTitle());
        human2.setName(MyGenerator.generateTitle());
        human2.setSurname(MyGenerator.generateTitle());
        human2.setBirthDate(MyGenerator.generateDateLong(new GregorianCalendar(1930, 0, 1).getTimeInMillis(), new GregorianCalendar(1990, 11, 31).getTimeInMillis()));
        human2.setDeathDate(MyGenerator.generateDateLong(new GregorianCalendar(1991, 0, 1).getTimeInMillis(), new Date().getTime()));
        human2.setProfessions(Arrays.asList(Professions.SINGER, Professions.DIRECTOR));
        humans.addAll(Arrays.asList(human1, human2));
        humanDao.saveOrUpdateList(humans);
        opera.setTitle(MyGenerator.generateTitle());
        opera.setDescription(MyGenerator.generateTitle());
        Aria aria1 = new Aria();
        aria1.setTitle(MyGenerator.generateTitle());
        aria1.setText(MyGenerator.generateTitle());
        aria1.setPosition(1);
        aria1.setOpera(opera);
        Aria aria2 = new Aria();
        aria2.setTitle(MyGenerator.generateTitle());
        aria2.setText(MyGenerator.generateTitle());
        aria2.setPosition(2);
        aria2.setOpera(opera);
        aries.addAll(Arrays.asList(aria1, aria2));
        opera.setAries(aries);
        operaDao.saveOrUpdate(opera);
        concert.setDescription(MyGenerator.generateTitle());
        concert.setTitle(MyGenerator.generateTitle());
        concert.setDirector(humans.get(0));
        concert.setAries(Arrays.asList(aries.get(0)));
        concert.setSingers(Arrays.asList(humans.get(0)));  
    }
    
    @AfterClass
    public static void tearDownClass() {
        operaDao.delete(opera);
        humanDao.deleteList(humans);
        sessionFactory.close();
    }

    @Test
    public void aTestAdd() {
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.aTestAdd()");
        System.out.println(concert);
        System.out.println("Search concert before save");
        Optional<Concert> o = dao.getById(concert.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(concert);
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.aTestAdd() : OK");
    }

    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.bTestGetById()");
        Optional<Concert> o = dao.getById(concert.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(concert));
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.bTestGetById() : OK");
    }

    @Test
    public void cTestEdit() {
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.cTestEdit()");
        Concert newConcert = dao.getById(concert.getId()).get();
        newConcert.setTitle(MyGenerator.generateTitle());
        newConcert.setDescription(MyGenerator.generateTitle());
        newConcert.setDirector(humans.get(1));
        newConcert.setSingers(Arrays.asList(humans.get(1)));
        newConcert.setAries(Arrays.asList(aries.get(1)));
        System.out.println("Edited concert: " + newConcert);
        dao.saveOrUpdate(newConcert);
        Concert getNewConcert = dao.getById(concert.getId()).get();
        assertTrue(newConcert.equals(getNewConcert));
        assertFalse(getNewConcert.equals(concert));
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.cTestEdit() : OK");
    }
    @Test
    public void dTestDelete() {
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.dTestDelete()");
        dao.delete(concert);
        Optional<Concert> o = dao.getById(concert.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.ConcertDaoTest.dTestDelete() : OK");
    } 
}
