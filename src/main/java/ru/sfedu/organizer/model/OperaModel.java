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
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */
public class OperaModel {
    private long id;
    private String title;
    private String description;
    private Map<Long, String> personages = new HashMap<>();
    private Map<Long, String> aries  = new HashMap<>();
    private long librettoId;
    private Map<Long, String> writers  = new HashMap<>();
    private Map<Long, String> composers  = new HashMap<>();  
    private Map<Long, String> stages = new HashMap<>();
    private List<SingleEventInfo> futureEvents = new ArrayList<>();
    
    /**
     *
     */
    public OperaModel() {
    }

    /**
     *
     * @param id
     * @param title
     * @param description
     */
    public OperaModel(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }   

    /**
     *
     * @param list
     */
    public void addPersonages(List<Personage> list){
        if (list != null)
            list.forEach(e -> this.personages.put(e.getId(), e.getName())
            );
    }

    /**
     *
     * @param list
     */
    public void addAries(List<Aria> list){
        if (list != null)
            list.forEach(e -> this.aries.put(e.getId(), e.getPosition() + " " + e.getTitle())
            );
    }
    
    /**
     *
     * @param librettoId
     */
    public void setLibrettoId(long librettoId) {
        this.librettoId = librettoId;
    }

    /**
     *
     * @param list
     */
    public void addWriters(List<Human> list){
        if (list != null)
            list.forEach(e -> this.writers.put(e.getId(), Utils.getHumanName(e)));
    }
    
    /**
     *
     * @param list
     */
    public void addComposers(List<Human> list){
        if (list != null)
            list.forEach(e -> this.composers.put(e.getId(), Utils.getHumanName(e)));
    } 
    
    /**
     *
     * @param list
     */
    public void addStages(List<Stage> list){
        if (list != null)
            list.stream().forEach(e -> this.stages.put(e.getId(), e.getTitle()));
    }
    
    /**
     *
     * @param list
     */
    public void addFutureEvents(List<SingleEvent> list){
        if (list != null)
           list.stream().forEach(e -> this.futureEvents.add(new SingleEventInfo(e.getEvent().getClass().getSimpleName().toLowerCase(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
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
    public long getLibrettoId() {
        return librettoId;
    }
    
    /**
     *
     * @return
     */
    public List<Long> getPersonagesId(){
        if (this.personages.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.personages.keySet());
        return keyList;
    }
    
    /**
     *
     * @return
     */
    public List<Long> getAriesId(){
        if (this.aries.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.aries.keySet());
        return keyList;
    }
    
}
