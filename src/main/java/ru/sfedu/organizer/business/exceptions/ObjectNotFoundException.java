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

    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }

    public ObjectNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ObjectNotFoundException(ObjectTypes objectType, long objectId) {
        this.objectType = objectType;
        this.objectId = objectId;
    }

    public ObjectTypes getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectTypes objectType) {
        this.objectType = objectType;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }
     
    
    
}
