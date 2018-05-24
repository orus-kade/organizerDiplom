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

    /**
     *
     * @param id
     * @param title
     * @param type
     * @param eventId
     * @param description
     * @param date
     * @param placeId
     * @param placeTitle
     * @param placeLocation
     */
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
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public long getEventId() {
        return eventId;
    }

    /**
     *
     * @param eventId
     */
    public void setEventId(long eventId) {
        this.eventId = eventId;
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
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public long getPlaceId() {
        return placeId;
    }

    /**
     *
     * @param placeId
     */
    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    /**
     *
     * @return
     */
    public String getPlaceTitle() {
        return placeTitle;
    }

    /**
     *
     * @param placeTitle
     */
    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    /**
     *
     * @return
     */
    public String getPlaceLocation() {
        return placeLocation;
    }

    /**
     *
     * @param placeLocation
     */
    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }
    
    
    
}
