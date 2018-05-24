/*
 */
package ru.sfedu.organizer.model;

import java.util.Date;

/**
 *
 * @author sterie
 */
public class SingleEventInfo{
        String type;
        long id;
        String title;
        Date time;

    /**
     *
     * @param type
     * @param id
     * @param title
     * @param time
     */
    public SingleEventInfo(String type, long id, String title, Date time) {
            this.type = type;
            this.id = id;
            this.title = title;
            this.time = time;
        }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
    public Date getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }
        
        
    }
