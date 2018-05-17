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
        
}
