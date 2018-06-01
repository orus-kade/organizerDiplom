/*
 */
package ru.sfedu.organizer.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import ru.sfedu.organizer.business.exceptions.ObjectNotFoundException;
import ru.sfedu.organizer.dao.EventDao;
import ru.sfedu.organizer.dao.PlaceDao;
import ru.sfedu.organizer.dao.SingleEventDao;
import ru.sfedu.organizer.entity.Event;
import ru.sfedu.organizer.entity.MediaLink;
import ru.sfedu.organizer.entity.ObjectTypes;
import ru.sfedu.organizer.entity.Place;
import ru.sfedu.organizer.entity.SingleEvent;
import ru.sfedu.organizer.model.SearchResult;
import ru.sfedu.organizer.model.SingleEventInfo;
import ru.sfedu.organizer.model.SingleEventModel;

/**
 *
 * @author sterie
 */
public class SingleEventBusiness {

    private static final SingleEventDao dao = new SingleEventDao();

    private static final PlaceDao placeDao = new PlaceDao();

    private static final EventDao eventDao = new EventDao();

    /**
     *
     */
    public SingleEventBusiness() {
    }

    /**
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    public SingleEventModel getById(long id) throws ObjectNotFoundException {
        Optional<SingleEvent> o = dao.getById(id);
        SingleEventModel singleEventModel = null;
        if (o.isPresent()) {
            SingleEvent singleEvent = o.get();
            singleEventModel = new SingleEventModel(
                    singleEvent.getId(),
                    singleEvent.getEvent().getTitle(),
                    singleEvent.getEvent().getClass().getSimpleName().toLowerCase(),
                    singleEvent.getEvent().getId(),
                    singleEvent.getDescription(),
                    new Date(singleEvent.getDatetime()),
                    singleEvent.getPlace().getId(),
                    singleEvent.getPlace().getTitle(),
                    singleEvent.getPlace().getLocation()
            );
            singleEventModel.setLinks(dao.getMediaLinks(id));
        }
        else throw new ObjectNotFoundException(ObjectTypes.SINGLE_EVENT, id);
        return singleEventModel;
    }

    /**
     *
     * @param id
     * @throws ObjectNotFoundException
     */
    public void delete(long id) throws ObjectNotFoundException {
        Optional<SingleEvent> o = dao.getById(id);
        if (o.isPresent()) {
            dao.delete(o.get());
        }
        else throw new ObjectNotFoundException(ObjectTypes.SINGLE_EVENT, id);
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public List<SingleEventInfo> getByRange(int from, int to) {
        Optional<List> o;
        if (from == 0 && to == 0) {
            o = dao.getAll();
        } else {
            o = dao.getByRange(from, to);
        }
        List<SingleEventInfo> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()) {
            List<SingleEvent> list = o.get();
            list.stream().forEach(e -> result.add(new SingleEventInfo(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
        }
        return result;
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public List<SingleEventInfo> getByRangeFuture(int from, int to) {
        Optional<List> o;
        if (from == 0 && to == 0) {
            o = dao.getAll();
        } else {
            o = dao.getByRange(from, to);
        }
        List<SingleEventInfo> result = new ArrayList<>();
        if (o.isPresent() && !o.get().isEmpty()) {
            List<SingleEvent> list = o.get();
            list.stream().forEach(e -> result.add(new SingleEventInfo(e.getClass().getSimpleName().toLowerCase(), e.getId(), e.getEvent().getTitle(), new Date(e.getDatetime()))));
        }
        return result;
    }

    /**
     *
     * @return
     */
    public List<SingleEventInfo> getAll() {
        return this.getByRange(0, 0);
    }

    /**
     *
     * @return
     */
    public List<SingleEventInfo> getAllFuture() {
        return this.getByRange(0, 0);
    }

    /**
     *
     * @return
     */
    public int count() {
        return dao.count();
    }

    /**
     *
     * @return
     */
    public int countFuture() {
        return dao.countFuture();
    }

    /**
     *
     * @param singleEventModel
     * @return
     * @throws ObjectNotFoundException
     */
    public long createOrSave(SingleEventModel singleEventModel) throws ObjectNotFoundException {
        SingleEvent sEvent;
        if (singleEventModel.getId() <= 0) {
            sEvent = new SingleEvent();
        } else {
            Optional<SingleEvent> o = dao.getById(singleEventModel.getId());
            if (!o.isPresent()) {
                throw new ObjectNotFoundException(ObjectTypes.SINGLE_EVENT, singleEventModel.getId());
            }
            sEvent = o.get();
        }
        sEvent.setDescription(singleEventModel.getDescription());
        sEvent.setDatetime(singleEventModel.getDate().getTime());
        Optional<Place> op = placeDao.getById(singleEventModel.getPlaceId());
        if (!op.isPresent()) {
            throw new ObjectNotFoundException(ObjectTypes.PLACE, singleEventModel.getPlaceId());
        }
        Place place = op.get();
        sEvent.setPlace(place);
        Optional<Event> oe = eventDao.getById(singleEventModel.getEventId());
        if (!oe.isPresent()) {
            throw new ObjectNotFoundException(ObjectTypes.EVENT, singleEventModel.getEventId());
        }
        Event event = oe.get();
        sEvent.setEvent(event);
        dao.saveOrUpdate(sEvent);
        this.updateLinks(singleEventModel.getLinks(), sEvent.getId());
        return sEvent.getId();
    }

    private void updateLinks(List<MediaLink> list, long id) {
        List<MediaLink> oldList = new ArrayList<>();
        oldList.addAll(dao.getMediaLinks(id));
        if (list != null && !list.isEmpty()) {
            list.stream().forEach(e -> {
                e.setObjectId(id);
                e.setObjectType(ObjectTypes.SINGLE_EVENT);
            });
            List<Long> newIds = new ArrayList<>();
            newIds.addAll(list.stream().collect(ArrayList<Long>::new,
                    (a, r) -> a.add(r.getId()),
                    (a1, a2) -> a1.addAll(a2))
            );
            oldList.removeIf(e -> newIds.contains(e.getId()));
            dao.saveLinks(list);
        }        
        dao.deleteLinks(oldList);        
    }
}
