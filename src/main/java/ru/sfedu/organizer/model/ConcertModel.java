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
    
    public void addDirector(Human human){
        if (human != null){
            String name = human.getSurname() + " " + human.getName();
            if (human.getPatronymic() != null) name += " " + human.getPatronymic();
            this.director.put(human.getId(), name);
        }            
    }
    
    public void addSingers(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> {
                String name = e.getSurname() + " " + e.getName();
                if (e.getPatronymic() != null) name += " " + e.getPatronymic();
                this.singers.put(e.getId(), name);
            });
    }
    public void addAries(List<Aria> list){
        if (list != null)
            list.stream().forEach(e -> this.aries.add(new AriaInfo(e)));
    }
    
    public void addEvents(List<SingleEvent> list){
        if (list != null)
            list.stream().forEach(e -> this.events.put(e.getId(), new Date(e.getDatetime())));
    }
}
