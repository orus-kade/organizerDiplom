/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.organizer.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.hibernate.Session;
import ru.sfedu.organizer.model.*;


/**
 *
 * @author sterie
 */
public class HibernateUtilTest {
    
    public HibernateUtilTest() {
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
     * Test of getSessionFactory method, of class HibernateUtil.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory expResult = null;
        SessionFactory result = HibernateUtil.getSessionFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void test(){
        System.out.println("test");
        Session session =  HibernateUtil.getSessionFactory().openSession();
        List list = session.getNamedQuery("GET_TABLES").getResultList();
        System.out.println(list);
        list = new ArrayList<Human>();
        Human human;
        for (int i = 1; i<=10; i++){
            human = new Human();
            human.setBiography("bio"+i);
            human.setName("Name"+i);
            human.setSurname("Surname"+i);
            human.setPatronymic("Patr"+i);
            human.setProfessions(new HashSet<Professions>(Arrays.asList(Professions.COMPOSER, Professions.DIRECTOR, Professions.SINGER, Professions.WRITER)));
            human.setVoice(Voices.TENOR);
            human.setBirthDate(new GregorianCalendar(1980, i, i).getTimeInMillis());
            human.setDeathDate(new GregorianCalendar(2016, 11-i, 11-i).getTimeInMillis());
            list.add(human);
        }
        //human = session.get(Human.class, (long) 11);
        //System.out.println(human);
        session.beginTransaction();
        //session.delete(human);
        list.forEach(e -> session.save(e));
        session.getTransaction().commit();
        session.close();
    }   
    
    @Test
    public void t2(){
        Session session =  HibernateUtil.getSessionFactory().openSession();
        Human human = new Human();
        
        //human.setId((long) 4);
        
        human = session.get(Human.class, (long) 5);
        
//        System.out.println(human);
//        
//        session.beginTransaction();
//        session.delete(human);
//        session.delete(human);
        //session.getTransaction().commit();
        //System.out.println(human);
        System.out.println(Optional.ofNullable(human));
        System.out.println(Optional.ofNullable(human).get());
        
        session.close();
    }
    
    
}
