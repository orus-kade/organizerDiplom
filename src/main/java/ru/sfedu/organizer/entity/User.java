package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Class User
 */
@Entity
@Table(name = "userdata")
public class User {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long id;
    
    @Column(name="user_login")
    @NotNull
    private String login;
    
    @Column(name="user_password")
    @NotNull
    private String password;
    
    @Column(name="user_salt")
    @NotNull
    private byte[] salt;
    
    @Fetch(FetchMode.SELECT)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name="user_role")
    private List<UserRoles> roles;
    
//    @Column(name="user_email")
//    @NotNull
//    private String email;
    
    @Column(name="user_create_date")
    @NotNull
    private long createDate;
    
//    @Column(name="user_name")
//    private String name;
    
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Note> notes;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "single_event_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "single_event_id"))
    private List<SingleEvent> events;

    //
    // Constructors
    //
    public User() {
    }

    ;
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId(long newVar) {
        id = newVar;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Set the value of login
     *
     * @param newVar the new value of login
     */
    public void setLogin(String newVar) {
        login = newVar;
    }

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    public List<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles;
    }

    /**
     * Set the value of email
     *
     * @param newVar the new value of email
     */
//    public void setEmail(String newVar) {
//        email = newVar;
//    }
//
//    /**
//     * Get the value of email
//     *
//     * @return the value of email
//     */
//    public String getEmail() {
//        return email;
//    }

    /**
     * Set the value of createDate
     *
     * @param newVar the new value of createDate
     */
    public void setCreateDate(long newVar) {
        createDate = newVar;
    }

    /**
     * Get the value of createDate
     *
     * @return the value of createDate
     */
    public long getCreateDate() {
        return createDate;
    }

    /**
     * Set the value of name
     *
     * @param newVar the new value of name
     */
//    public void setName(String newVar) {
//        name = newVar;
//    }
//
//    /**
//     * Get the value of name
//     *
//     * @return the value of name
//     */
//    public String getName() {
//        return name;
//    }

    /**
     * Set the value of notes
     *
     * @param newVar the new value of notes
     */
    public void setNotes(List<Note> newVar) {
        notes = newVar;
    }

    /**
     * Get the value of notes
     *
     * @return the value of notes
     */
    @XmlTransient
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Set the value of events
     *
     * @param newVar the new value of events
     */
    public void setEvents(List<SingleEvent> newVar) {
        events = newVar;
    }

    /**
     * Get the value of events
     *
     * @return the value of events
     */
    public List<SingleEvent> getEvents() {
        return events;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    //
    // Other methods
    //

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) 
            return false;
        User a = (User) obj;
        return this.id == a.getId();
    } 
}
