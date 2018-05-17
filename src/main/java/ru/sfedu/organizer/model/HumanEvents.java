/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.sfedu.organizer.entity.Event;
import ru.sfedu.organizer.entity.SingleEvent;

/**
 *
 * @author sterie
 */
public class HumanEvents {
    
    private List<SingleEventInfo> futureEvents = new ArrayList<>();
    private List<EventInfo> directorEvents = new ArrayList<>();
    private List<EventInfo> singerEvents = new ArrayList<>();

    public HumanEvents() {
    }
    
    public void addFutureEvents(List<SingleEvent> list){
        list.stream().forEach(e -> this.futureEvents.add(new SingleEventInfo(e.getEvent().getClass().getSimpleName().toLowerCase(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
    }
    
    public void addDirectorEvents(List<Event> list){
        list.stream().forEach(e -> this.directorEvents.add(new EventInfo(e.getClass().getSimpleName(), e.getId(), e.getTitle())));
    }
    
    public void addSingerEvents(List<Event> list){
        list.stream().forEach(e -> this.singerEvents.add(new EventInfo(e.getClass().getSimpleName(), e.getId(), e.getTitle())));
    }

    
    
    class EventInfo{
        String type;
        long id;
        String title;  

        public EventInfo(String type, long id, String title) {
            this.type = type;
            this.id = id;
            this.title = title;
        }        
    }
}
