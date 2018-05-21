/*
 */
package ru.sfedu.organizer.model;

import java.util.*;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.utils.Utils;

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
            list.stream().forEach(e -> this.composers.put(e.getId(), Utils.getHumanName(e)));
    }
    
    public void addWriters(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> this.writers.put(e.getId(), Utils.getHumanName(e)));
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
    
    public long getOperaId(){
        if (this.opera.isEmpty())
            return 0;
        List<Long> keyList = new ArrayList(this.opera.keySet());
        return keyList.get(0);
    }
    
    public List<Long> getWritersId(){
        if (this.writers.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.writers.keySet());
        return keyList;
    }
    
    public List<Long> getComposersId(){
        if (this.composers.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.composers.keySet());
        return keyList;
    }
    
    public List<Long> getPersonagesId(){
        if (this.personages.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.personages.keySet());
        return keyList;
    }
}
