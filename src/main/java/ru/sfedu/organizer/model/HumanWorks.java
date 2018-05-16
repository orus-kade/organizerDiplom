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

    public HumanWorks() {
    }
    
    public void addComposerWorks(List<Aria> aries){
//        aries.stream().sorted(new Comparator<Aria>(){
//            @Override
//            public int compare(Aria o1, Aria o2) {
//                if (o1.getOpera().getId() == o2.getOpera().getId()) return 0;
//                else if (o1.getOpera().getId()> o2.getOpera().getId()) return 1;
//                else return -1;
//            }           
//        }).forEachOrdered(e -> {
//            long operaId = e.getOpera().getId();
//            
//        });
        aries.stream().forEach(e -> this.composerWorks.add(new AriaInfo(e)));
    }
    
    public void addWriterWorks(List<Aria> aries){
        aries.stream().forEach(e -> this.writerWorks.add(new AriaInfo(e)));
    }
    
    public void addLibrettos(List<Libretto> libs){
        libs.stream().forEach(e -> this.librettos.add(new LibrettoInfo(e.getOpera(), e.getId())));
    }
    
    class AriaInfo{
        long id;
        String title;
        long operaId;
        String operaTitle;
        public AriaInfo(Aria aria) {
            this.id = aria.getId();
            this.title = aria.getTitle();
            this.operaId = aria.getOpera().getId();
            this.operaTitle = aria.getOpera().getTitle();
        }
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




