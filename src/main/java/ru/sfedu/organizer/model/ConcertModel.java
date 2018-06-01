/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */
public class ConcertModel {
    private long id;
    private String title;
    private String description;
    private Map<Long, String> director = new HashMap<>();
    private Map<Long, String> singers = new HashMap<>();
    private List<AriaInfo> aries = new ArrayList<>();
    private Map<Long, Date> events = new HashMap<>();
    private List<MediaLink> links = new ArrayList();

    /**
     *
     * @param id
     * @param title
     * @param description
     */
    public ConcertModel(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     *
     */
    public ConcertModel() {
    }
    
    /**
     *
     * @param human
     */
    public void addDirector(Human human){
        if (human != null){
            this.director.put(human.getId(), Utils.getHumanName(human));
        }            
    }
    
    /**
     *
     * @param list
     */
    public void addSingers(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> this.singers.put(e.getId(), Utils.getHumanName(e)));
    }

    /**
     *
     * @param list
     */
    public void addAries(List<Aria> list){
        if (list != null)
            list.stream().forEach(e -> this.aries.add(new AriaInfo(e)));
    }
    
    /**
     *
     * @param list
     */
    public void addEvents(List<SingleEvent> list){
        if (list != null)
            list.stream().forEach(e -> this.events.put(e.getId(), new Date(e.getDatetime())));
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
    public List<AriaInfo> getAries() {
        return aries;
    }

    /**
     *
     * @param aries
     */
    public void setAries(List<AriaInfo> aries) {
        this.aries = aries;
    }

    /**
     *
     * @return
     */
    public long getDirectorId() {
        if (this.director.isEmpty())
            return 0;
        List<Long> keyList = new ArrayList(this.director.keySet());
        return keyList.get(0);
    }

    /**
     *
     * @return
     */
    public List<Long> getSingersIds() {
        if (this.singers.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.singers.keySet());
        return keyList;
    }

    public List<MediaLink> getLinks() {
        return links;
    }

    public void setLinks(List<MediaLink> links) {
        this.links = links;
    }
    
    

}
