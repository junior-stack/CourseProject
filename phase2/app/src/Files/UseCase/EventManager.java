package UseCase;

import Entity.Event;
import Entity.MultiSpeakerEvent;
import Entity.NoSpeakerEvent;
import Entity.OneSpeakerEvent;
import Entity.Room;
import Entity.Schedulable;
import Entity.Speaker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class representing a EventManager.
 *
 * @author Hanzhi Zhang & Ye Zhou
 * @version 1.0
 */
public class EventManager implements Iterable<Event>{

  public static ArrayList<Event> eventpool;

  private SchedulableManager rooms_list;
  private SchedulableManager speakers_list;

  /**
   * Create a EventManager with a RoomManager, a SpeakerScheduleManager and an ArrayList of Events.
   *
   * @param rooms_list
   * @param speakers_list
   * @param eventpool
   */
  public EventManager(SchedulableManager rooms_list, SchedulableManager speakers_list, ArrayList<Event> eventpool) {
    this.rooms_list = rooms_list;
    this.speakers_list = speakers_list;
    EventManager.eventpool = eventpool;
  }

  /**
   * Return the RoomManager of this EventManager.
   *
   * @return RoomManager
   */
  public SchedulableManager get_vr() {
    return rooms_list;
  }

  /**
   * Return the SpeakerScheduleManager of this EventManager.
   *
   * @return
   */
  public SchedulableManager get_vs() {
    return speakers_list;
  }

  /**
   * Check if: The start is not earlier than 9 am and end if not later than 5 pm and the room and
   * speaker are both available during the time period from start to end.
   *
   * @param rm_id
   * @param start
   * @param end
   * @param sp_id
   * @return boolean of the result
   */
  public boolean checkIsEventValid(int rm_id, Time start, Time end, ArrayList<Integer> sp_id) {
    Time beggining = java.sql.Time.valueOf("09:00:00");
    Time ending = java.sql.Time.valueOf("17:00:00");

    if (start.compareTo(beggining) < 0 | end.compareTo(ending) > 0) {
      return false;
    }


    if (!rooms_list.CheckSchedulableAvailable(rm_id, start, end)) {
      return false;
    }
    for(Integer sp: sp_id){
      if(!speakers_list.CheckSchedulableAvailable(sp, start, end)){
        return false;
      }
    }
    return true;
  }

  /**
   * Add a Event with given parameters to the system.
   *
   * @param rm_id
   * @param start
   * @param end
   * @param sp
   * @param topic
   */
  public void addEvent(int rm_id, Time start, Time end, String topic, int max, String eventtype, ArrayList<Integer> sp) {

    Event event;
    if(eventtype.equals("MultipleSpeakerEvent")){
      event = new MultiSpeakerEvent(rm_id, start, end, topic, max, sp);
    }
    else if(eventtype.equals("OneSpeakerEvent")){
      event = new OneSpeakerEvent(rm_id, start, end, topic, max, sp);
    }else{
      event = new NoSpeakerEvent(rm_id, start, end, topic, max);
    }

    eventpool.add(event);

    rooms_list.giveSchedulableNewSchedule(rm_id, start, end);

    for(Integer id: sp){
      speakers_list.giveSchedulableNewSchedule(id, start, end);
    }
  }

  /**
   * Add a constructed Event to the system.
   *
   * @param event
   */
  public void addEvent(Event event) {
    eventpool.add(event);

    rooms_list.giveSchedulableNewSchedule(event.getRoomId(), event.getStartTime(), event.getEndTime());
    if(event instanceof OneSpeakerEvent) {
      OneSpeakerEvent e = (OneSpeakerEvent) event;
      for(Integer sp: e.getSpeaker()){
        speakers_list
            .giveSchedulableNewSchedule(sp, event.getStartTime(), event.getEndTime());
      }
    }
    else if (event instanceof MultiSpeakerEvent){
      MultiSpeakerEvent e = (MultiSpeakerEvent) event;
      for(Integer sp: e.getSpeaker()){
        speakers_list
            .giveSchedulableNewSchedule(sp, event.getStartTime(), event.getEndTime());
      }
    }
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
    }catch(NullPointerException e){
      System.out.println("There is no event with such id to be deleted");
      return false;
    }
    ArrayList<Event> eventpool_copy = new ArrayList<>();
    eventpool_copy.addAll(eventpool);
    for (Event e : eventpool_copy) {
      if (e.getId() == id) {
        eventpool.remove(e);
        rooms_list.delSchedulableSchedule(event.getRoomId(), event.getStartTime(), event.getEndTime());
        for(Integer sp_id: event.getSpeaker()) {
          speakers_list.delSchedulableSchedule(sp_id, event.getStartTime(), event.getEndTime());
        }
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
   * @param id
   * @param new_rm
   * @param start
   * @param end
   * @param topic
   * @param new_sp
   * @return boolean of whether the Event is successfully edited
   */
  public boolean editEvent(int id, int new_rm, Time start, Time end, String topic, ArrayList<Integer> new_sp) {
    Event old;
    try {
      old = get_event(id);
    } catch (NullPointerException e) {
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
          old.setRoomId(new_rm);
          old.setTopic(topic);
          old.setSpeakerId(new_sp);
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

  public boolean setEventCapacity(int room_id, int event_id, int new_maximum) {
    Room rm = (Room) rooms_list.get_sch(room_id);
    int rc = rm.getCapacity();
    Event e = get_event(event_id);
    if (new_maximum < e.getAllAttendee().size() | new_maximum > rc) {
      return false;
    }
    e.setMaximum_attentees(new_maximum);
    return true;
  }

  @Override
  public Iterator<Event> iterator() {
    return new EventManagerIterator();
  }


  private class EventManagerIterator implements Iterator<Event>{

    private int current = 0;
    @Override
    public boolean hasNext() {
      return current < eventpool.size();
    }

    @Override
    public Event next() {
      Event res;
      try{
        res = eventpool.get(current);
      }catch (IndexOutOfBoundsException e){
        throw new NoSuchElementException();
      }
      current += 1;
      return res;

    }

    @Override
    public void remove() {
      eventpool.remove(current - 1);
    }
  }
}

