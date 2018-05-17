/*
 */
package ru.sfedu.organizer.services;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 *
 * @author sterie
 */
@ApplicationPath("/")
public class OrganizerApplication extends Application {
   
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
        resources.add(ru.sfedu.organizer.services.AriaConrtoller.class);
        resources.add(ru.sfedu.organizer.services.ConcertController.class);
        resources.add(ru.sfedu.organizer.services.HumanController.class);
        resources.add(ru.sfedu.organizer.services.LibrettoController.class);
        resources.add(ru.sfedu.organizer.services.NoteFacadeREST.class);
        resources.add(ru.sfedu.organizer.services.OperaController.class);
        resources.add(ru.sfedu.organizer.services.PersonageController.class);
        resources.add(ru.sfedu.organizer.services.PlaceController.class);
        resources.add(ru.sfedu.organizer.services.RoleFacadeREST.class);
        resources.add(ru.sfedu.organizer.services.SingleEventController.class);
        resources.add(ru.sfedu.organizer.services.StageFacadeREST.class);
        resources.add(ru.sfedu.organizer.services.UserFacadeREST.class);
    }    
}
