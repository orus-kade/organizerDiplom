/*
 */
package ru.sfedu.organizer.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 *
 * @author sterie
 */
public class MyGenerator {
    
    public static long generateDateLong(){
        long begin = (new GregorianCalendar(1970, 0, 0)).getTimeInMillis();
        long end = new Date().getTime();
        return generateDateLong(begin, end);
    }
    
    public static long generateDateLong(Date begin, Date end){
        return generateDateLong(begin.getTime(), end.getTime());
    }
    
    public static long generateDateLong(long begin, long end){
        if (begin > end){
            long l = begin;
            begin = end;
            end = l;
        }
        long offset = Math.round(Math.random()*(end-begin));
        return begin + offset;        
    }
    
    public static String generateTitle(){
        List<String> list = Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "Deimos", "Phobos", "Moon");
        int count = list.size();
        return list.get((int)(Math.random()*count));
    } 
    
    public static long generateLong(){
        long begin = 0;
        long end = Long.MAX_VALUE;
        return generateDateLong(begin, end);
    }
    
    public static long generateLong(long begin, long end){
        return generateDateLong(begin, end);
    }
    
    public static int generateInt(){
        int begin = 0;
        int end = Integer.MAX_VALUE;
        return generateInt(begin, end);
    }
    
    public static int generateInt(int begin, int end){
        if (begin > end){
            int i = begin;
            begin = end;
            end = i;
        }
        int offset = (int)Math.round(Math.random()*(end-begin));
        return begin + offset;
    }
}
