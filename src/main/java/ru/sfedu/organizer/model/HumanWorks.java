/*
 */
package ru.sfedu.organizer.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.Opera;

/**
 *
 * @author sterie
 */
public class HumanWorks {
    
    private List<AriaInfo> composerWorks = new ArrayList<>();
    private List<AriaInfo> writerWorks = new ArrayList<>();
    private List<LibrettoInfo> librettos = new ArrayList<>();

    /**
     *
     */
    public HumanWorks() {
    }
    
    /**
     *
     * @param list
     */
    public void addComposerWorks(List<Aria> list){
        if (list != null)
            list.stream().forEach(e -> this.composerWorks.add(new AriaInfo(e)));
    }
    
    /**
     *
     * @param list
     */
    public void addWriterWorks(List<Aria> list){
        if (list != null)
            list.stream().forEach(e -> this.writerWorks.add(new AriaInfo(e)));
    }
    
    /**
     *
     * @param list
     */
    public void addLibrettos(List<Libretto> list){
        if (list != null)
            list.stream().forEach(e -> this.librettos.add(new LibrettoInfo(e.getOpera(), e.getId())));
    }

    class LibrettoInfo{
        long operaId;
        String operaTitle;
        long id;

        public LibrettoInfo(Opera opera, long id) {
            this.operaId = opera.getId();
            this.operaTitle = opera.getTitle();
            this.id = id;
        }
    }
}




