/*
 */
package ru.sfedu.organizer.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import org.hibernate.Hibernate;
import ru.sfedu.organizer.dao.Dao;
import ru.sfedu.organizer.entity.Aria;

/**
 *
 * @author sterie
 */
public class InitialiseUtil {
    
    /**
     *
     * @param <T>
     * @param entity
     * @return
     */
    public static <T> T forcedInitialise(T entity){
        try {
            for(PropertyDescriptor propertyDescriptor :
                    Introspector.getBeanInfo(entity.getClass(), Object.class).getPropertyDescriptors()){
                
                // propertyEditor.getReadMethod() exposes the getter
                // btw, this may be null if you have a write-only property
                if (!Hibernate.isInitialized(propertyDescriptor.getReadMethod().invoke(entity, (Object[])null))){
                    Hibernate.initialize(propertyDescriptor.getReadMethod().invoke(entity, (Object[])null));
                }
                
            }
        } catch (IntrospectionException ex) {
            java.util.logging.Logger.getLogger(Dao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            java.util.logging.Logger.getLogger(Dao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(Dao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return entity;
    }
}
