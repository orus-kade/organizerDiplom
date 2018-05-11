package ru.sfedu.organizer.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.organizer.model.*;


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
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
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
            metadataSources.addResource("named-queries.hbm.xml");// Именованные запросы
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }

        return sessionFactory;
    }
}
