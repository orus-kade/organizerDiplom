/*
 */
package ru.sfedu.organizer.model;

/**
 *
 * @author sterie
 */
public class SearchResult {
    private String type;
    private long id;
    private String name;

    public SearchResult(String type, long id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
    }    
    
}
