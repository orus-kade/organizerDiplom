/*
 */
package ru.sfedu.organizer.services.exceptionhandlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;

/**
 *
 * @author sterie
 */
@Provider
public class ObjectNotFoundExceptionHandler implements ExceptionMapper<ObjectNotFoundException>{

    @Override
    public Response toResponse(ObjectNotFoundException exception) {
        String msg = "Object id: " + exception.getObjectId() + " Object type: " + exception.getObjectType().toString() + " was not found.";
        return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();  
    }    
}
