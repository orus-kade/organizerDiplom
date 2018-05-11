package ru.sfedu.organizer.model;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Class Concert
 */
@Entity
@Table(name = "concert")
public class Concert extends Event {

    //
    // Fields
    //
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "aria_concert",
            joinColumns = @JoinColumn(name = "aria_id"),
            inverseJoinColumns = @JoinColumn(name = "consert_id"))
    private Set<Aria> aries;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "concert_singer",
            joinColumns = @JoinColumn(name = "singer_id"),
            inverseJoinColumns = @JoinColumn(name = "consert_id"))
    private Set<Human> singers;

    //
    // Constructors
    //
    public Concert() {
    }

    ;
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of aries
   * @param newVar the new value of aries
   */
  public void setAries(Set<Aria> newVar) {
        aries = newVar;
    }

    /**
     * Get the value of aries
     *
     * @return the value of aries
     */
    public Set<Aria> getAries() {
        return aries;
    }

    /**
     * Set the value of singers
     *
     * @param newVar the new value of singers
     */
    public void setSingers(Set<Human> newVar) {
        singers = newVar;
    }

    /**
     * Get the value of singers
     *
     * @return the value of singers
     */
    public Set<Human> getSingers() {
        return singers;
    }

    //
    // Other methods
    //
    
}
