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
    
    
}
