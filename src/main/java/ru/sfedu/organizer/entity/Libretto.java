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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.logging.log4j.LogManager;
import org.hibernate.annotations.Type;

/**
 * Class Libretto
 */
@Entity
@Table(name = "libretto")
@XmlRootElement
public class Libretto {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libretto_id")
    private long id;

    @OneToOne(mappedBy = "libretto", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private Opera opera;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "libretto_writer",
            joinColumns = @JoinColumn(name = "libretto_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id"))
    private List<Human> writers;

    @Column(name = "libretto_text")
    @Type(type = "text")
    private String text;

    //
    // Constructors
    //

    /**
     *
     */
    public Libretto() {
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

    //
    // Other methods
    //

    @Override
    public String toString() {
        String st =  "Libretto{" + "id=" + id;
        try {
            String string =", opera=[id="+opera.getId()+"]";
            string += ", writers=[";
            if (this.writers != null && !this.writers.isEmpty()) {
                string += this.writers.stream().collect(StringBuilder::new,
                        (response, element) -> response.append(" ").append("id=" + element.getId()),
                        (response1, response2) -> response1.append(",").append(response2.toString()))
                        .toString();
            }
            st += string + "]";
        } catch (org.hibernate.LazyInitializationException ex) {
            LogManager.getLogger(Libretto.class).error("Object is not fully initialized\n" + ex);
        }      
        st += ", text=" + text + '}';
        return st;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Libretto a = (Libretto) obj;
        //return this.id == a.getId();
        return this.toString().equals(a.toString());
    }

}
