/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.sfedu.organizer.entity.UserRoles;

/**
 *
 * @author sterie
 */
public class UserModel {
    
    private long userId;
    private Date registerDate;
    private String login;
    private List<SingleEventModel> events = new ArrayList<>();
    private List<NoteModel> notes = new ArrayList<>();
    private List<String> roles = new ArrayList<>();

    /**
     *
     */
    public UserModel() {
    }

    /**
     *
     * @param userId
     * @param registerDate
     */
    public UserModel(long userId, Date registerDate) {
        this.userId = userId;
        this.registerDate = registerDate;
    }

    /**
     *
     * @param userId
     * @param registerDate
     * @param login
     */
    public UserModel(long userId, Date registerDate, String login) {
        this.userId = userId;
        this.registerDate = registerDate;
        this.login = login;
    }

    /**
     *
     * @return
     */
    public long getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     *
     * @param registerDate
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     *
     * @return
     */
    public List<SingleEventModel> getEvents() {
        return events;
    }

    /**
     *
     * @param events
     */
    public void setEvents(List<SingleEventModel> events) {
        this.events = events;
    }

    /**
     *
     * @return
     */
    public List<NoteModel> getNotes() {
        return notes;
    }

    /**
     *
     * @param notes
     */
    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles
     */
    public void setRoles(List<UserRoles> roles) {
        if(roles != null && !roles.isEmpty())
            roles.stream().forEach(e -> this.roles.add(e.toString()));
    }
}
