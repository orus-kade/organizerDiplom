/*
 */
package ru.sfedu.organizer.services.exceptionhandlers;

import com.google.gson.JsonParseException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author sterie
 */
@Provider
public class JsonParseExceptionHandler implements ExceptionMapper<JsonParseException>{

    @Override
    public Response toResponse(JsonParseException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();  
    }
    
}
