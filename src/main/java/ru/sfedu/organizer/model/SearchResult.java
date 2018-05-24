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

    /**
     *
     * @param type
     * @param id
     * @param name
     */
    public SearchResult(String type, long id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     */
    public SearchResult() {
    }
    
    
    
}
