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
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AriaDaoTest {
    
    private static SessionFactory sessionFactory; 
    private static Aria aria = new Aria();
    private static AriaDao dao = new AriaDao();
    private static OperaDao operaDao = new OperaDao();
    private static HumanDao humanDao = new HumanDao();
    private static Opera opera = new Opera();
    private static List<Human> humans = new ArrayList<>();
    
    public AriaDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.setUpClass()");
        sessionFactory = HibernateUtil.getSessionFactory();
        opera.setTitle(MyGenerator.generateTitle());
        opera.setDescription(MyGenerator.generateTitle());
        Personage ch1 = new Personage();
        ch1.setOpera(opera);
        ch1.setName(MyGenerator.generateTitle());
        Personage ch2 = new Personage();
        ch2.setOpera(opera);
        ch2.setName(MyGenerator.generateTitle());
        opera.setPersonages(Arrays.asList(ch2, ch1));
        Libretto libretto = new Libretto();
        libretto.setOpera(opera);
        libretto.setText(MyGenerator.generateTitle());
        opera.setLibretto(libretto);
        operaDao.saveOrUpdate(opera);
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
        aria.setTitle(MyGenerator.generateTitle());
        aria.setText(MyGenerator.generateTitle());
        aria.setPosition(1);
        aria.setOpera(opera);
        aria.setPersonages(Arrays.asList(opera.getPersonages().get(0)));
        aria.setComposers(Arrays.asList(humans.get(0)));
        aria.setWriters(Arrays.asList(humans.get(0)));
    }
    
    @AfterClass
    public static void tearDownClass() {
        humanDao.deleteList(humans);
        operaDao.delete(opera);
        sessionFactory.close();
    }

    @Test
    public void aTestAdd(){
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.aTestAdd()");
        System.out.println(aria);
        System.out.println("Search aria before save");
        Optional<Aria> o = dao.getById(aria.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(aria);
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.aTestAdd() : OK");
    }
    
    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.btestGetById()");
        Optional<Aria> o = dao.getById(aria.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(aria));
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.btestGetById() : OK");
    }


    @Test
    public void cTestEdit() {
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.cTestEdit()");
        Aria newAria = dao.getById(aria.getId()).get();
        newAria.setTitle(MyGenerator.generateTitle());
        newAria.setText(MyGenerator.generateTitle());
        newAria.setPosition(2);
        newAria.setPersonages(Arrays.asList(opera.getPersonages().get(1)));
        newAria.setComposers(Arrays.asList(humans.get(1)));
        newAria.setWriters(Arrays.asList(humans.get(1)));
        System.out.println("Edited aria: " + newAria);
        dao.saveOrUpdate(newAria);
        Aria getNewAria = dao.getById(aria.getId()).get();
        assertTrue(newAria.equals(getNewAria));
        assertFalse(getNewAria.equals(aria));
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.cTestEdit() : OK");
    }

    @Test
    public void dTestDelete() {
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.dTestDelete()");
        dao.delete(aria);
        Optional<Aria> o = dao.getById(aria.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.AriaDaoTest.dTestDelete() : OK");
    }    
}
