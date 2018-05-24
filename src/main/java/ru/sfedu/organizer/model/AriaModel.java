/*
 */
package ru.sfedu.organizer.model;

import java.util.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */
@XmlRootElement
public class AriaModel {
    @XmlAttribute
    private long id;
    @XmlAttribute
    private String title;
    @XmlAttribute
    private String text;
    private Map<Long, String> composers = new HashMap();
    private Map<Long, String> writers = new HashMap();
    private Map<Long, String> opera = new HashMap();
    private Map<Long, String> personages = new HashMap();
    @XmlAttribute
    private int position;

    /**
     *
     */
    public AriaModel() {
    }
  
    /**
     *
     * @param id
     * @param title
     * @param text
     * @param position
     */
    public AriaModel(long id, String title, String text, int position) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.position = position;
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
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }
    
    /**
     *
     * @param list
     */
    public void addComposers(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> this.composers.put(e.getId(), Utils.getHumanName(e)));
    }
    
    /**
     *
     * @param list
     */
    public void addWriters(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> this.writers.put(e.getId(), Utils.getHumanName(e)));
    }
    
    /**
     *
     * @param list
     */
    public void addPersonages(List<Personage> list){
        if (list != null)
            list.stream().forEach(e -> this.personages.put(e.getId(), e.getName())
            );
    }
    
    /**
     *
     * @param opera
     */
    public void addOpera(Opera opera){
        if (opera != null){
            this.opera.put(opera.getId(), opera.getTitle());
        }
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
    public List<Long> getWritersId(){
        if (this.writers.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.writers.keySet());
        return keyList;
    }
    
    /**
     *
     * @return
     */
    public List<Long> getComposersId(){
        if (this.composers.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.composers.keySet());
        return keyList;
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
}
