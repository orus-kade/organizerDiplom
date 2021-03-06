/*
 */
package ru.sfedu.organizer.services.interceptors;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.jboss.resteasy.util.Base64;
import ru.sfedu.organizer.business.UserBusiness;
import ru.sfedu.organizer.model.UserModel;

/**
 *
 * @author sterie
 */
@Provider
@ServerInterceptor
public class SecurityInterceptor implements PreProcessInterceptor {

    Logger logger = LogManager.getLogger(SecurityInterceptor.class);

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());

    @EJB
    private UserBusiness userBusiness = new UserBusiness();

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

        long id;
        String st = hr.getUri().getQueryParameters().getFirst("userId");
        if (st != null) {
            id = Long.valueOf(st);
        }
        else id = 0;

        Method method = rmi.getMethod();

        //Access allowed for all 
        if (method.isAnnotationPresent(PermitAll.class)) {
            logger.info("permit all");
            return null;
        }
        //Access denied for all 
        if (method.isAnnotationPresent(DenyAll.class)) {
            logger.info("deny all");
            return ACCESS_FORBIDDEN;
        }

        //Get request headers
        final HttpHeaders headers = hr.getHttpHeaders();

        //Fetch authorization header
        final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);

        //If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {
            return ACCESS_DENIED;
        }

        //Get encoded username and password
        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password
        String usernameAndPassword;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
        } catch (IOException e) {
            return SERVER_ERROR;
        }

        //Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        //Verify user access
        if (method.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            String role = rolesAnnotation.value()[0];

            //Is user valid?
            if (!isUserAllowed(username, password, role, id)) {
                return ACCESS_DENIED;
            }
        }

        //Return null to continue request processing
        return null;
    }

    private boolean isUserAllowed(final String username, final String password, final String role, final long id) {
        boolean isAllowed = false;

        UserModel userModel = userBusiness.login(username, password);
        if (userModel != null){
            if (userModel.getRoles().contains(role)){
                if (role.equals("USER")){
                    isAllowed = (id == userModel.getUserId());
                }
                else isAllowed = true;
            }
        } 
        return isAllowed;
    }
}
