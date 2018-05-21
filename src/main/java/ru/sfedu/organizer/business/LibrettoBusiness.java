/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.dao.LibrettoDao;
import ru.sfedu.organizer.dao.OperaDao;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.LibrettoModel;

/**
 *
 * @author sterie
 */
@Stateless
public class LibrettoBusiness {
    
    private static final LibrettoDao librettoDao = new LibrettoDao();
    
    private HumanDao humanDao = new HumanDao();
    
    @EJB 
    private HumanBusiness humanBusiness = new HumanBusiness();

    private OperaDao operaDao = new OperaDao();
    
    public LibrettoBusiness() {
    }

    public LibrettoModel getById(long id){
        Optional<Libretto> o = librettoDao.getById(id);
        LibrettoModel librettoModel = null;
        if (o.isPresent()){
            Libretto libretto = o.get();
            librettoModel = new LibrettoModel(libretto.getId(), libretto.getOpera().getId(), libretto.getOpera().getTitle(), libretto.getText());   
            librettoModel.addWriters(libretto.getWriters());
        }
        return librettoModel;
    }    
    
    public void delete(long id){
        Optional<Libretto> o = librettoDao.getById(id);
        if (o.isPresent()){
            librettoDao.delete(o.get());
        }
    }
    
    public long createOrSave (LibrettoModel librettoModel) throws ObjectNotFoundException{
        Libretto libretto;
        if (librettoModel.getId() <= 0){
            libretto = new Libretto();
        }    
        else{
            Optional<Libretto> o = librettoDao.getById(librettoModel.getId());
            if (!o.isPresent()) throw new ObjectNotFoundException(ObjectTypes.LIBRETTO, librettoModel.getId());
            libretto = o.get();
        }
        Optional<Opera> oOpera = operaDao.getById(librettoModel.getOperaId());
            if (!oOpera.isPresent()) throw new ObjectNotFoundException(ObjectTypes.OPERA, librettoModel.getId());
            Opera opera = oOpera.get();
            libretto.setOpera(opera);
        libretto.setText(librettoModel.getText());
        if (librettoModel.getWritersId() != null){
                List<Human> writers = new ArrayList<>();
                librettoModel.getWritersId().stream().forEach(e -> {
                    Optional<Human> o = humanDao.getById(e);
                    if (o.isPresent())
                        writers.add(o.get());
                });
                writers.removeIf(e -> !humanBusiness.isHumanProfession(e, Professions.WRITER));
                libretto.setWriters(writers);
            }
        librettoDao.saveOrUpdate(libretto);
        return libretto.getId();
    }
}
