/*
 */
package ru.sfedu.organizer.services.handlers;

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

/**
 *
 * @author sterie
 */
@Provider
public class NotAllowedExceptionHandler implements ExceptionMapper<NotAllowedException>{

    Logger logger = LogManager.getLogger(NotAllowedExceptionHandler.class);
    
    /**
     *
     * @param exception
     * @return
     */
    @Override
    public Response toResponse(NotAllowedException exception) {
        logger.debug(exception);
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
    
}
