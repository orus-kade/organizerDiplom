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

    /**
     *
     * @param personageId
     * @param personageTitle
     * @param singerId
     * @param singerName
     */
    public RoleInfo(long personageId, String personageTitle, long singerId, String singerName) {
        this.personageId = personageId;
        this.personageTitle = personageTitle;
        this.singerId = singerId;
        this.singerName = singerName;
    }

    /**
     *
     */
    public RoleInfo() {
    }

    /**
     *
     * @return
     */
    public long getPersonageId() {
        return personageId;
    }

    /**
     *
     * @param personageId
     */
    public void setPersonageId(long personageId) {
        this.personageId = personageId;
    }

    /**
     *
     * @return
     */
    public String getPersonageTitle() {
        return personageTitle;
    }

    /**
     *
     * @param personageTitle
     */
    public void setPersonageTitle(String personageTitle) {
        this.personageTitle = personageTitle;
    }

    /**
     *
     * @return
     */
    public long getSingerId() {
        return singerId;
    }

    /**
     *
     * @param singerId
     */
    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    /**
     *
     * @return
     */
    public String getSingerName() {
        return singerName;
    }

    /**
     *
     * @param singerName
     */
    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

}
