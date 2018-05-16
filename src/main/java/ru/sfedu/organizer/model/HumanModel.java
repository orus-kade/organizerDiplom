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

    public HumanModel(long id, String name, String surname, String patronymic, String biography, Date birthDate, Date deathDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.biography = biography;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }
   
    public void addPlaces(List<Place> list){
        list.stream().forEach(e -> this.places.put(e.getId(), e.getTitle()));
    }
    
    public void addProfessions(List<Professions> list){
        list.stream().forEach(e -> this.professions.add(e.toString()));
    }
}
