package UseCase;

import Entity.Event;
import Entity.Room;
import Entity.Speaker;
import exception.DoubleBooking;
import exception.InvertedTime;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {

  public static final ArrayList<Event> eventpool = new ArrayList<>();
  private final ValidateRoom vr;
  private final ValidateSpeaker vs;

  public EventManager(ValidateRoom vr, ValidateSpeaker vs) {
    this.vr = vr;
    this.vs = vs;
  }

  public ValidateRoom get_vr() {
    return vr;
  }

  public ValidateSpeaker get_vs() {
    return vs;
  }

  public boolean checkIsEventValid(Room rm, Time start, Time end, Speaker sp) {
    Time beggining = java.sql.Time.valueOf("09:00:00");
    Time ending = java.sql.Time.valueOf("17:00:00");

    if (start.compareTo(beggining) < 0 && end.compareTo(ending) > 0) {
      return false;
    }

    try {
      if (vs.validateSpeaker(sp, start, end) && vr.validateRoom(rm, start, end)) {
        return true;
      }
    } catch (DoubleBooking doubleBooking) {
      System.out.println(doubleBooking.getMessage());
      return false;
    } catch (InvertedTime e) {
      System.out.println(e.getMessage());
      return false;
    }
    return false;
  }

  public void addEvent(Room rm, Time start, Time end, Speaker sp, String topic) {

    Event event = new Event(rm.getRoomId(), start, end, topic);

    eventpool.add(event);

    vr.give_room_schedule(rm, start, end);

    vs.giveSpeakerNewSchedule(sp, start, end);
  }

  public boolean delEvent(Event event) {

    int id = event.getId();

    for (Event e : eventpool) {
      if (e.getId() == id) {
        eventpool.remove(e);
        vr.del_room_schedule(event.getRoomId(), event.getStartTime(), event.getEndTime());
        vs.delSpeakerSchedule(event.getSpeaker(), event.getStartTime(), event.getEndTime());
        return true;
      }
    }
    return false;
  }

  public boolean editEvent(Event old, Room new_rm, Time start, Time end, String topic,
      Speaker new_sp) {

    int id = old.getId();
    Room old_rm;
    Speaker old_sp;

    for (Event e : eventpool) {
      if (e.getId() == id) {
        this.delEvent(old);
        if (this.checkIsEventValid(new_rm, start, end, new_sp)) {
          this.addEvent(new_rm, start, end, new_sp, topic);
          return true;
        } else {
          old_rm = vr.get_rm(old.getRoomId());
          old_sp = vs.get_sp(old.getSpeaker());
          this.addEvent(old_rm, start, end, old_sp, topic);
          return false;
        }
      }
    }
    return false;
  }

  public Event get_event(int event_ID) {
    HashMap<Integer, Event> tmp = this.get_events();
    Event event1;
    try {
      event1 = tmp.get(event_ID);
      return event1;
    } catch (NullPointerException e) {
      System.out.println("There is no event with such ID");
      e.printStackTrace();
    }
    return null;
  }

  public HashMap<Integer, Event> get_events() {
    HashMap<Integer, Event> events = new HashMap<>();
    for (Event e : eventpool) {
      events.put(e.getId(), e);
    }
    return events;
  }

}

