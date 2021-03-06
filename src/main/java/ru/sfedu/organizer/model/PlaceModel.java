/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.utils.Utils;


/**
 *
 * @author sterie
 */
public class PlaceModel {
    private long id;
    private String title;
    private String location;
    private String description;
    private Map<Long, String> humans = new HashMap<>();
    private List<SingleEventInfo> events = new ArrayList<>();
    private List<MediaLink> links = new ArrayList();

    /**
     *
     * @param id
     * @param title
     * @param location
     * @param description
     */
    public PlaceModel(long id, String title, String location, String description) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
    }
    
    /**
     *
     * @param list
     */
    public void addHumans(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> this.humans.put(e.getId(), Utils.getHumanName(e)));
    }
    
    /**
     *
     * @param list
     */
    public void addEvents(List<SingleEvent> list){
        if (list != null)
            list.stream().forEach(e -> this.events.add(new SingleEventInfo(e.getEvent().getClass().getSimpleName(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
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
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
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
    public List<SingleEventInfo> getEvents() {
        return events;
    }

    /**
     *
     * @return
     */
    public List<Long> getHumansId(){
        if (this.humans.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.humans.keySet());
        return keyList;
    }

    public List<MediaLink> getLinks() {
        return links;
    }

    public void setLinks(List<MediaLink> links) {
        this.links = links;
    }
    
    

}
