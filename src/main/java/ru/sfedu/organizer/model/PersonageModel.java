/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.MediaLink;

/**
 *
 * @author sterie
 */
public class PersonageModel {
    private long id;
    private String name;
    private long operaId;
    private String opreTitle;
    private String description;
    private Map<Long, String> aries = new HashMap<>(); 
    private List<MediaLink> links = new ArrayList();

    /**
     *
     * @param id
     * @param name
     * @param operaId
     * @param opreTitle
     * @param description
     */
    public PersonageModel(long id, String name, long operaId, String opreTitle, String description) {
        this.id = id;
        this.name = name;
        this.operaId = operaId;
        this.opreTitle = opreTitle;
        this.description = description;
    }

    /**
     *
     * @param list
     */
    public void addAries(List<Aria> list){
        if (list != null)
            list.forEach(e -> this.aries.put(e.getId(), e.getPosition() + " " + e.getTitle())
            );
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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public long getOperaId() {
        return operaId;
    }

    /**
     *
     * @param operaId
     */
    public void setOperaId(long operaId) {
        this.operaId = operaId;
    }

    /**
     *
     * @return
     */
    public String getOpreTitle() {
        return opreTitle;
    }

    /**
     *
     * @param opreTitle
     */
    public void setOpreTitle(String opreTitle) {
        this.opreTitle = opreTitle;
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
    public List<Long> getAriesId(){
        if (this.aries.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.aries.keySet());
        return keyList;
    }

    public List<MediaLink> getLinks() {
        return links;
    }

    public void setLinks(List<MediaLink> links) {
        this.links = links;
    }
    
    
    
}
