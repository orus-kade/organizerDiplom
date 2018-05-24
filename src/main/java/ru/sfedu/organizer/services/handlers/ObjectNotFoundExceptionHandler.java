/*
 */
package ru.sfedu.organizer.services.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;

/**
 *
 * @author sterie
 */
@Provider
public class ObjectNotFoundExceptionHandler implements ExceptionMapper<ObjectNotFoundException>{

    Logger logger = LogManager.getLogger(ObjectNotFoundExceptionHandler.class);
    
    /**
     *
     * @param exception
     * @return
     */
    @Override
    public Response toResponse(ObjectNotFoundException exception) {
        String msg = "Object id: " + exception.getObjectId() + " Object type: " + exception.getObjectType().toString() + " was not found.";
        logger.error(msg);
        return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();  
    }    
}
