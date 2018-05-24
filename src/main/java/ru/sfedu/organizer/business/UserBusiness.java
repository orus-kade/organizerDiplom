/*
 */
package ru.sfedu.organizer.business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.NoteDao;
import ru.sfedu.organizer.dao.SingleEventDao;
import ru.sfedu.organizer.dao.UserDao;
import ru.sfedu.organizer.entity.Note;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.entity.User;
import ru.sfedu.organizer.entity.UserRoles;
import ru.sfedu.organizer.model.HumanModel;
import ru.sfedu.organizer.model.NoteModel;
import ru.sfedu.organizer.model.SingleEventModel;
import ru.sfedu.organizer.model.UserModel;

/**
 *
 * @author sterie
 */
@Stateless
public class UserBusiness {
    
    static final Logger logger = LogManager.getLogger(UserBusiness.class);
    
    private final UserDao userDao = new UserDao();
    
    private final SingleEventDao dao = new SingleEventDao();
    
    private final NoteDao noteDao = new NoteDao();
    
    @EJB
    private final SingleEventBusiness sEBusiness = new SingleEventBusiness();
    
    @EJB
    private final AriaBusiness ariaBusiness = new AriaBusiness();
    
    @EJB
    private final ConcertBusiness concertBusiness = new ConcertBusiness();
    
    @EJB
    private final HumanBusiness humanBusiness = new HumanBusiness();
    
    @EJB
    private final LibrettoBusiness librettoBusiness = new LibrettoBusiness();
    
    @EJB
    private final OperaBusiness operaBusiness = new OperaBusiness();
    
    @EJB
    private final PersonageBusiness personageBusiness = new PersonageBusiness();
    
    @EJB
    private final PlaceBusiness placeBusiness = new PlaceBusiness();
    
    @EJB
    private final StageBusiness stageBusiness = new StageBusiness();
    
    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    public UserModel getById(long id) throws ObjectNotFoundException{
        Optional<User> o = userDao.getById(id);
        if(!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.USER, id);
        User user = o.get();
        return this.getModel(user);
    }
    
    /**
     *
     * @param login
     * @param pass
     * @return
     */
    public UserModel login(String login, String pass){
        Optional<User> o = userDao.getByLogin(login);
        if(!o.isPresent()) return null; 
        User user = o.get();
        try {
            if (!verifyPassword(pass, user)) return null;
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex);
            return null;
        }
        return this.getModel(user);
    }
    
    /**
     *
     * @param login
     * @param pass
     * @return
     */
    public boolean registration(String login, String pass){
        Optional<User> o = userDao.getByLogin(login);
        if(o.isPresent()) return false;
        User user = new User();
        user.setLogin(login);
        user.setCreateDate(new Date().getTime());
        user.setRoles(new ArrayList<>(Arrays.asList(UserRoles.USER)));
        try {
            byte[] salt = this.getSalt();
            String securePass = this.getSecurePassword(pass, salt);
            user.setSalt(salt);
            user.setPassword(securePass);    
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex);
            return false;
        } catch (NoSuchProviderException ex) {
            logger.error(ex);
            return false;
        } 
        userDao.saveOrUpdate(user);
        return true;
    }
    
    /**
     *
     * @return
     */
    public int count(){
        return userDao.count();
    }
    
    private String getSecurePassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException{
        String generatedPassword = null;
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        return generatedPassword;
    }

    private byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
    
    private boolean verifyPassword(String candidate, User user) throws NoSuchAlgorithmException{
        return user.getPassword().equals(this.getSecurePassword(candidate, user.getSalt()));
    } 
    
    /**
     *
     * @param id
     */
    public void delete(long id){
        Optional<User> o = userDao.getById(id);
        if (o.isPresent()){
            userDao.delete(o.get());
        }
    }
    
    /**
     *
     * @param id
     * @param eventId
     * @throws ObjectNotFoundException
     */
    public void removeEvent(long id, long eventId) throws ObjectNotFoundException{
        Optional<User> o = userDao.getById(id);
        if(!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.USER, id);
        User user = o.get();
        List<SingleEvent> events = user.getEvents();
        if (!events.removeIf(e -> e.getId() == eventId)) throw new ObjectNotFoundException(ObjectTypes.SINGLE_EVENT, eventId);
        user.setEvents(events);
        userDao.saveOrUpdate(user);
    }
    
    /**
     *
     * @param id
     * @param eventId
     * @throws ObjectNotFoundException
     */
    public void addEvent(long id, long eventId) throws ObjectNotFoundException{
        Optional<User> o = userDao.getById(id);
        if(!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.USER, id);
        User user = o.get();
        List<SingleEvent> events = user.getEvents();
        Optional<SingleEvent> oe = dao.getById(eventId);
        if (!oe.isPresent()) throw new ObjectNotFoundException(ObjectTypes.SINGLE_EVENT, eventId);
        events.add(oe.get());
        user.setEvents(events);
        userDao.saveOrUpdate(user);
    }
    
    /**
     *
     * @param id
     * @param noteId
     * @throws ObjectNotFoundException
     */
    public void removeNote(long id, long noteId) throws ObjectNotFoundException{
        Optional<User> o = userDao.getById(id);
        if(!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.USER, id);
        User user = o.get();
        List<Note> notes = user.getNotes();
        if (!notes.removeIf(e -> e.getId() == noteId)) throw new ObjectNotFoundException(ObjectTypes.NOTE, noteId);
        user.setNotes(notes);
        userDao.saveOrUpdate(user);
    }
    
    /**
     *
     * @param noteModel
     * @param userId
     * @return
     * @throws ObjectNotFoundException
     */
    public long createOrSaveNote(NoteModel noteModel, long userId) throws ObjectNotFoundException{
        Note note;
        if (noteModel.getId() <= 0){
            note = new Note();
            Optional<User> ou = userDao.getById(userId);
            if(!ou.isPresent()) throw new ObjectNotFoundException(ObjectTypes.USER, userId);
            note.setUser(ou.get());
            try{
                ObjectTypes oType = ObjectTypes.valueOf(noteModel.getObjectType().toUpperCase());
                if (this.checkNoteAndGetObjectName(oType, noteModel.getObjectId()) != null){
                    note.setObjectId(noteModel.getObjectId());
                    note.setObjectType(oType);
                }
            } catch (IllegalArgumentException ex){
                logger.error(ex);
                return 0;
            }
            note.setCreateDate(new Date().getTime());
            note.setUpdateDate(note.getCreateDate());
        } 
        else{
            Optional<Note> o = noteDao.getById(noteModel.getId());
            if (!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.NOTE, noteModel.getId());
            note = o.get();
        }
        note.setDescription(noteModel.getDescription());
        note.setTitle(noteModel.getTitle());
        note.setUpdateDate(new Date().getTime());
        noteDao.saveOrUpdate(note);
        return note.getId();
    }
    
    private String checkNoteAndGetObjectName(ObjectTypes objectType, long objectId) throws ObjectNotFoundException{
        switch (objectType){
            case ARIA : {
                return ariaBusiness.getById(objectId).getTitle();
            }
            case CONCERT : {
                return concertBusiness.getById(objectId).getTitle();
            } 
            case HUMAN : {
                HumanModel hm = humanBusiness.getById(objectId);
                String name = hm.getSurname() + " " + hm.getName();
                if (hm.getPatronymic()!= null) name += " " + hm.getPatronymic();
                return name;

            }
            case LIBRETTO : {
                return librettoBusiness.getById(objectId).getOperaTitle();
            }
            case OPERA : {
                return operaBusiness.getById(objectId).getTitle();
            }
            case PERSONAGE : {
                return personageBusiness.getById(objectId).getName();
            }
            case PLACE : {
                return placeBusiness.getById(objectId).getTitle();
            }
            case SINGLE_EVENT : {
                return sEBusiness.getById(objectId).getTitle();
            }
            case STAGE : {
                return stageBusiness.getById(objectId).getTitle();
            }
            default: return null;
        }
    }

    private UserModel getModel(User user) {
        UserModel userModel = new UserModel(user.getId(), new Date(user.getCreateDate()), user.getLogin());
        if (user.getEvents() != null && !user.getEvents().isEmpty()){
            List<SingleEventModel> events = new ArrayList<>();
            user.getEvents().stream().forEach(e -> {
                try {
                    events.add(sEBusiness.getById(e.getId()));
                } catch (ObjectNotFoundException ex) {
                    logger.error(ex);
                }
            });
            userModel.setEvents(events);
        }
        if (user.getNotes() != null && !user.getNotes().isEmpty()){
            List<NoteModel> notes = new ArrayList<>();
            user.getNotes().stream().forEach(e -> {
                try {
                    notes.add(new NoteModel(e.getId(),
                            e.getTitle(),
                            e.getDescription(),
                            e.getObjectType().toString(),
                            e.getObjectId(),
                            this.checkNoteAndGetObjectName(e.getObjectType(), e.getObjectId()),
                            new Date(e.getCreateDate()),
                            new Date(e.getUpdateDate())));
                } catch (ObjectNotFoundException ex) {
                    logger.error(ex);
                }
            });
            userModel.setNotes(notes);
        }
        userModel.setRoles(user.getRoles());
        return userModel;
    }
    
    /**
     *
     * @return
     */
    public List<UserModel> detAll(){
        Optional<List> o = userDao.getAll();
        List<UserModel> list = new ArrayList<>();
        if (o.isPresent()){
            List<User> users = o.get();
            users.stream().forEachOrdered(e -> {
                UserModel um = new UserModel(e.getId(), new Date(e.getCreateDate()), e.getLogin());
                um.setRoles(e.getRoles());
                list.add(um);
            });
        }
        return list;
    }
    
    /**
     *
     * @param userModel
     * @throws ObjectNotFoundException
     */
    public void setRoles(UserModel userModel) throws ObjectNotFoundException{
        Optional<User> o = userDao.getById(userModel.getUserId());
        if(!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.USER, userModel.getUserId());
        User user = o.get();
        List<String> rolesSt = userModel.getRoles();
        List<UserRoles> roles = new ArrayList<>();
        rolesSt.stream().forEach(e -> {
            try{
               roles.add(UserRoles.valueOf(e.toUpperCase()));
            } catch (IllegalArgumentException ex){
                logger.error(ex);
            }            
        });
        user.setRoles(roles);
        userDao.saveOrUpdate(user);
    }
}
