package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.apache.logging.log4j.LogManager;

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

    /**
     *
     */
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
    public String toString() {
        String st = "Concert{" + "id=" + getId() + ", title=" + getTitle() + ", description=" + getDescription();
        try{
            String string =", aries=[";
        if (this.aries!=null && !this.aries.isEmpty())
           string += this.aries.stream().collect(StringBuilder::new,
	    		(response, element) -> response.append(" ").append("id="+element.getId()),
	    		(response1, response2) -> response1.append(",").append(response2.toString()))
	    		.toString();
        string += "], singers=[";
        if (this.singers!=null && !this.singers.isEmpty())
           string += this.singers.stream().collect(StringBuilder::new,
	    		(response, element) -> response.append(" ").append("id="+element.getId()),
	    		(response1, response2) -> response1.append(",").append(response2.toString()))
	    		.toString();
        string +="], director=[";
        if (getDirector()!=null) string+="id="+getDirector().getId();
        string +="]";
        st += string;
        } catch (org.hibernate.LazyInitializationException ex){
            LogManager.getLogger(Concert.class).error("Object is not fully initialized\n"+ex);
        } 
        st += '}';
        return st;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) 
            return false;
        Concert a = (Concert) obj;
        return this.toString().equals(a.toString());
    } 
    
}
