/*
 */
package ru.sfedu.organizer.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Human;

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
        list.stream().forEach(e -> {
            String name = e.getSurname() + " " + e.getName();
            if (e.getPatronymic() != null) name += " " + e.getPatronymic();
            this.writers.put(e.getId(), name);
        });
    }
}
