/*
 */
package ru.sfedu.organizer.model;

import ru.sfedu.organizer.entity.Aria;

/**
 *
 * @author sterie
 */
public class AriaInfo {
        long id;
        String title;
        long operaId;
        String operaTitle;
        
    public AriaInfo(Aria aria) {
        this.id = aria.getId();
        this.title = aria.getTitle();
        this.operaId = aria.getOpera().getId();
        this.operaTitle = aria.getOpera().getTitle();
    }

    public AriaInfo() {
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
    
    
        
}
