/*
 */
package ru.sfedu.organizer.utils;

import java.util.Calendar;
import ru.sfedu.organizer.entity.Human;

/**
 *
 * @author sterie
 */
public class Utils {    

    /**
     *
     * @param human
     * @return
     */
    public static String getHumanName(Human human){
        String name = human.getSurname() + " " + human.getName();
        if (human.getPatronymic() != null) name += " " + human.getPatronymic();
        return name;
    }
    
    /**
     *
     * @return
     */
    public static long getCurrentDateWithoutTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);        
        return cal.getTimeInMillis();
    }
}
