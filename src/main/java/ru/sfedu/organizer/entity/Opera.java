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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Type;

/**
 * Class Opera
 */
@Entity
@Table(name = "opera")
@XmlRootElement
public class Opera {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="opera_id")
    private long id;
    
    @Column(name="opera_title")
    @NotNull
    private String title;
    
    @Column(name="opera_description")
    @Type(type = "text")
    private String description;
    
    @OneToMany(mappedBy="opera", cascade = CascadeType.ALL)
    private List<Personage> personages;
    
    @OneToMany(mappedBy = "opera", cascade = CascadeType.ALL)
    private List<Aria> aries;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="libretto_id")
    private Libretto libretto;
    

    //
    // Constructors
    //
    public Opera() {
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
     * Set the value of personages
     *
     * @param newVar the new value of personages
     */
    public void setPersonages(List<Personage> newVar) {
        personages = newVar;
    }

    /**
     * Get the value of personages
     *
     * @return the value of personages
     */
    @XmlTransient
    public List<Personage> getPersonages() {
        return personages;
    }

    /**
     * Set the value of aries
     *
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
    @XmlTransient
    public List<Aria> getAries() {
        return aries;
    }

    /**
     * Set the value of libretto
     *
     * @param newVar the new value of libretto
     */
    public void setLibretto(Libretto newVar) {
        libretto = newVar;
    }

    /**
     * Get the value of libretto
     *
     * @return the value of libretto
     */
    @XmlTransient
    public Libretto getLibretto() {
        return libretto;
    }

    //
    // Other methods
    //
}
