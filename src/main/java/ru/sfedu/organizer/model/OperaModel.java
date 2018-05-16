/*
 */
package ru.sfedu.organizer.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;

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

    public OperaModel() {
    }

    public OperaModel(long id, String title, String description, long librettoId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.librettoId = librettoId;
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

    public Map<Long, String> getPersonages() {
        return personages;
    }

    public void addPersonages(List<Personage> list){
        list.forEach(e -> this.personages.put(e.getId(), e.getName())
        );
    }

    public Map<Long, String> getAries() {
        return aries;
    }

    public void addAries(List<Aria> list){
        list.forEach(e -> this.aries.put(e.getId(), e.getPosition() + " " + e.getTitle())
        );
    }

    public long getLibrettoId() {
        return librettoId;
    }

    public void setLibrettoId(long librettoId) {
        this.librettoId = librettoId;
    }
    

    public Map<Long, String> getWriters() {
        return writers;
    }

    public void addWriters(List<Human> list){
        list.forEach(e -> {
            String name = e.getSurname() + " " + e.getName();
            if (e.getPatronymic() != null) name += " " + e.getPatronymic();
            this.writers.put(e.getId(), name);
        });
    }
    
    public void addComposers(List<Human> list){
        list.forEach(e -> {
            String name = e.getSurname() + " " + e.getName();
            if (e.getPatronymic() != null) name += " " + e.getPatronymic();
            this.composers.put(e.getId(), name);
        });
    }

    public Map<Long, String> getComposers() {
        return composers;
    }   
    
}
