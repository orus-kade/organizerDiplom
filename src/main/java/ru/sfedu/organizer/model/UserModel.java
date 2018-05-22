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

    public UserModel() {
    }

    public UserModel(long userId, Date registerDate) {
        this.userId = userId;
        this.registerDate = registerDate;
    }

    public UserModel(long userId, Date registerDate, String login) {
        this.userId = userId;
        this.registerDate = registerDate;
        this.login = login;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<SingleEventModel> getEvents() {
        return events;
    }

    public void setEvents(List<SingleEventModel> events) {
        this.events = events;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        if(roles != null && !roles.isEmpty())
            roles.stream().forEach(e -> this.roles.add(e.toString()));
    }
}
