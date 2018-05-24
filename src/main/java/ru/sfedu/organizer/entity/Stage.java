package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.logging.log4j.LogManager;


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
    @JoinColumn(name = "opera_id")
    private Opera opera;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "role_stage",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    //
    // Constructors
    //
    /**
     *
     */
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
    public void setRoles(List<Role> newVar) {
        roles = newVar;
    }

    /**
     * Get the value of roles
     *
     * @return the value of roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    //
    // Other methods
    //
    @Override
    public String toString() {
        String st = "Stage{" + "id=" + getId() + ", title=" + getTitle() + ", description=" + getDescription();
        try {
            String string = ", opera=[id=" + opera.getId() + "]";
            string += ", roles=[";
            if (this.roles != null && !this.roles.isEmpty()) {
                string += this.roles.stream().collect(StringBuilder::new,
                        (response, element) -> response.append(" ").append("id=" + element.getId()),
                        (response1, response2) -> response1.append(",").append(response2.toString()))
                        .toString();
            }
            string += "]";
            st += string;
        } catch (org.hibernate.LazyInitializationException ex) {
            LogManager.getLogger(Stage.class).error("Object is not fully initialized\n" + ex);
        }
        st += '}';
        return st;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Stage a = (Stage) obj;
        //return this.getId() == a.getId();
        return this.toString().equals(a.toString());
    }
}
