/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.AriaDao;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.dao.OperaDao;
import ru.sfedu.organizer.dao.PersonageDao;
import ru.sfedu.organizer.entity.Aria;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.model.AriaModel;
import ru.sfedu.organizer.model.SearchResult;

/**
 *
 * @author sterie
 */
@Stateless
public class AriaBusiness {

    private final AriaDao ariaDao = new AriaDao();

    private final HumanDao humanDao = new HumanDao();

    @EJB
    private final HumanBusiness humanBusiness = new HumanBusiness();

    private final OperaDao operaDao = new OperaDao();

    private final PersonageDao personageDao = new PersonageDao();

    /**
     *
     */
    public AriaBusiness() {
    }

    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    public AriaModel getById(long id) throws ObjectNotFoundException {
        Optional<Aria> o = ariaDao.getById(id);
        AriaModel ariaModel = null;
        if (o.isPresent()) {
            Aria aria = o.get();
            ariaModel = new AriaModel(aria.getId(), aria.getTitle(), aria.getText(), aria.getPosition());
            ariaModel.addOpera(aria.getOpera());
            ariaModel.addComposers(aria.getComposers());
            ariaModel.addWriters(aria.getWriters());
            ariaModel.addPersonages(aria.getPersonages());
            ariaModel.setLinks(ariaDao.getMediaLinks(id));
        } else {
            throw new ObjectNotFoundException(ObjectTypes.ARIA, id);
        }
        return ariaModel;
    }

    /**
     *
     * @param id
     * @throws ObjectNotFoundException
     */
    public void delete(long id) throws ObjectNotFoundException {
        Optional<Aria> o = ariaDao.getById(id);
        if (o.isPresent()) {
            ariaDao.delete(o.get());
        } else {
            throw new ObjectNotFoundException(ObjectTypes.ARIA, id);
        }
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public List<SearchResult> getByRange(int from, int to) {
        Optional<List> o;
        if (from == 0 && to == 0) {
            o = ariaDao.getAll();
        } else {
            o = ariaDao.getByRange(from, to);
        }
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()) {
            List<Aria> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }

    /**
     *
     * @return
     */
    public List<SearchResult> getAll() {
        return this.getByRange(0, 0);
    }

    /**
     *
     * @return
     */
    public int count() {
        return ariaDao.count();
    }

    /**
     *
     * @param key
     * @return
     */
    public List<SearchResult> search(String key) {
        if (key == null || key.trim().length() == 0) {
            return this.getAll();
        }
        String keyNew = key.trim().toLowerCase();
        Optional<List> o = ariaDao.search(keyNew);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()) {
            List<Aria> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }

    /**
     *
     * @param ariaModel
     * @return
     * @throws ObjectNotFoundException
     */
    public long createOrSave(AriaModel ariaModel) throws ObjectNotFoundException {
        Aria aria;
        if (ariaModel.getId() <= 0) {
            aria = new Aria();
        } else {
            Optional<Aria> o = ariaDao.getById(ariaModel.getId());
            if (!o.isPresent()) {
                throw new ObjectNotFoundException(ObjectTypes.ARIA, ariaModel.getId());
            }
            aria = o.get();
        }
        aria.setPosition(ariaModel.getPosition());
        aria.setTitle(ariaModel.getTitle());
        aria.setText(ariaModel.getText());
        Optional<Opera> opera = operaDao.getById(ariaModel.getOperaId());
        if (!opera.isPresent()) {
            throw new ObjectNotFoundException(ObjectTypes.OPERA, ariaModel.getOperaId());
        }
        aria.setOpera(opera.get());
        if (ariaModel.getWritersId() != null) {
            List<Human> writers = new ArrayList<>();
            ariaModel.getWritersId().stream().forEach(e -> {
                Optional<Human> o = humanDao.getById(e);
                if (o.isPresent()) {
                    writers.add(o.get());
                }
            });
            writers.removeIf(e -> !humanBusiness.isHumanProfession(e, Professions.WRITER));
            aria.setWriters(writers);
        }
        if (ariaModel.getComposersId() != null) {
            List<Human> composers = new ArrayList<>();
            ariaModel.getComposersId().stream().forEach(e -> {
                Optional<Human> o = humanDao.getById(e);
                if (o.isPresent()) {
                    composers.add(o.get());
                }
            });
            composers.removeIf(e -> !humanBusiness.isHumanProfession(e, Professions.COMPOSER));
            aria.setComposers(composers);
        }
        if (ariaModel.getPersonagesId() != null) {
            List<Personage> personages = new ArrayList<>();
            ariaModel.getPersonagesId().stream().forEach(e -> {
                Optional<Personage> o = personageDao.getById(e);
                if (o.isPresent()) {
                    if (aria.getOpera().equals(o.get().getOpera())) {
                        personages.add(o.get());
                    }
                }
            });
            aria.setPersonages(personages);
        }
        ariaDao.saveOrUpdate(aria);
        this.updateLinks(ariaModel.getLinks(), aria.getId());
        return aria.getId();
    }

    private void updateLinks(List<MediaLink> list, long id) {
        List<MediaLink> oldList = new ArrayList<>();
        oldList.addAll(ariaDao.getMediaLinks(id));
        if (list != null && !list.isEmpty()) {
            list.stream().forEach(e -> {
                e.setObjectId(id);
                e.setObjectType(ObjectTypes.ARIA);
            });
            List<Long> newIds = new ArrayList<>();
            newIds.addAll(list.stream().collect(ArrayList<Long>::new,
                    (a, r) -> a.add(r.getId()),
                    (a1, a2) -> a1.addAll(a2))
            );
            oldList.removeIf(e -> newIds.contains(e.getId()));
            ariaDao.saveLinks(list);
        }        
        ariaDao.deleteLinks(oldList);        
    }
}
