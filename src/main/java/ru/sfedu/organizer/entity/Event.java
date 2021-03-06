package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * Class Event
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "event")
public class Event {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="title", nullable = false)
    private String title;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="event_director_id")
    private Human director;
    
    @Column(name="event_description")
    @Type(type = "text")
    private String description;
    
    @OneToMany(mappedBy="event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SingleEvent> singleEvents;

    //
    // Constructors
    //

    /**
     *
     */
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
     *
     * @return
     */
    public List<SingleEvent> getSingleEvents() {
        return singleEvents;
    }

    /**
     *
     * @param singleEvents
     */
    public void setSingleEvents(List<SingleEvent> singleEvents) {
        this.singleEvents = singleEvents;
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

    /**
     *
     * @return
     */

    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) 
            return false;
        Event a = (Event) obj;
        return this.id == a.getId();
    } 
    
}
