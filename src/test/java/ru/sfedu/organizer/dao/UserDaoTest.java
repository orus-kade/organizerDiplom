/*
 */
package ru.sfedu.organizer.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import ru.sfedu.organizer.entity.Note;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.entity.User;
import ru.sfedu.organizer.entity.UserRoles;
import ru.sfedu.organizer.utils.HibernateUtil;
import ru.sfedu.organizer.utils.MyGenerator;

/**
 *
 * @author sterie
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
    
    private static SessionFactory sessionFactory; 
    private static Opera opera;
    private static User user = new User();
    private static UserDao dao = new UserDao();
    private static Note note = new Note();
    private static NoteDao noteDao = new NoteDao();
    private static SingleEvent event;
    
    public UserDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        sessionFactory = HibernateUtil.getSessionFactory();
        Human human = ((List<Human>)(new HumanDao().getByRange(1, 1).get())).get(0);
        opera = ((List<Opera>)(new OperaDao().getByRange(1, 1).get())).get(0);
        event = ((List<SingleEvent>)(new SingleEventDao().getByRange(1, 1).get())).get(0);
        user.setCreateDate(new Date().getTime());
        user.setLogin(MyGenerator.generateTitle());
        user.setRoles(Arrays.asList(UserRoles.USER));
        user.setPassword("pp");
        user.setSalt(new byte[16]);
        note.setCreateDate(new Date().getTime());
        note.setDescription(MyGenerator.generateTitle());
        note.setObjectType(ObjectTypes.valueOf(human.getClass().getSimpleName().toUpperCase()));
        note.setObjectId(human.getId());
        note.setTitle(MyGenerator.generateTitle());
        note.setUpdateDate(note.getCreateDate());
    }
    
    @AfterClass
    public static void tearDownClass() {
        sessionFactory.close();
    }

    @Test
    public void aTestAddUser() {
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.aTestAddUser()");
        System.out.println(user);
        System.out.println("Search user before save");
        Optional<User> o = dao.getByLogin(user.getLogin());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        dao.saveOrUpdate(user);
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.aTestAddUser() : OK");
    }

    @Test
    public void bTestGetById() {
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.bTestGetById()");
        Optional<User> o = dao.getById(user.getId());
        assertTrue(o.isPresent());
        assertTrue(o.get().equals(user));
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.btestGetById() : OK");
    }

    @Test
    public void cTestAddNote(){
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.cTestAddNote()"); 
        note.setUser(user);
        System.out.println(note);
        user.setNotes(Arrays.asList(note));
        dao.saveOrUpdate(user);
        Optional<User> o = dao.getById(user.getId());
        System.out.println(o.get());
        assertTrue(o.get().getNotes().get(0).equals(note));
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.cTestAddNote() : OK");
    }

    @Test
    public void dTestEditNote() {
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.dTestEditNote()");
        Note newNote = dao.getById(user.getId()).get().getNotes().get(0);
        newNote.setObjectId(opera.getId());
        newNote.setObjectType(ObjectTypes.valueOf(opera.getClass().getSimpleName().toUpperCase()));
        newNote.setUpdateDate(new Date().getTime());
        newNote.setDescription(MyGenerator.generateTitle());
        newNote.setTitle(MyGenerator.generateTitle());
        user.setNotes(Arrays.asList(newNote));
        dao.saveOrUpdate(user);
        Note getNewNote = dao.getById(user.getId()).get().getNotes().get(0);
        assertTrue(newNote.equals(getNewNote));
        assertFalse(getNewNote.equals(note));
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.dTestEditNote() : OK");
    }
    
    @Test
    public void eTestDeleteNote(){
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.eTestDeleteNote()");
        user.setNotes(new ArrayList<>());
        dao.saveOrUpdate(user);
        assertTrue(dao.getById(user.getId()).get().getNotes().isEmpty());
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.eTestDeleteNote() : OK");
    }
    
    @Test
    public void gTestAddEvent(){
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.fTestAddEvent()");
        user.setEvents(Arrays.asList(event));
        dao.saveOrUpdate(user);
        assertTrue(dao.getById(user.getId()).get().getEvents().get(0).equals(event));
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.fTestAddEvent() : OK");
    }
    
    @Test
    public void hTestRemoveEvent(){
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.fTestRemoveEvent()");
        user.setEvents(new ArrayList<>());
        dao.saveOrUpdate(user);
        assertTrue(dao.getById(user.getId()).get().getEvents().isEmpty());
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.fTestRemoveEvent() : OK");
    }
    
    @Test
    public void iTestDeleteUser(){
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.fTestDeleteUser()");
        dao.delete(user);
        Optional<User> o = dao.getByLogin(user.getLogin());
        assertFalse(o.isPresent());
        System.out.println("NOT FOUND");
        System.out.println("ru.sfedu.organizer.dao.UserDaoTest.fTestDeleteUser() : OK");
    }    
}
