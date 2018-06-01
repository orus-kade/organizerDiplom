package ru.sfedu.organizer.utils;


import ru.sfedu.organizer.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author sterie
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Создание фабрики
     *
     * @return 
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
// loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
            MetadataSources metadataSources
                    = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(Aria.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(Personage.class);
            metadataSources.addAnnotatedClass(Concert.class);
            metadataSources.addAnnotatedClass(Event.class);
            metadataSources.addAnnotatedClass(Human.class);
            metadataSources.addAnnotatedClass(Libretto.class);
            metadataSources.addAnnotatedClass(Note.class);
            metadataSources.addAnnotatedClass(Opera.class);
            metadataSources.addAnnotatedClass(Place.class);
            metadataSources.addAnnotatedClass(Role.class);
            metadataSources.addAnnotatedClass(SingleEvent.class);
            metadataSources.addAnnotatedClass(Stage.class);
            metadataSources.addAnnotatedClass(User.class);
            metadataSources.addAnnotatedClass(MediaLink.class);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }

    
    @Override
    protected void finalize() throws Throwable {
        this.finalize();
    }
}
