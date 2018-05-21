/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.utils.Utils;

/**
 *
 * @author sterie
 */
public class LibrettoModel {
    private long id;
    private long operaId;
    private String operaTitle;
    private Map<Long, String> writers = new HashMap<>();
    private String text;

    public LibrettoModel(long id, long operaId, String operaTitle, String text) {
        this.id = id;
        this.operaId = operaId;
        this.operaTitle = operaTitle;
        this.text = text;
    }
    
    public void addWriters(List<Human> list){
        if (list != null)
            list.stream().forEach(e -> this.writers.put(e.getId(), Utils.getHumanName(e)));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOperaId() {
        return operaId;
    }

    public void setOperaId(long operaId) {
        this.operaId = operaId;
    }

    public String getOperaTitle() {
        return operaTitle;
    }

    public void setOperaTitle(String operaTitle) {
        this.operaTitle = operaTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public List<Long> getWritersId(){
        if (this.writers.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.writers.keySet());
        return keyList;
    }
    
}
