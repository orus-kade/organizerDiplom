/*
 */
package ru.sfedu.organizer.dao;

import java.util.Arrays;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import ru.sfedu.organizer.entity.Aria;
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
public class OperaDaoTest {
    
    private static SessionFactory sessionFactory;
    private static OperaDao dao = new OperaDao();
    private static Opera opera = new Opera();
    
    public OperaDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.setUpClass()");
        sessionFactory = HibernateUtil.getSessionFactory();
        opera.setTitle(MyGenerator.generateTitle());
        opera.setDescription(MyGenerator.generateTitle());
        Personage ch1 = new Personage();
        ch1.setOpera(opera);
        ch1.setName(MyGenerator.generateTitle());
        opera.setPersonages(Arrays.asList(ch1));
        Libretto libretto = new Libretto();
        libretto.setOpera(opera);
        libretto.setText(MyGenerator.generateTitle());
        opera.setLibretto(libretto);
        Aria aria = new Aria();
        aria.setTitle(MyGenerator.generateTitle());
        aria.setText(MyGenerator.generateTitle());
        aria.setPosition(1);
        aria.setOpera(opera);
        opera.setAries(Arrays.asList(aria));        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        sessionFactory.close();
    }

    @Test
    public void aTestAdd(){
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.aTestAdd()");
        System.out.println(opera);
        System.out.println("Search opera before save");
        Optional<Opera> o = dao.getById(opera.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(opera);
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.aTestAdd() : OK");
    }
    
    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.btestGetById()");
        Optional<Opera> o = dao.getById(opera.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(opera));
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.btestGetById() : OK");
    }


    @Test
    public void cTestEdit() {
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.cTestEdit()");
        Opera newOpera = dao.getById(opera.getId()).get();
        newOpera.setTitle(MyGenerator.generateTitle());
        newOpera.setDescription(MyGenerator.generateTitle());
        Personage ch1 = new Personage();
        ch1.setOpera(newOpera);
        ch1.setName(MyGenerator.generateTitle());
        newOpera.setPersonages(Arrays.asList(ch1));
        Libretto libretto = new Libretto();
        libretto.setOpera(newOpera);
        libretto.setText(MyGenerator.generateTitle());
        newOpera.setLibretto(libretto);
        Aria aria = new Aria();
        aria.setTitle(MyGenerator.generateTitle());
        aria.setText(MyGenerator.generateTitle());
        aria.setPosition(1);
        aria.setOpera(newOpera);
        newOpera.setAries(Arrays.asList(aria));
        System.out.println("Edited opera: " + newOpera);
        dao.saveOrUpdate(newOpera);
        Opera getNewOpera = dao.getById(opera.getId()).get();
        assertTrue(newOpera.equals(getNewOpera));
        assertFalse(getNewOpera.equals(opera));
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.cTestEdit() : OK");
    }

    @Test
    public void dTestDelete() {
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.dTestDelete()");
        dao.delete(opera);
        Optional<Opera> o = dao.getById(opera.getId());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.OperaDaoTest.dTestDelete() : OK");
    }    
}
