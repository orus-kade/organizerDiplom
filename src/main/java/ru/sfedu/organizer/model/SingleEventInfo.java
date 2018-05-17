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
    }
