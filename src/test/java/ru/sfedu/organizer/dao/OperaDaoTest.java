/*
 */
package ru.sfedu.organizer.dao;

import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Professions;

/**
 *
 * @author sterie
 */
public class OperaDaoTest {
    
    public OperaDaoTest() {
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
     * Test of getById method, of class OperaDao.
     */
    //@Test
    public void testGetById() {
        System.out.println("getById");
        long id = 0L;
        OperaDao instance = new OperaDao();
        Optional<Opera> expResult = null;
        Optional<Opera> result = instance.getById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class OperaDao.
     */
    //@Test
    public void testCount() {
        System.out.println("count");
        OperaDao instance = new OperaDao();
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class OperaDao.
     */
    //@Test
    public void testGetAll() {
        System.out.println("getAll");
        OperaDao instance = new OperaDao();
        Optional<List> expResult = null;
        Optional<List> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllByPage method, of class OperaDao.
     */
    //@Test
//    public void testGetAllByPage() {
//        System.out.println("getAllByPage");
//        int page = 0;
//        OperaDao instance = new OperaDao();
//        Optional<List> expResult = null;
//        Optional<List> result = instance.getAllByPage(page);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getHumansByProfession method, of class OperaDao.
     */
    @Test
    public void testGetHumansByProfession() {
        System.out.println("getHumansByProfession");
        Opera opera = null;
        Professions profession = Professions.SINGER;
        OperaDao instance = new OperaDao();
        opera = instance.getById(1L).get();
        //List<Human> expResult = null;
        List<Human> result = instance.getHumansByProfession(opera, profession);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        result.stream().forEach(System.out::println);
    }
    
}
