/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.SingleEvent;


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

    public PlaceModel(long id, String title, String location, String description) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
    }
    
    public void addHumans(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> {
                String name = e.getSurname() + " " + e.getName();
                if (e.getPatronymic() != null) name += " " + e.getPatronymic();
                this.humans.put(e.getId(), name);
            });
    }
    
    public void addEvents(List<SingleEvent> list){
        if (list != null)
            list.stream().forEach(e -> this.events.add(new SingleEventInfo(e.getEvent().getClass().getSimpleName(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
    }
}
