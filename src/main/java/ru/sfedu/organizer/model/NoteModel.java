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

    public NoteModel() {
    }
    
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getObjectTitle() {
        return objectTitle;
    }

    public void setObjectTitle(String objectTitle) {
        this.objectTitle = objectTitle;
    }

    public Date getCreateate() {
        return createate;
    }

    public void setCreateate(Date createate) {
        this.createate = createate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    
    
}
