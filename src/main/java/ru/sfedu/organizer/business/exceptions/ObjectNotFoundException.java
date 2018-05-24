/*
 */
package ru.sfedu.organizer.business.exceptions;

import ru.sfedu.organizer.entity.ObjectTypes;

/**
 *
 * @author sterie
 */
public class ObjectNotFoundException extends Exception{
    
    private ObjectTypes objectType;
    private long objectId;

    /**
     *
     */
    public ObjectNotFoundException() {
    }

    /**
     *
     * @param message
     */
    public ObjectNotFoundException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ObjectNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     *
     * @param objectType
     * @param objectId
     */
    public ObjectNotFoundException(ObjectTypes objectType, long objectId) {
        this.objectType = objectType;
        this.objectId = objectId;
    }

    /**
     *
     * @return
     */
    public ObjectTypes getObjectType() {
        return objectType;
    }

    /**
     *
     * @param objectType
     */
    public void setObjectType(ObjectTypes objectType) {
        this.objectType = objectType;
    }

    /**
     *
     * @return
     */
    public long getObjectId() {
        return objectId;
    }

    /**
     *
     * @param objectId
     */
    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }
     
    
    
}
