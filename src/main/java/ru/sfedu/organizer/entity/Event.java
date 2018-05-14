package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Event
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "event")
@XmlRootElement
public class Event {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="title")
    private String title;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="event_director_id")
    private Human director;
    
    @Column(name="event_description")
    private String description;

    //
    // Constructors
    //
    public Event() {
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
     * Set the value of director
     *
     * @param newVar the new value of director
     */
    public void setDirector(Human newVar) {
        director = newVar;
    }

    /**
     * Get the value of director
     *
     * @return the value of director
     */
    public Human getDirector() {
        return director;
    }

    /**
     * Set the value of description
     *
     * @param newVar the new value of description
     */
    public void setDescription(String newVar) {
        description = newVar;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    
     
    //
    // Other methods
    //

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
