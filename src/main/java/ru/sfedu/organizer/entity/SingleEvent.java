package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class SingleEvent
 */
@Entity
@Table(name = "single_event")
@XmlRootElement
public class SingleEvent {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name="place_id")
    @NotNull
    private Place place;
    
    @Column(name="single_event_datetime")
    @NotNull
    private long datetime;
    
    @Column(name="singe_event_description")
    private String description;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="event_id")
    @NotNull
    private Event event;

    //
    // Constructors
    //
    public SingleEvent() {
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
     * Set the value of place
     *
     * @param newVar the new value of place
     */
    public void setPlace(Place newVar) {
        place = newVar;
    }

    /**
     * Get the value of place
     *
     * @return the value of place
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Set the value of datetime
     *
     * @param newVar the new value of datetime
     */
    public void setDatetime(Long newVar) {
        datetime = newVar;
    }

    /**
     * Get the value of datetime
     *
     * @return the value of datetime
     */
    public Long getDatetime() {
        return datetime;
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

    /**
     * Set the value of event
     *
     * @param newVar the new value of event
     */
    public void setEvent(Event newVar) {
        event = newVar;
    }

    /**
     * Get the value of event
     *
     * @return the value of event
     */
    public Event getEvent() {
        return event;
    }

    //
    // Other methods
    //
}
