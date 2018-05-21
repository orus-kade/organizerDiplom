/*
 */
package ru.sfedu.organizer.model;

/**
 *
 * @author sterie
 */
public class RoleInfo {

    long personageId;
    String personageTitle;
    long singerId;
    String singerName;

    public RoleInfo(long personageId, String personageTitle, long singerId, String singerName) {
        this.personageId = personageId;
        this.personageTitle = personageTitle;
        this.singerId = singerId;
        this.singerName = singerName;
    }

    public RoleInfo() {
    }

    public long getPersonageId() {
        return personageId;
    }

    public void setPersonageId(long personageId) {
        this.personageId = personageId;
    }

    public String getPersonageTitle() {
        return personageTitle;
    }

    public void setPersonageTitle(String personageTitle) {
        this.personageTitle = personageTitle;
    }

    public long getSingerId() {
        return singerId;
    }

    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

}
