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

        public SingleEventInfo(String type, long id, String title, Date time) {
            this.type = type;
            this.id = id;
            this.title = title;
            this.time = time;
        }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
        
        
    }
