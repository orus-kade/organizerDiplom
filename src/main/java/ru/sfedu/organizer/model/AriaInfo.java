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
        
    /**
     *
     * @param aria
     */
    public AriaInfo(Aria aria) {
        this.id = aria.getId();
        this.title = aria.getTitle();
        this.operaId = aria.getOpera().getId();
        this.operaTitle = aria.getOpera().getTitle();
    }

    /**
     *
     */
    public AriaInfo() {
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
    
    
        
}
