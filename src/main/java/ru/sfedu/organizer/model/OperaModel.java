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
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Opera;
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
    

    public OperaModel() {
    }

    public OperaModel(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }   

    public void addPersonages(List<Personage> list){
        if (list != null)
            list.forEach(e -> this.personages.put(e.getId(), e.getName())
            );
    }

    public void addAries(List<Aria> list){
        if (list != null)
            list.forEach(e -> this.aries.put(e.getId(), e.getPosition() + " " + e.getTitle())
            );
    }
    
    public void setLibrettoId(long librettoId) {
        this.librettoId = librettoId;
    }

    public void addWriters(List<Human> list){
        if (list != null)
            list.forEach(e -> this.writers.put(e.getId(), Utils.getHumanName(e)));
    }
    
    public void addComposers(List<Human> list){
        if (list != null)
            list.forEach(e -> this.composers.put(e.getId(), Utils.getHumanName(e)));
    } 
    
    public void addStages(List<Stage> list){
        if (list != null)
            list.stream().forEach(e -> this.stages.put(e.getId(), e.getTitle()));
    }
    
    public void addFutureEvents(List<SingleEvent> list){
        if (list != null)
           list.stream().forEach(e -> this.futureEvents.add(new SingleEventInfo(e.getEvent().getClass().getSimpleName().toLowerCase(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
    }
}
