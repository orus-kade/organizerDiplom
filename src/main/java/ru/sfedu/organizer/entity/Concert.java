package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
            joinColumns = @JoinColumn(name = "concert_id"),
            inverseJoinColumns = @JoinColumn(name = "aria_id"))
    private List<Aria> aries;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "concert_singer",
            joinColumns = @JoinColumn(name = "concert_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_id"))
    private List<Human> singers;

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
  public void setAries(List<Aria> newVar) {
        aries = newVar;
    }

    /**
     * Get the value of aries
     *
     * @return the value of aries
     */
    public List<Aria> getAries() {
        return aries;
    }

    /**
     * Set the value of singers
     *
     * @param newVar the new value of singers
     */
    public void setSingers(List<Human> newVar) {
        singers = newVar;
    }

    /**
     * Get the value of singers
     *
     * @return the value of singers
     */
    public List<Human> getSingers() {
        return singers;
    }

    //
    // Other methods
    //
    
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) 
            return false;
        Concert a = (Concert) obj;
        return this.getId() == a.getId();
    } 
    
}
