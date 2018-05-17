/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Role;
import ru.sfedu.organizer.entity.SingleEvent;

/**
 *
 * @author sterie
 */
public class StageModel {
    
    private long id;
    private String title;
    private String description;
    private Map<Long, String> opera = new HashMap<>();
    private Map<Long, String> director = new HashMap<>();
    private List<RoleInfo> roles = new ArrayList<>();
    private Map<Long, Date> events = new HashMap<>();

    public StageModel(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    
    public void addOpera(Opera opera){
        if (opera != null)
            this.opera.put(opera.getId(), opera.getTitle());
    }
    
    public void addDirector(Human human){
        if (human != null){
            String name = human.getSurname() + " " + human.getName();
            if (human.getPatronymic() != null) name += " " + human.getPatronymic();
            this.director.put(human.getId(), name);
        }           
    }
    
    public void addRoles(List<Role> list){
        if (list != null)
            list.stream().forEach(e -> {
                String name = e.getSinger().getSurname() + " " + e.getSinger().getName();
                if (e.getSinger().getPatronymic() != null) name += " " + e.getSinger().getPatronymic();
                this.roles.add(new RoleInfo(e.getPersonage().getId(), e.getPersonage().getName(), e.getSinger().getId(), name));
            });     
    }
    
    public void addEvents(List<SingleEvent> list){
        if (list != null)
            list.stream().forEach(e -> this.events.put(e.getId(), new Date(e.getDatetime())));
    }
    
    class RoleInfo{
        long personageId;
        String personageTitle;
        long singerId;
        String singerName;

        public RoleInfo(long personageId, String personageTitle, long singerId, String singerName) {
            this.personageId = personageId;
            this.personageTitle = personageTitle;
            this.singerId = singerId;
            this.singerName = singerName;
        }       
        
    }
}
