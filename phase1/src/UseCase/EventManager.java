package UseCase;

import Entity.Event;
import Entity.Room;
import Entity.Speaker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a EventManager.
 *
 * @author Hanzhi Zhang & Ye Zhou
 * @version 1.0
 */
public class EventManager {

  public static ArrayList<Event> eventpool;

  private RoomManager vr;
  private SpeakerScheduleManager vs;

  /**
   * Create a EventManager with a RoomManager, a SpeakerScheduleManager and an ArrayList of Events.
   *
   * @param vr
   * @param vs
   * @param eventpool
   */
  public EventManager(RoomManager vr, SpeakerScheduleManager vs, ArrayList<Event> eventpool) {
    this.vr = vr;
    this.vs = vs;
    EventManager.eventpool = eventpool;
  }

  /**
   * Return the RoomManager of this EventManager.
   *
   * @return RoomManager
   */
  public RoomManager get_vr() {
    return vr;
  }

  /**
   * Return the SpeakerScheduleManager of this EventManager.
   *
   * @return
   */
  public SpeakerScheduleManager get_vs() {
    return vs;
  }

  /**
   * Check if: The start is not earlier than 9 am and end if not later than 5 pm and the room and
   * speaker are both available during the time period from start to end.
   *
   * @param rm
   * @param start
   * @param end
   * @param sp
   * @return boolean of the result
   */
  public boolean checkIsEventValid(Room rm, Time start, Time end, Speaker sp) {
    Time beggining = java.sql.Time.valueOf("09:00:00");
    Time ending = java.sql.Time.valueOf("17:00:00");

    if (start.compareTo(beggining) < 0 | end.compareTo(ending) > 0) {
      return false;
    }

      if (vs.validateSpeaker(sp, start, end) && vr.validateRoom(rm, start, end)) {
        return true;
      }

    return false;
  }

  /**
   * Add a Event with given parameters to the system.
   *
   * @param rm
   * @param start
   * @param end
   * @param sp
   * @param topic
   */
  public void addEvent(Room rm, Time start, Time end, Speaker sp, String topic) {

    Event event = new Event(rm.getRoomName(), start, end, topic);

    eventpool.add(event);

    vr.give_room_schedule(rm, start, end);

    vs.giveSpeakerNewSchedule(sp, start, end);
  }

  /**
   * Add a constructed Event to the system.
   *
   * @param e
   */
  public void addEvent(Event e) {
    eventpool.add(e);

    vr.give_room_schedule(vr.get_rm(e.getRoomId()), e.getStartTime(), e.getEndTime());

    vs.giveSpeakerNewSchedule(vs.get_sp(e.getSpeaker()), e.getStartTime(), e.getEndTime());
  }

  /**
   * Delete the given Event from the system. Print "There is no event with such id to be deleted" if
   * the event does not exist in the system.
   *
   * @param event
   * @return boolean of whether the event is successfully deleted
   */
  public boolean delEvent(Event event) {
    int id;
    try {
      id = event.getId();
    } catch (NullPointerException e) {
      System.out.println("There is no event with such id to be deleted");
      return false;
    }
    ArrayList<Event> eventpool_copy = new ArrayList<>();
    eventpool_copy.addAll(eventpool);
    for (Event e : eventpool_copy) {
      if (e.getId() == id) {
        eventpool.remove(e);
        vr.del_room_schedule(event.getRoomId(), event.getStartTime(), event.getEndTime());
        vs.delSpeakerSchedule(event.getSpeaker(), event.getStartTime(), event.getEndTime());
        return true;
      }
    }
    return false;
  }

  /**
   * Edit the old Event and assign new room, start_time, end_time, topic, and speaker to it if the
   * new Event is still valid, otherwise keep the old event unchanged. If there is no such event,
   * print "There is no event with such id to edit".
   *
   * @param old
   * @param new_rm
   * @param start
   * @param end
   * @param topic
   * @param new_sp
   * @return boolean of whether the Event is successfully edited
   */
  public boolean editEvent(Event old, Room new_rm, Time start, Time end, String topic,
      Speaker new_sp) {
    int id;
    try {
      id = old.getId();
    } catch (NullPointerException e) {
      System.out.println("There is no event with such id to edit");
      return false;
    }

    long time_difference = end.getTime() - start.getTime();
    if (time_difference > 3600000) {
      return false;
    }

    ArrayList<Event> tmp = new ArrayList<>();
    tmp.addAll(eventpool);
    for (Event e : tmp) {
      if (e.getId() == id) {
        this.delEvent(old);
        if (this.checkIsEventValid(new_rm, start, end, new_sp)) {
          old.setStartTime(start);
          old.setEndTime(end);
          old.setRoomId(new_rm.getRoomName());
          old.setTopic(topic);
          old.SetSpeaker(new_sp.getUserId());
          this.addEvent(old);
          return true;
        } else {
          this.addEvent(old);
          return false;
        }
      }
    }
    return false;
  }

  /**
   * Return the Event with given eventId in the system. Print "There is no event with such ID" if no
   * such Event is in the system.
   *
   * @param event_ID
   * @return Event with given eventId or null if no such Event
   */
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

  /**
   * Return a HashMap of all Events' eventId to the related Event.
   *
   * @return HashMap of all Events' eventId to the related Event
   */
  public HashMap<Integer, Event> get_events() {
    HashMap<Integer, Event> events = new HashMap<>();
    for (Event e : eventpool) {
      events.put(e.getId(), e);
    }
    return events;
  }

  /**
   * Return a HashMap of all Events' eventId to the related Event's string representation.
   *
   * @return HashMap of all Events' eventId to the related Event's string representation
   */
  public HashMap<Integer, String> get_events_info() {
    HashMap<Integer, String> events_info = new HashMap<>();
    for (Event e : eventpool) {
      events_info.put(e.getId(), e.toString());
    }
    return events_info;
  }

  /**
   * Return an ArrayList of all the string representation of Events in the system with given topic.
   *
   * @param topic
   * @return ArrayList of all the string representation of Events in the system with given topic
   */
  public ArrayList<String> browse(String topic) {
    ArrayList<String> result = new ArrayList<>();
    for (Event e : eventpool) {
      if (e.getTopic().equals(topic)) {
        result.add(e.toString());
      }
    }
    return result;
  }

  /**
   * Return an Arraylist all the string representation of eventId-Topic. A string representation of
   * eventId-Topic is in the form of "eventId-eventTopic".
   *
   * @return Arraylist all the string representation of eventId-Topic
   */
  public ArrayList<String> get_events_lst() {
    ArrayList<String> tmp = new ArrayList<>();
    for (Event e : eventpool) {
      tmp.add(e.getId() + "-" + e.getTopic());
    }
    return tmp;
  }

  /**
   * Return an Arraylist of eventId of the given Event.
   *
   * @param e
   * @return Arraylist of eventId
   */
  public ArrayList<Integer> get_event_spots(Event e) {
    ArrayList<Integer> spots_list = new ArrayList<>();
    spots_list.add(e.getRoomId());
    return spots_list;
  }

  /**
   * Get this EventManager's eventpool.
   *
   * @return eventpool
   */
  public ArrayList<Event> get_eventpool() {
    return eventpool;
  }

  /**
   * This method sets up a new counter.
   *
   * @param newcounter
   */
  public void setNewCounter(int newcounter) {
    Event.setCounter(newcounter);
  }
}

