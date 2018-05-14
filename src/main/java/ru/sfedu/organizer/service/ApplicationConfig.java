/*
 */
package ru.sfedu.organizer.service;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 *
 * @author sterie
 */
@ApplicationPath("/webresources")
public class ApplicationConfig extends Application {
   
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
        resources.add(ru.sfedu.organizer.service.AriaFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.ConcertFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.EventFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.HumanFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.LibrettoFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.NoteFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.OperaFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.PersonageFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.PlaceFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.RoleFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.SingleEventFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.StageFacadeREST.class);
        resources.add(ru.sfedu.organizer.service.UserFacadeREST.class);
    }
    
}
