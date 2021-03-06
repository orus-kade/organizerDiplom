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
    private List<MediaLink> links = new ArrayList();

    /**
     *
     * @param id
     * @param title
     * @param description
     */
    public StageModel(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    
    /**
     *
     * @param opera
     */
    public void addOpera(Opera opera){
        if (opera != null)
            this.opera.put(opera.getId(), opera.getTitle());
    }
    
    /**
     *
     * @param human
     */
    public void addDirector(Human human){
        if (human != null){
            String name = human.getSurname() + " " + human.getName();
            if (human.getPatronymic() != null) name += " " + human.getPatronymic();
            this.director.put(human.getId(), name);
        }           
    }
    
    /**
     *
     * @param list
     */
    public void addRoles(List<Role> list){
        if (list != null)
            list.stream().forEach(e -> {
                String name = e.getSinger().getSurname() + " " + e.getSinger().getName();
                if (e.getSinger().getPatronymic() != null) name += " " + e.getSinger().getPatronymic();
                this.roles.add(new RoleInfo(e.getPersonage().getId(), e.getPersonage().getName(), e.getSinger().getId(), name));
            });     
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
    public long getOperaId(){
        if (this.opera.isEmpty())
            return 0;
        List<Long> keyList = new ArrayList(this.opera.keySet());
        return keyList.get(0);
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
    public List<RoleInfo> getRoles() {
        return roles;
    }

    public List<MediaLink> getLinks() {
        return links;
    }

    public void setLinks(List<MediaLink> links) {
        this.links = links;
    }
    
    

}
