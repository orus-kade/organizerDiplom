/*
 */
package ru.sfedu.organizer.model;

import java.util.Date;

/**
 *
 * @author sterie
 */
public class SingleEventModel {
    
    private long id;
    private String title;
    private String type;
    private long eventId;
    private String description;
    private Date date;
    private long placeId;
    private String placeTitle;
    private String placeLocation;

    public SingleEventModel(long id, String title, String type, long eventId, String description, Date date, long placeId, String placeTitle, String placeLocation) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.eventId = eventId;
        this.description = description;
        this.date = date;
        this.placeId = placeId;
        this.placeTitle = placeTitle;
        this.placeLocation = placeLocation;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }
    
    
    
}
