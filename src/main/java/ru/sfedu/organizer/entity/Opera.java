package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.logging.log4j.LogManager;
import org.hibernate.annotations.Type;

/**
 * Class Opera
 */
@Entity
@Table(name = "opera")
public class Opera {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="opera_id")
    private long id;
    
    @Column(name="opera_title", nullable = false)
    private String title;
    
    @Column(name="opera_description")
    @Type(type = "text")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "opera_id")
    private List<Personage> personages;
    
    //@OneToMany(mappedBy = "opera", cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "opera_id")
    private List<Aria> aries;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "opera_id")
    private List<Stage> stages;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="libretto_id")
    private Libretto libretto;
    

    //
    // Constructors
    //

    /**
     *
     */
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
    public Libretto getLibretto() {
        return libretto;
    }
    
    

    //
    // Other methods
    //

    /**
     *
     * @return
     */

    public List<Stage> getStages() {
        return stages;
    }

    /**
     *
     * @param stages
     */
    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    @Override
    public String toString() {
        String st = "Opera{" + "id=" + id + ", title=" + title + ", description=" + description;
        try{
            String string =", personages=[";
        if (this.personages!=null && !this.personages.isEmpty())
           string += this.personages.stream().collect(StringBuilder::new,
	    		(response, element) -> response.append(" ").append("id="+element.getId()),
	    		(response1, response2) -> response1.append(",").append(response2.toString()))
	    		.toString();
        string += "], aries=[";
        if (this.aries!=null && !this.aries.isEmpty())
           string += this.aries.stream().collect(StringBuilder::new,
	    		(response, element) -> response.append(" ").append("id="+element.getId()),
	    		(response1, response2) -> response1.append(",").append(response2.toString()))
	    		.toString();
        string +="], stages=[";
        if (this.stages!=null && !this.stages.isEmpty())
           string += this.stages.stream().collect(StringBuilder::new,
	    		(response, element) -> response.append(" ").append("id="+element.getId()),
	    		(response1, response2) -> response1.append(",").append(response2.toString()))
	    		.toString();
        string +="], libretto=[";
        if (libretto!= null) string += "id="+libretto.getId();
        st += string + "]";
        } catch (org.hibernate.LazyInitializationException ex){
            LogManager.getLogger(Opera.class).error("Object is not fully initialized\n"+ex);
        }
        st += '}';
        return st;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) 
            return false;
        Opera a = (Opera) obj;
        //return this.id == a.getId();
        return this.toString().equals(a.toString());
    } 
}
