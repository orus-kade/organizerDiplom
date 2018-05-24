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
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.entity.Role;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StageDaoTest {
    
    private static SessionFactory sessionFactory; 
    private static StageDao dao = new StageDao();
    private static Stage stage = new Stage();
    private static OperaDao operaDao = new OperaDao();
    private static Opera opera = new Opera();
    private static List<Human> humans = new ArrayList<>();
    private static HumanDao humanDao = new HumanDao();
    
    public StageDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.setUpClass()");
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
        stage.setDescription(MyGenerator.generateTitle());
        stage.setTitle(MyGenerator.generateTitle());
        stage.setDirector(humans.get(0));
        stage.setOpera(opera);
        Role role = new Role();
        role.setSinger(humans.get(0));
        role.setPersonage(opera.getPersonages().get(0));
        stage.setRoles(Arrays.asList(role));
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
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.aTestAdd()");
        System.out.println(stage);
        System.out.println("Search stage before save");
        Optional<Stage> o = dao.getById(stage.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(stage);
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.aTestAdd() : OK");
    }
    
    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.btestGetById()");
        Optional<Stage> o = dao.getById(stage.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(stage));
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.btestGetById() : OK");
    }


    @Test
    public void cTestEdit() {
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.cTestEdit()");
        Stage newStage = dao.getById(stage.getId()).get();
        newStage.setDescription(MyGenerator.generateTitle());
        newStage.setTitle(MyGenerator.generateTitle());
        newStage.setDirector(humans.get(1));
        newStage.setOpera(opera);
        Role role = new Role();
        role.setSinger(humans.get(1));
        role.setPersonage(opera.getPersonages().get(1));
        newStage.setRoles(Arrays.asList(role));
        System.out.println("Edited stage: " + newStage);
        dao.saveOrUpdate(newStage);
        Stage getNewStage = dao.getById(stage.getId()).get();
        assertTrue(newStage.equals(getNewStage));
        assertFalse(getNewStage.equals(stage));
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.cTestEdit() : OK");
    }

    @Test
    public void dTestDelete() {
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.dTestDelete()");
        dao.delete(stage);
        Optional<Stage> o = dao.getById(stage.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.StageDaoTest.dTestDelete() : OK");
    }
    
}
