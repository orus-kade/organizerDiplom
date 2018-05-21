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

    public ConcertModel(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public ConcertModel() {
    }
    
    
    public void addDirector(Human human){
        if (human != null){
            this.director.put(human.getId(), Utils.getHumanName(human));
        }            
    }
    
    public void addSingers(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> this.singers.put(e.getId(), Utils.getHumanName(e)));
    }
    public void addAries(List<Aria> list){
        if (list != null)
            list.stream().forEach(e -> this.aries.add(new AriaInfo(e)));
    }
    
    public void addEvents(List<SingleEvent> list){
        if (list != null)
            list.stream().forEach(e -> this.events.put(e.getId(), new Date(e.getDatetime())));
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

    public List<AriaInfo> getAries() {
        return aries;
    }

    public void setAries(List<AriaInfo> aries) {
        this.aries = aries;
    }

    public long getDirectorId() {
        if (this.director.isEmpty())
            return 0;
        List<Long> keyList = new ArrayList(this.director.keySet());
        return keyList.get(0);
    }

    public List<Long> getSingersIds() {
        if (this.singers.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.singers.keySet());
        return keyList;
    }

}
