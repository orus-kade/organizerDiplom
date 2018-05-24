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
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.entity.Voices;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HumanDaoTest {

    private static SessionFactory sessionFactory;
    private static HumanDao dao = new HumanDao();
    private static PlaceDao placeDao = new PlaceDao();
    private static List<Place> places = new ArrayList<>();
    private static Human human;

    public HumanDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.setUpClass()");
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
        human = new Human();
        human.setBiography(MyGenerator.generateTitle());
        human.setName(MyGenerator.generateTitle());
        human.setSurname(MyGenerator.generateTitle());
        human.setPatronymic(MyGenerator.generateTitle());
        human.setProfessions(Arrays.asList(Professions.values()));
        human.setVoice(Voices.values()[MyGenerator.generateInt(0, Voices.values().length - 1)]);
        human.setBirthDate(MyGenerator.generateDateLong(new GregorianCalendar(1930, 0, 1).getTimeInMillis(), new GregorianCalendar(1990, 11, 31).getTimeInMillis()));
        human.setDeathDate(MyGenerator.generateDateLong(new GregorianCalendar(1991, 0, 1).getTimeInMillis(), new Date().getTime()));
        human.setPlaces(Arrays.asList(places.get(0)));
    }

    @AfterClass
    public static void tearDownClass() {
        placeDao.deleteList(places);
        sessionFactory.close();
    }

    @Test
    public void aTestAdd(){
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.aTestAdd()");
        System.out.println(human);
        System.out.println("Search human before save");
        Optional<Human> o = dao.getById(human.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(human);
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.aTestAdd() : OK");
    }
    
    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.bTestGetById()");
        Optional<Human> o = dao.getById(human.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(human));
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.bTestGetById() : OK");
    }


    @Test
    public void cTestEdit() {
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.cTestEdit()");
        Human newHuman = dao.getById(human.getId()).get();
        newHuman.setBiography(MyGenerator.generateTitle());
        newHuman.setName(MyGenerator.generateTitle());
        newHuman.setSurname(MyGenerator.generateTitle());
        newHuman.setPatronymic(MyGenerator.generateTitle());
        newHuman.setVoice(Voices.values()[MyGenerator.generateInt(0, Voices.values().length - 1)]);
        newHuman.setBirthDate(MyGenerator.generateDateLong(new GregorianCalendar(1930, 0, 1).getTimeInMillis(), new GregorianCalendar(1990, 11, 31).getTimeInMillis()));
        newHuman.setDeathDate(MyGenerator.generateDateLong(new GregorianCalendar(1991, 0, 1).getTimeInMillis(), new Date().getTime()));
        newHuman.setPlaces(Arrays.asList(places.get(1)));
        System.out.println("Edited human: " + newHuman);
        dao.saveOrUpdate(newHuman);
        Human getNewHuman = dao.getById(human.getId()).get();
        assertTrue(newHuman.equals(getNewHuman));
        assertFalse(getNewHuman.equals(human));
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.cTestEdit() : OK");
    }

    @Test
    public void dTestDelete() {
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.dTestDelete()");
        dao.delete(human);
        Optional<Human> o = dao.getById(human.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.HumanDaoTest.dTestDelete() : OK");
    }
}
