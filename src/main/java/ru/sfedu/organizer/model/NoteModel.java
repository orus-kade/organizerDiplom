/*
 */
package ru.sfedu.organizer.model;

import java.util.Date;
import ru.sfedu.organizer.entity.Note;

/**
 *
 * @author sterie
 */
public class NoteModel {
    
    private long id;
    private String title;
    private String description;
    private String objectType;
    private long objectId;
    private String objectTitle;
    private Date createate;
    private Date updateDate;

    /**
     *
     */
    public NoteModel() {
    }
    
    /**
     *
     * @param note
     * @param objectTitle
     */
    public NoteModel(Note note, String objectTitle) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.description = note.getDescription();
        this.objectType = note.getObjectType().toString().toLowerCase();
        this.objectId = note.getObjectId();
        this.createate = new Date(note.getCreateDate());
        this.updateDate = new Date(note.getUpdateDate());
        this.objectTitle = objectTitle;
    }

    /**
     *
     * @param id
     * @param title
     * @param description
     * @param objectType
     * @param objectId
     * @param objectTitle
     * @param createate
     * @param updateDate
     */
    public NoteModel(long id, String title, String description, String objectType, long objectId, String objectTitle, Date createate, Date updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.objectType = objectType;
        this.objectId = objectId;
        this.objectTitle = objectTitle;
        this.createate = createate;
        this.updateDate = updateDate;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

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

    /**
     *
     * @return
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     *
     * @param objectType
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     *
     * @return
     */
    public long getObjectId() {
        return objectId;
    }

    /**
     *
     * @param objectId
     */
    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    /**
     *
     * @return
     */
    public String getObjectTitle() {
        return objectTitle;
    }

    /**
     *
     * @param objectTitle
     */
    public void setObjectTitle(String objectTitle) {
        this.objectTitle = objectTitle;
    }

    /**
     *
     * @return
     */
    public Date getCreateate() {
        return createate;
    }

    /**
     *
     * @param createate
     */
    public void setCreateate(Date createate) {
        this.createate = createate;
    }

    /**
     *
     * @return
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     *
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    
    
}
