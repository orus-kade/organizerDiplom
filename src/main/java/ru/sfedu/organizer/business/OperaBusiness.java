/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.dao.LibrettoDao;
import ru.sfedu.organizer.dao.OperaDao;
import ru.sfedu.organizer.dao.PersonageDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Libretto;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.OperaModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
public class OperaBusiness {

    private static OperaDao operaDao = new OperaDao();

    private LibrettoDao librettoDao = new LibrettoDao();

    private PersonageDao personageDao = new PersonageDao();
    
    private AriaDao ariaDao =  new AriaDao();

    public OperaBusiness() {
    }

    public OperaModel getById(long id) {
        Optional<Opera> o = operaDao.getById(id);
        OperaModel operaModel = null;
        if (o.isPresent()) {
            Opera opera = o.get();
            operaModel = new OperaModel(opera.getId(), opera.getTitle(), opera.getDescription());
            operaModel.addAries(opera.getAries());
            if (opera.getLibretto() != null) {
                operaModel.setLibrettoId(opera.getLibretto().getId());
            }
            operaModel.addPersonages(opera.getPersonages());
            operaModel.addComposers(operaDao.getHumansByProfession(opera, Professions.COMPOSER));
            operaModel.addWriters(operaDao.getHumansByProfession(opera, Professions.WRITER));
            operaModel.addStages(opera.getStages());
            operaModel.addFutureEvents(operaDao.getFutureEvents(opera.getStages()));
        }
        return operaModel;
    }

    public void delete(long id) {
        Optional<Opera> o = operaDao.getById(id);
        if (o.isPresent()) {
            operaDao.delete(o.get());
        }
    }

    public List<SearchResult> getByRange(int from, int to) {
        Optional<List> o;
        if (from == 0 && to == 0) {
            o = operaDao.getAll();
        } else {
            o = operaDao.getByRange(from, to);
        }
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()) {
            List<Opera> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }

    public List<SearchResult> getAll() {
        return this.getByRange(0, 0);
    }

    public int count() {
        return operaDao.count();
    }

    public List<SearchResult> search(String key) {
        if (key == null || key.trim().length() == 0) {
            return this.getAll();
        }
        String keyNew = key.trim().toLowerCase();
        Optional<List> o = operaDao.search(keyNew);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()) {
            List<Opera> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }

    public long createOrSave(OperaModel operaModel) throws ObjectNotFoundException {
        Opera opera;
        if (operaModel.getId() <= 0) {
            opera = new Opera();
        } else {
            Optional<Opera> o = operaDao.getById(operaModel.getId());
            if (!o.isPresent()) {
                throw new ObjectNotFoundException(ObjectTypes.OPERA, operaModel.getId());
            }
            opera = o.get();
        }
        opera.setTitle(operaModel.getTitle());
        opera.setDescription(operaModel.getDescription());
        if (operaModel.getLibrettoId() > 0) {
            Optional<Libretto> o = librettoDao.getById(operaModel.getId());
            if (o.isPresent()) {
                Libretto libr = o.get();
                opera.setLibretto(libr);
            }
        }
        if (operaModel.getPersonagesId() != null) {
            List<Personage> personages = new ArrayList<>();
            operaModel.getPersonagesId().stream().forEach(e -> {
                Optional<Personage> o = personageDao.getById(e);
                personages.add(o.get());
            });
            opera.setPersonages(personages);
        }
        if (operaModel.getAriesId()!= null) {
            List<Aria> aries = new ArrayList<>();
            operaModel.getAriesId().stream().forEach(e -> {
                Optional<Aria> o = ariaDao.getById(e);
                aries.add(o.get());
            });
            opera.setAries(aries);
        }
        operaDao.saveOrUpdate(opera);
        return opera.getId();
    }
}
