/*
 */
package ru.sfedu.organizer;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.hibernate.Hibernate;
import ru.sfedu.organizer.utils.HibernateUtil;


/**
 *
 * @author sterie
 */
@ApplicationPath("/")
public class OrganizerApplication extends Application {

    public OrganizerApplication() {
        HibernateUtil.getSessionFactory();
    }
    
    
   
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ru.sfedu.organizer.services.AriaService.class);
        resources.add(ru.sfedu.organizer.services.ConcertService.class);
        resources.add(ru.sfedu.organizer.services.HumanService.class);
        resources.add(ru.sfedu.organizer.services.LibrettoService.class);
        resources.add(ru.sfedu.organizer.services.NoteService.class);
        resources.add(ru.sfedu.organizer.services.OperaService.class);
        resources.add(ru.sfedu.organizer.services.PersonageService.class);
        resources.add(ru.sfedu.organizer.services.PlaceService.class);
        resources.add(ru.sfedu.organizer.services.SearchService.class);
        resources.add(ru.sfedu.organizer.services.SingleEventService.class);
        resources.add(ru.sfedu.organizer.services.StageService.class);
        resources.add(ru.sfedu.organizer.services.UserService.class);
        resources.add(ru.sfedu.organizer.services.exceptionhandlers.JsonParseExceptionHandler.class);
        resources.add(ru.sfedu.organizer.services.exceptionhandlers.ObjectNotFoundExceptionHandler.class);
        resources.add(ru.sfedu.organizer.services.interceptors.LoggingInterceptor.class);
        resources.add(ru.sfedu.organizer.services.interceptors.SecurityInterceptor.class);
        
    }    
}
