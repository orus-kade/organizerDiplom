/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.HumanDao;
import ru.sfedu.organizer.dao.OperaDao;
import ru.sfedu.organizer.dao.PersonageDao;
import ru.sfedu.organizer.dao.RoleDao;
import ru.sfedu.organizer.dao.StageDao;
import ru.sfedu.organizer.entity.Human;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Opera;
import ru.sfedu.organizer.entity.Personage;
import ru.sfedu.organizer.entity.Professions;
import ru.sfedu.organizer.entity.Role;
import ru.sfedu.organizer.entity.Stage;
import ru.sfedu.organizer.model.SearchResult;
import ru.sfedu.organizer.model.StageModel;

/**
 *
 * @author sterie
 */
public class StageBusiness {

    private final StageDao stageDao = new StageDao();

    private final OperaDao operaDao = new OperaDao();

    private final HumanDao humanDao = new HumanDao();

    private final RoleDao roleDao = new RoleDao();

    private final PersonageDao personageDao = new PersonageDao();

    @EJB
    private final HumanBusiness humanBusiness = new HumanBusiness();

    /**
     *
     */
    public StageBusiness() {
    }

    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    public StageModel getById(long id) throws ObjectNotFoundException{
        Optional<Stage> o = stageDao.getById(id);
        StageModel stageModel = null;
        if (o.isPresent()) {
            Stage stage = o.get();
            stageModel = new StageModel(stage.getId(), stage.getTitle(), stage.getDescription());
            stageModel.addDirector(stage.getDirector());
            stageModel.addEvents(stage.getSingleEvents());
            stageModel.addOpera(stage.getOpera());
            stageModel.addRoles(stage.getRoles());
            stageModel.setLinks(stageDao.getMediaLinks(id));
        }
        else throw new ObjectNotFoundException(ObjectTypes.STAGE, id);
        return stageModel;
    }

    /**
     *
     * @param id
     * @throws ObjectNotFoundException
     */
    public void delete(long id) throws ObjectNotFoundException {
        Optional<Stage> o = stageDao.getById(id);
        if (o.isPresent()) {
            stageDao.delete(o.get());
        }
        else throw new ObjectNotFoundException(ObjectTypes.STAGE, id);
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
            o = stageDao.getAll();
        } else {
            o = stageDao.getByRange(from, to);
        }
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()) {
            List<Stage> list = o.get();
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
        return stageDao.count();
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
        Optional<List> o = stageDao.search(keyNew);
        List<SearchResult> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()) {
            List<Stage> list = o.get();
            list.stream().forEach(e -> result.add(new SearchResult(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getTitle())));
        }
        return result;
    }

    /**
     *
     * @param stageModel
     * @return
     * @throws ObjectNotFoundException
     */
    public long createOrSave(StageModel stageModel) throws ObjectNotFoundException {
        Stage stage;
        if (stageModel.getId() <= 0) {
            stage = new Stage();
        } else {
            Optional<Stage> o = stageDao.getById(stageModel.getId());
            if (!o.isPresent()) {
                throw new ObjectNotFoundException(ObjectTypes.STAGE, stageModel.getId());
            }
            stage = o.get();
        }
        stage.setDescription(stageModel.getDescription());
        stage.setTitle(stageModel.getTitle());
        Optional<Opera> opera = operaDao.getById(stageModel.getOperaId());
        if (!opera.isPresent()) {
            throw new ObjectNotFoundException(ObjectTypes.OPERA, stageModel.getOperaId());
        }
        stage.setOpera(opera.get());
        if (stageModel.getDirectorId() > 0) {
            Optional<Human> o = humanDao.getById(stageModel.getDirectorId());
            if (o.isPresent()) {
                Human h = o.get();
                if (humanBusiness.isHumanProfession(h, Professions.DIRECTOR)) {
                    stage.setDirector(h);
                }
            }
        }
        if (stageModel.getRoles() != null && !stageModel.getRoles().isEmpty()) {
            List<Role> roles = new ArrayList<>();
            stageModel.getRoles().stream().forEach(e -> {
                Optional<Role> or = roleDao.getByPesonageAndSinger(e.getPersonageId(), e.getSingerId());
                if (or.isPresent()) {
                    Role role = or.get();
                    roles.add(role);
                } else {
                    Optional<Human> oh = humanDao.getById(e.getSingerId());
                    Optional<Personage> op = personageDao.getById(e.getPersonageId());
                    if (oh.isPresent() && op.isPresent()) {
                        Personage p = op.get();
                        Human h = oh.get();
                        if (humanBusiness.isHumanProfession(h, Professions.SINGER) && p.getOpera().equals(opera.get())) {
                            Role role = new Role();
                            role.setPersonage(p);
                            role.setSinger(h);
                            roles.add(role);
                        }
                    }
                }
            });
            stage.setRoles(roles);
        }
        stageDao.saveOrUpdate(stage);
        this.updateLinks(stageModel.getLinks(), stage.getId());
        return stage.getId();
    }

    private void updateLinks(List<MediaLink> list, long id) {
        List<MediaLink> oldList = new ArrayList<>();
        oldList.addAll(stageDao.getMediaLinks(id));
        if (list != null && !list.isEmpty()) {
            list.stream().forEach(e -> {
                e.setObjectId(id);
                e.setObjectType(ObjectTypes.STAGE);
            });
            List<Long> newIds = new ArrayList<>();
            newIds.addAll(list.stream().collect(ArrayList<Long>::new,
                    (a, r) -> a.add(r.getId()),
                    (a1, a2) -> a1.addAll(a2))
            );
            oldList.removeIf(e -> newIds.contains(e.getId()));
            stageDao.saveLinks(list);
        }        
        stageDao.deleteLinks(oldList);        
    }
}
