/*
 */
package ru.sfedu.organizer.model;

import java.util.*;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;

/**
 *
 * @author sterie
 */
public class AriaModel {
    private long id;
    private String title;
    private String text;
    private Map<Long, String> composers = new HashMap();
    private Map<Long, String> writers = new HashMap();
    private Map<Long, String> opera = new HashMap();
    private Map<Long, String> personages = new HashMap();
    private int position;

    public AriaModel() {
    }
  
    public AriaModel(long id, String title, String text, int position) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.position = position;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public void addComposers(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> {
                String name = e.getSurname() + " " + e.getName();
                if (e.getPatronymic() != null) name += " " + e.getPatronymic();
                this.composers.put(e.getId(), name);
            });
    }
    
    public void addWriters(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> {
                String name = e.getSurname() + " " + e.getName();
                if (e.getPatronymic() != null) name += " " + e.getPatronymic();
                this.writers.put(e.getId(), name);
            });
    }
    
    public void addPersonages(List<Personage> list){
        if (list != null)
            list.stream().forEach(e -> this.personages.put(e.getId(), e.getName())
            );
    }
    
    public void addOpera(Opera opera){
        if (opera != null){
            this.opera.put(opera.getId(), opera.getTitle());
        }
    }
}
