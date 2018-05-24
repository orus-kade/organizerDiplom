/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.entity.Professions;

/**
 *
 * @author sterie
 */
public class HumanModel {
    private long id;
    private String name;
    private String surname;
    private String patronymic;
    private String biography;
    private Date birthDate;
    private Date deathDate;
    private Map<Long, String> places = new HashMap<>();
    private List<String> professions = new ArrayList<>();
    private String voice;

    /**
     *
     * @param id
     * @param name
     * @param surname
     * @param patronymic
     * @param biography
     * @param birthDate
     * @param deathDate
     */
    public HumanModel(long id, String name, String surname, String patronymic, String biography, Date birthDate, Date deathDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.biography = biography;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    /**
     *
     */
    public HumanModel() {
    }
   
    /**
     *
     * @param list
     */
    public void addPlaces(List<Place> list){
        if (list != null)
            list.stream().forEach(e -> this.places.put(e.getId(), e.getTitle()));
    }
    
    /**
     *
     * @param list
     */
    public void addProfessions(List<Professions> list){
        if (list != null)
            list.stream().forEach(e -> this.professions.add(e.toString()));
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
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     *
     * @param patronymic
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     *
     * @return
     */
    public String getBiography() {
        return biography;
    }

    /**
     *
     * @param biography
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     *
     * @return
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     *
     * @return
     */
    public Date getDeathDate() {
        return deathDate;
    }

    /**
     *
     * @param deathDate
     */
    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    /**
     *
     * @return
     */
    public List<Long> getPlacesIds() {
        if (this.places.isEmpty())
            return null;
        List<Long> keyList = new ArrayList(this.places.keySet());
        return keyList;
    }

    /**
     *
     * @return
     */
    public List<String> getProfessions() {
        return professions;
    }

    /**
     *
     * @return
     */
    public String getVoice() {
        return voice;
    }

    /**
     *
     * @param voice
     */
    public void setVoice(String voice) {
        this.voice = voice;
    }    
    
}
