package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Type;

/**
 * Class Place
 */
@Entity
@Table(name = "place")
@XmlRootElement
public class Place {

    //
    // Fields
    //
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="place_id")
    private long id;
    
    @Column(name="place_title")
    @NotNull
    private String title;
    
    @Column(name="place_location")
    private String location;
    
    @Column(name="place_description")
    @Type(type = "text")
    private String description;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "place_human",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "human_id"))
    private List<Human> persons;

    @OneToMany(mappedBy = "place", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<SingleEvent> events;
    
    //
    // Constructors
    //
    public Place() {
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
     * Set the value of title
     *
     * @param newVar the new value of title
     */
    public void setTitle(String newVar) {
        title = newVar;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of location
     *
     * @param newVar the new value of location
     */
    public void setLocation(String newVar) {
        location = newVar;
    }

    /**
     * Get the value of location
     *
     * @return the value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the value of persons
     *
     * @param newVar the new value of persons
     */
    public void setPersons(List<Human> newVar) {
        persons = newVar;
    }

    /**
     * Get the value of persons
     *
     * @return the value of persons
     */
    @XmlTransient
    public List<Human> getPersons() {
        return persons;
    }
    
    @XmlTransient
    public List<SingleEvent> getEvents() {
        return events;
    }

    public void setEvents(List<SingleEvent> events) {
        this.events = events;
    }

    //
    // Other methods
    //

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
