package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Type;

/**
 * Class Human
 */
@Entity
@Table(name = "human")
@Check(constraints = "human_birthDate is null or human_deathDate is null or (human_birthDate < human_deathDate)")
@XmlRootElement
public class Human {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "human_id")
    private long id;

    @Column(name = "human_name")
    @NotNull
    private String name;

    @Column(name = "human_surname")
    @NotNull
    private String surname;

    @Column(name = "human_patronymic")
    private String patronymic;

    @Column(name = "human_biography")
    @Type(type = "text")
    private String biography;

    @Column(name = "human_birthDate")
    private long birthDate;

    @Column(name = "human_deathDate")
    private long deathDate;

    @ManyToMany(mappedBy = "persons", cascade = {CascadeType.MERGE, CascadeType.REFRESH})    
    private List<Place> places;

    @Column(name = "human_voice", nullable = true)
    @Enumerated(EnumType.STRING)
    private Voices voice;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Professions> professions;

    //
    // Constructors
    //
    public Human() {
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
     * Set the value of name
     *
     * @param newVar the new value of name
     */
    public void setName(String newVar) {
        name = newVar;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of surname
     *
     * @param newVar the new value of surname
     */
    public void setSurname(String newVar) {
        surname = newVar;
    }

    /**
     * Get the value of surname
     *
     * @return the value of surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the value of patronymic
     *
     * @param newVar the new value of patronymic
     */
    public void setPatronymic(String newVar) {
        patronymic = newVar;
    }

    /**
     * Get the value of patronymic
     *
     * @return the value of patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Set the value of biography
     *
     * @param newVar the new value of biography
     */
    public void setBiography(String newVar) {
        biography = newVar;
    }

    /**
     * Get the value of biography
     *
     * @return the value of biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * Set the value of birthDate
     *
     * @param newVar the new value of birthDate
     */
    public void setBirthDate(long newVar) {
        birthDate = newVar;
    }

    /**
     * Get the value of birthDate
     *
     * @return the value of birthDate
     */
    public long getBirthDate() {
        return birthDate;
    }

    /**
     * Set the value of deathDate
     *
     * @param newVar the new value of deathDate
     */
    public void setDeathDate(long newVar) {
        deathDate = newVar;
    }

    /**
     * Get the value of deathDate
     *
     * @return the value of deathDate
     */
    public long getDeathDate() {
        return deathDate;
    }

    /**
     * Set the value of places
     *
     * @param newVar the new value of places
     */
    public void setPlaces(List<Place> newVar) {
        places = newVar;
    }

    /**
     * Get the value of places
     *
     * @return the value of places
     */
    @XmlTransient
    public List<Place> getPlaces() {
        return places;
    }

    /**
     * Set the value of voice
     *
     * @param newVar the new value of voice
     */
    public void setVoice(Voices newVar) {
        voice = newVar;
    }

    /**
     * Get the value of voice
     *
     * @return the value of voice
     */
    public Voices getVoice() {
        return voice;
    }

    /**
     * Set the value of professions
     *
     * @param newVar the new value of professions
     */
    public void setProfessions(List<Professions> newVar) {
        professions = newVar;
    }

    /**
     * Get the value of professions
     *
     * @return the value of professions
     */
    public List<Professions> getProfessions() {
        return professions;
    }

    //
    // Other methods
    //
    
}
