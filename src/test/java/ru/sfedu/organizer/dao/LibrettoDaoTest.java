/*
 */
package ru.sfedu.organizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
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
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.entity.Role;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.entity.Voices;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LibrettoDaoTest {
    
    private static SessionFactory sessionFactory; 
    private static OperaDao operaDao = new OperaDao();
    private static HumanDao humanDao = new HumanDao();
    private static Opera opera = new Opera();
    private static List<Human> humans = new ArrayList<>();
    private static Libretto libretto;
    private static LibrettoDao dao = new LibrettoDao();
    
    public LibrettoDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.setUpClass()");
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
        libretto = new Libretto();
        libretto.setOpera(opera);
        libretto.setWriters(Arrays.asList(humans.get(0)));
        libretto.setText(MyGenerator.generateTitle());
    }
    
    @AfterClass
    public static void tearDownClass() {
        opera = operaDao.getById(opera.getId()).get();
        operaDao.delete(opera);
        humanDao.deleteList(humans);
        sessionFactory.close();
    }
    
    @Test
    public void aTestAdd(){
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.aTestAdd()");
        System.out.println(libretto);
        System.out.println("Search aria before save");
        Optional<Libretto> o = dao.getById(libretto.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(libretto);
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.aTestAdd()) : OK");
    }
    
    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.btestGetById()");
        Optional<Libretto> o = dao.getById(libretto.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(libretto));
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.btestGetById() : OK");
    }


    @Test
    public void cTestEdit() {
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.cTestEdit()");
        Libretto newLibretto = dao.getById(libretto.getId()).get();
        newLibretto.setWriters(Arrays.asList(humans.get(1)));
        newLibretto.setText(MyGenerator.generateTitle());
        System.out.println("Edited aria: " + newLibretto);
        dao.saveOrUpdate(newLibretto);
        Libretto getNewLibretto = dao.getById(libretto.getId()).get();
        assertTrue(newLibretto.equals(getNewLibretto));
        assertFalse(getNewLibretto.equals(libretto));
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.cTestEdit() : OK");
    }

    @Test
    public void dTestDelete() {
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.dTestDelete()");
        dao.delete(libretto);
        Optional<Libretto> o = dao.getById(libretto.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.LibrettoDaoTest.dTestDelete() : OK");
    }
    
    
    //@Test
    public void addData() {
            List<Human> list = new ArrayList<>();
            Human human;
            for (int i = 1; i <= 10; i++) {
                human = new Human();
                human.setBiography(MyGenerator.generateTitle() + " bio " + i);
                human.setName(MyGenerator.generateTitle() + " " + i);
                human.setSurname(MyGenerator.generateTitle() + " " + i);
                human.setPatronymic(MyGenerator.generateTitle() + "" + i);
                human.setProfessions(Arrays.asList(Professions.values()));
                human.setVoice(Voices.values()[MyGenerator.generateInt(0, Voices.values().length - 1)]);
                human.setBirthDate(MyGenerator.generateDateLong(new GregorianCalendar(1930, 0, 1).getTimeInMillis(), new GregorianCalendar(1990, 11, 31).getTimeInMillis()));
                human.setDeathDate(MyGenerator.generateDateLong(new GregorianCalendar(1991, 0, 1).getTimeInMillis(), new Date().getTime()));
                list.add(human);
            }
            HumanDao humanDao = new HumanDao();
            humanDao.saveOrUpdateList(list);
            List<Personage> chars;
            PersonageDao daoPers = new PersonageDao();
            OperaDao daoOpera = new OperaDao();
            List<Aria> aries;
            for (int i = 1; i <= 10; i++) {
                Opera opera = new Opera();
                opera.setTitle("TitleOpera " + i);
                opera.setDescription("some descr " + i);
                daoOpera.saveOrUpdate(opera);
                chars = new ArrayList<>();
                Personage ch1 = new Personage();
                ch1.setOpera(opera);
                ch1.setName("Character1 " + i);
                chars.add(ch1);

                Personage ch2 = new Personage();
                ch2.setOpera(opera);
                ch2.setName("Character2 " + i);
                chars.add(ch2);

                Personage ch3 = new Personage();
                ch3.setOpera(opera);
                ch3.setName("Character3 " + i);
                chars.add(ch3);

                opera.setPersonages(chars);

                daoOpera.saveOrUpdate(opera);

                aries = new ArrayList<>();
                for (int j = 1; j <= 7; j++) {
                    Aria aria = new Aria();
                    aria.setOpera(opera);
                    aria.setPosition(j);
                    aria.setText("some " + j + " text " + j);
                    aria.setTitle(" " + j + " Aria title  " + i);
                    List<Personage> charsAria = new ArrayList<>();
                    charsAria.add(daoPers.getById(MyGenerator.generateLong((i - 1) * 3 + 1, i * 3 - 1)).get());
                    charsAria.add(daoPers.getById(i * 3).get());
                    aria.setPersonages(charsAria);
                    List<Human> humans = new ArrayList<>();
                    humans.add(humanDao.getById((long) MyGenerator.generateLong(1, 5)).get());
                    humans.add(humanDao.getById((long) MyGenerator.generateLong(8, 10)).get());
                    aria.setComposers(humans);
                    humans = new ArrayList<>();
                    humans.add(humanDao.getById((long) MyGenerator.generateLong(1, 5)).get());
                    humans.add(humanDao.getById((long) MyGenerator.generateLong(8, 10)).get());
                    aria.setWriters(humans);
                    aries.add(aria);
                }
                Libretto libretto = new Libretto();
                libretto.setText(i + " some libretto text");
                List<Human> humans = new ArrayList<>();
                humans.add(humanDao.get((long) i).get());
                libretto.setWriters(humans);
                opera.setLibretto(libretto);
                opera.setAries(aries);
                daoOpera.saveOrUpdate(opera);
            }
            PlaceDao placeDao = new PlaceDao();
            List<Place> places = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Place place = new Place();
                place.setTitle(MyGenerator.generateTitle() + " " + i);
                List<Human> humans = new ArrayList<>();
                humans.add(humanDao.getById(MyGenerator.generateLong(1L, 5L)).get());
                humans.add(humanDao.getById(MyGenerator.generateLong(8L, 10L)).get());

                place.setPersons(humans);
                place.setLocation("location some " + i);
                places.add(place);
            }
            placeDao.saveOrUpdateList(places);
            AriaDao ariaDao = new AriaDao();
            ConcertDao concertDao = new ConcertDao();
            List<Concert> concerts = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Concert concert = new Concert();
                aries = new ArrayList<>();
                for (int j = 0; j <= 5; j++) {
                    aries.add(ariaDao.getById(MyGenerator.generateLong(1, 70)).get());
                }
                concert.setAries(aries);
                concert.setDescription(MyGenerator.generateTitle() + " desc " + i);
                concert.setDirector(humanDao.getById(MyGenerator.generateLong(1, 10)).get());
                List<Human> humans = new ArrayList<>();
                humans.add(humanDao.getById(MyGenerator.generateLong(1L, 6L)).get());
                humans.add(humanDao.getById(MyGenerator.generateLong(1L, 6L)).get());
                humans.add(humanDao.getById(MyGenerator.generateLong(1L, 6L)).get());
                concert.setSingers(humans);
                concert.setTitle("Consert " + i);
                concerts.add(concert);
            }
            concertDao.saveOrUpdateList(concerts);
            StageDao stageDao = new StageDao();
            List<Stage> stages = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Stage stage = new Stage();
                aries = new ArrayList<>();
                for (int j = 1; j <= 5; j++) {
                    aries.add(ariaDao.getById(MyGenerator.generateLong(1, 70)).get());
                }
                stage.setOpera(daoOpera.getById(MyGenerator.generateLong(1, 10)).get());
                stage.setDescription(MyGenerator.generateTitle() + " desc " + i);
                stage.setDirector(humanDao.getById(MyGenerator.generateLong(1, 6)).get());
                stage.setTitle("Stage " + i + " " + stage.getOpera().getTitle());
                stages.add(stage);
            }
            stageDao.saveOrUpdateList(stages);
            EventDao eventDao = new EventDao();
            SingleEventDao singleEventDao = new SingleEventDao();
            List<SingleEvent> singleEvents = new ArrayList<>();
            for (int i = 1; i <= 15; i++) {
                SingleEvent singleEvent = new SingleEvent();
                singleEvent.setDatetime(MyGenerator.generateDateLong(new GregorianCalendar(2015, 0, 1).getTimeInMillis(), new GregorianCalendar(2020, 11, 31).getTimeInMillis()));
                singleEvent.setPlace(placeDao.getById(MyGenerator.generateLong(1, 10)).get());
                singleEvent.setDescription(MyGenerator.generateTitle() + " desc " + i);
                singleEvent.setEvent(eventDao.getById(MyGenerator.generateLong(1, 20)).get());
                singleEvents.add(singleEvent);
            }
            singleEventDao.saveOrUpdateList(singleEvents);
            RoleDao roleDao = new RoleDao();
            List<Role> roles = new ArrayList<>();
            for (int i = 1; i <= 30; i++) {
                Role role = new Role();
                role.setSinger(humanDao.getById(MyGenerator.generateLong(1L, 6L)).get());
                role.setPersonage(daoPers.getById(i).get());
                roles.add(role);
            }
            roleDao.saveOrUpdateList(roles);
            stages = new ArrayList<>();
            for (int i = 11; i <= 20; i++) {
                Stage stage = stageDao.getById(i).get();
                long operaId = stage.getOpera().getId();
                List<Role> rs = new ArrayList<>();
                rs.add(roleDao.getById(operaId * 3).get());
                rs.add(roleDao.getById(operaId * 3 - 1).get());
                rs.add(roleDao.getById(operaId * 3 - 2).get());
                stage.setRoles(rs);
                stages.add(stage);
            }
            stageDao.saveOrUpdateList(stages);
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
        
    }
    
}
