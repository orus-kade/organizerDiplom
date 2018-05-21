/*
 */
package ru.sfedu.organizer.model;

/**
 *
 * @author sterie
 */
public class SearchResult{
    private String type;
    private long id;
    private String name;

    public SearchResult(String type, long id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SearchResult() {
    }
    
    
    
}
