/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Aria;

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

    public PersonageModel(long id, String name, long operaId, String opreTitle, String description) {
        this.id = id;
        this.name = name;
        this.operaId = operaId;
        this.opreTitle = opreTitle;
        this.description = description;
    }

    public void addAries(List<Aria> list){
        if (list != null)
            list.forEach(e -> this.aries.put(e.getId(), e.getPosition() + " " + e.getTitle())
            );
    }   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOperaId() {
        return operaId;
    }

    public void setOperaId(long operaId) {
        this.operaId = operaId;
    }

    public String getOpreTitle() {
        return opreTitle;
    }

    public void setOpreTitle(String opreTitle) {
        this.opreTitle = opreTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Long> getAriesId(){
        if (this.aries.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.aries.keySet());
        return keyList;
    }
    
}
