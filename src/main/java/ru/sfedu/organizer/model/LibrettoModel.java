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

    /**
     *
     * @param id
     * @param operaId
     * @param operaTitle
     * @param text
     */
    public LibrettoModel(long id, long operaId, String operaTitle, String text) {
        this.id = id;
        this.operaId = operaId;
        this.operaTitle = operaTitle;
        this.text = text;
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
    public String getOperaTitle() {
        return operaTitle;
    }

    /**
     *
     * @param operaTitle
     */
    public void setOperaTitle(String operaTitle) {
        this.operaTitle = operaTitle;
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
    public List<Long> getWritersId(){
        if (this.writers.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.writers.keySet());
        return keyList;
    }
    
}
