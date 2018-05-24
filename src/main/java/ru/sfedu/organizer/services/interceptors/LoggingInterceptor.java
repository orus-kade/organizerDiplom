/*
 */
package ru.sfedu.organizer.services.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

/**
 *
 * @author sterie
 */
@Provider
@ServerInterceptor
public class LoggingInterceptor implements PreProcessInterceptor{
    Logger logger = LogManager.getLogger(LoggingInterceptor.class);

	@Context
	HttpServletRequest servletRequest;

    /**
     *
     * @param hr
     * @param rmi
     * @return
     * @throws Failure
     * @throws WebApplicationException
     */
    @Override
    public ServerResponse preProcess(HttpRequest hr, ResourceMethodInvoker rmi) throws Failure, WebApplicationException {
        String methodName = rmi.getMethod().getName();
		logger.info("Receiving request from: " + servletRequest.getRemoteAddr());
		logger.info("Attempt to invoke method \"" + methodName + "\"");
                hr.getHttpHeaders().getCookies().values();
		return null;
    }
    
}
