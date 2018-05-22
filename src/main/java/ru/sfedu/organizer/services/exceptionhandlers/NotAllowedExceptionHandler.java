/*
 */
package ru.sfedu.organizer.services.exceptionhandlers;

/**
 *
 * @author sterie
 */

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
public class NotAllowedExceptionHandler implements ExceptionMapper<NotAllowedException>{

    Logger logger = LogManager.getLogger(NotAllowedExceptionHandler.class);
    
    @Override
    public Response toResponse(NotAllowedException exception) {
        logger.debug(exception);
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
    
}
