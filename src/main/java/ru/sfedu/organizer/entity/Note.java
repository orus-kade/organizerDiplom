package ru.sfedu.organizer.entity;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 * Class Note
 */
@Entity
@Table(name = "note")
@XmlRootElement
public class Note {

    //
    // Fields
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="note_id")
    private long id;
    
    @Column(name="note_object_id")
    @NotNull
    private long objectId;
    
    @Column(name="note_object_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ObjectTypes objectType;
    
    @Column(name="note_description")
    @NotNull
    @Type(type = "text")
    private String description;
    
    @Column(name="note_createDate")
    @NotNull
    private long createDate;
    
    @Column(name="note_updateDate")
    @NotNull
    private long updateDate;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    //
    // Constructors
    //
    public Note() {
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
     * Set the value of objectId
     *
     * @param newVar the new value of objectId
     */
    public void setObjectId(long newVar) {
        objectId = newVar;
    }

    /**
     * Get the value of objectId
     *
     * @return the value of objectId
     */
    public long getObjectId() {
        return objectId;
    }

    /**
     * Set the value of objectType
     *
     * @param newVar the new value of objectType
     */
    public void setObjectType(ObjectTypes newVar) {
        objectType = newVar;
    }

    /**
     * Get the value of objectType
     *
     * @return the value of objectType
     */
    public ObjectTypes getObjectType() {
        return objectType;
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
     * Set the value of createDate
     *
     * @param newVar the new value of createDate
     */
    public void setCreateDate(long newVar) {
        createDate = newVar;
    }

    /**
     * Get the value of createDate
     *
     * @return the value of createDate
     */
    public long getCreateDate() {
        return createDate;
    }

    /**
     * Set the value of updateDate
     *
     * @param newVar the new value of updateDate
     */
    public void setUpdateDate(long newVar) {
        updateDate = newVar;
    }

    /**
     * Get the value of updateDate
     *
     * @return the value of updateDate
     */
    public long getUpdateDate() {
        return updateDate;
    }
    
    

    //
    // Other methods
    //

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null || getClass() != obj.getClass()) 
            return false;
        Note a = (Note) obj;
        return this.id == a.getId();
    } 
}
