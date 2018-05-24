package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Type;

/**
 *
 * @author sterie
 */
/**
 * Class Personage
 */
@Entity
@Table(name = "personage")
@XmlRootElement
public class Personage {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personage_id")
    private long id;

    @Column(name = "personage_name", nullable = false)
    private String name;
    
    @Column(name = "personage_description")
    @Type(type = "text")
    private String description;

    @ManyToMany
    @JoinTable(name = "aria_personage",
            joinColumns = @JoinColumn(name = "personage_id"),
            inverseJoinColumns = @JoinColumn(name = "aria_id"))
//    @ManyToMany(mappedBy = "personages")
    private List<Aria> aries;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "opera_id")//, nullable = false)
    private Opera opera;

    //
    // Constructors
    //

    /**
     *
     */
    public Personage() {
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
    public void setName(String newVar) {
        name = newVar;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of aries
     *
     * @param newVar the new value of personages
     */
    public void setAries(List<Aria> newVar) {
        aries = newVar;
    }

    /**
     * Get the value of personages
     *
     * @return the value of personages
     */
    @XmlTransient
    public List<Aria> getAries() {
        return aries;
    }

    /**
     * Set the value of opera
     *
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

    
    
    //
    // Other methods
    //

    /**
     *
     * @return
     */

    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) 
            return false;
        Personage a = (Personage) obj;
        return this.id == a.getId();
    } 
}
