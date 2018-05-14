package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class Stage
 */
@Entity
@Table(name = "stage")
@XmlRootElement
public class Stage extends Event {

    //
    // Fields
    //
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="opera_id")
    private Opera opera;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "role_stage",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    //
    // Constructors
    //
    public Stage() {
    }

    ;
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of opera
   * @param newVar the new value of opera
   */
  public void setOpera(Opera newVar) {
        opera = newVar;
    }

    /**
     * Get the value of opera
     *
     * @return the value of opera
     */
    public Opera getOpera() {
        return opera;
    }

    /**
     * Set the value of roles
     *
     * @param newVar the new value of roles
     */
    public void setRoles(Set<Role> newVar) {
        roles = newVar;
    }

    /**
     * Get the value of roles
     *
     * @return the value of roles
     */
    @XmlTransient
    public Set<Role> getRoles() {
        return roles;
    }

    //
    // Other methods
    //
}