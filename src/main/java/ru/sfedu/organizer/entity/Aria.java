package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
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

/**
 * Class Aria
 */
@Entity
@Table(name = "aria")
@XmlRootElement(name = "aria")

public class Aria {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aria_id")
    private long id;

    @Column(name = "aria_title")
    @NotNull
    private String title;

    @Column(name = "aria_text")
    @Type(type = "text")
    private String text;
//fetch = FetchType.LAZY, 
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "aria_composer",
            joinColumns = @JoinColumn(name = "aria_id"),
            inverseJoinColumns = @JoinColumn(name = "composer_id"))
    private List<Human> composers;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "aria_writer",
            joinColumns = @JoinColumn(name = "aria_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id"))
    private List<Human> writers;

//    @ManyToMany(mappedBy = "aries", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "aria_personage",
            joinColumns = @JoinColumn(name = "aria_id"),
            inverseJoinColumns = @JoinColumn(name = "personage_id"))
    private List<Personage> personages;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "opera_id")
    @NotNull
    private Opera opera;

    @Column(name = "position")
    @NotNull
    private int position;

    //
    // Constructors
    //
    public Aria() {
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
     * Set the value of text
     *
     * @param newVar the new value of text
     */
    public void setText(String newVar) {
        text = newVar;
    }

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of composers
     *
     * @param newVar the new value of composers
     */
    public void setComposers(List<Human> newVar) {
        composers = newVar;
    }

    /**
     * Get the value of composers
     *
     * @return the value of composers
     */
    @XmlTransient
    public List<Human> getComposers() {
        return composers;
    }

    /**
     * Set the value of writers
     *
     * @param newVar the new value of writers
     */
    public void setWriters(List<Human> newVar) {
        writers = newVar;
    }

    /**
     * Get the value of writers
     *
     * @return the value of writers
     */
    @XmlTransient
    public List<Human> getWriters() {
        return writers;
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

    /**
     * Set the value of position
     *
     * @param newVar the new value of position
     */
    public void setPosition(int newVar) {
        position = newVar;
    }

    /**
     * Get the value of position
     *
     * @return the value of position
     */
    public int getPosition() {
        return position;
    }

    //
    // Other methods
    //

    @Override
    public String toString() {
        return "Aria{" + "id=" + id + ", title=" + title + ", text=" + text + ", composers=" + composers + ", writers=" + writers + ", personages=" + personages + ", opera=" + opera + ", position=" + position + '}';
    }
    
    
    
}
