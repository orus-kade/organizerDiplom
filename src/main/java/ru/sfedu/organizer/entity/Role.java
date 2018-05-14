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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Role
 */
@Entity
@Table(name = "role")
@XmlRootElement
public class Role {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "personage_id")
    @NotNull
    private Personage personage;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "singer_id")
    @NotNull
    private Human singer;

    //
    // Constructors
    //
    public Role() {
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
     * Set the value of personage
     *
     * @param newVar the new value of personage
     */
    public void setPersonage(Personage newVar) {
        personage = newVar;
    }

    /**
     * Get the value of personage
     *
     * @return the value of personage
     */
    public Personage getPersonage() {
        return personage;
    }

    /**
     * Set the value of singer
     *
     * @param newVar the new value of singer
     */
    public void setSinger(Human newVar) {
        singer = newVar;
    }

    /**
     * Get the value of singer
     *
     * @return the value of singer
     */
    public Human getSinger() {
        return singer;
    }

    //
    // Other methods
    //
}
