/*
 */
package ru.sfedu.organizer.services.handlers;

/**
 *
 * @author sterie
 */

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author sterie
 */
@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException>{

    Logger logger = LogManager.getLogger(NotFoundExceptionHandler.class);
    
    /**
     *
     * @param exception
     * @return
     */
    @Override
    public Response toResponse(NotFoundException exception) {
        logger.debug(exception);
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
}
