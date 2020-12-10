package UseCase;

import Dao.EventDao;
import Entity.Event;
import Entity.MultiSpeakerEvent;
import Entity.NoSpeakerEvent;
import Entity.OneSpeakerEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class representing a EventManager.
 *
 * @author Hanzhi Zhang & Ye Zhou
 * @version 1.0
 */
public class EventManager implements Iterable<Event> {

  public static ArrayList<Event> eventpool;

  /**
   * Create a EventManager with a RoomManager, a SpeakerScheduleManager and an ArrayList of Events.
   *
   * @param eventpool list of events
   */
  public EventManager(ArrayList<Event> eventpool) {
    EventManager.eventpool = eventpool;
  }


  /**
   * Add a Event with given parameters to the system.
   *
   * @param rm_id this represents the roomID of the room where event holds
   * @param start the start time of the event
   * @param end the end time of the event
   * @param sp the speakerIDs of the speakers of the event
   * @param topic the topic of the event
   */
  public void addEvent(int rm_id, Time start, Time end, String topic, int max, String eventtype,
      ArrayList<Integer> sp) {

    Event event;
    if (eventtype.equals("MultipleSpeakerEvent")) {
      event = new MultiSpeakerEvent(rm_id, start, end, topic, max, sp);
    } else if (eventtype.equals("OneSpeakerEvent")) {
      event = new OneSpeakerEvent(rm_id, start, end, topic, max, sp);
    } else {
      event = new NoSpeakerEvent(rm_id, start, end, topic, max);
    }
    eventpool.add(event);
  }

  /**
   * Delete the given Event from the system. Print "There is no event with such id to be deleted" if
   * the event does not exist in the system.
   *
   * @param id the eventID of the event
   * @return boolean of whether the event is successfully deleted
   */
  public boolean delEvent(int id) {
    ArrayList<Event> eventpool_copy = new ArrayList<>();
    eventpool_copy.addAll(eventpool);
    for (Event e : eventpool_copy) {
      if (e.getId() == id) {
        eventpool.remove(e);
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
   * @param id the id of the target event going to be edited
   * @param new_rm the id of the new room assigned to the event
   * @param start the new start time of the event
   * @param end the new end time of the event
   * @param topic the topic of the event
   * @param new_sp the new speakers assigned to this event
   * @return boolean of whether the Event is successfully edited
   */
  public boolean editEvent(int id, int new_rm, Time start, Time end, String topic,
      ArrayList<Integer> new_sp) {
    Event old = get_event(id);
    if (old == null) {
      return false;
    }
    old.setStartTime(start);
    old.setEndTime(end);
    old.setRoomId(new_rm);
    old.setTopic(topic);
    old.setSpeakerId(new_sp);
    return true;
  }

  /**
   * Return the Event with given eventId in the system. Print "There is no event with such ID" if no
   * such Event is in the system.
   *
   * @param event_ID the id of the event
   * @return Event with given eventId or null if no such Event
   */
  public Event get_event(int event_ID) {
    for (Event curr : this) {
      if (curr.getId() == event_ID) {
        return curr;
      }
    }
    return null;
  }


  /**
   * Return a HashMap of all Events' eventId to the related Event's string representation.
   *
   * @return HashMap of all Events' eventId to the related Event's string representation
   */
  public List<String> get_events_info() {
    List<String> events_info = new ArrayList<>();
    for (Event e : this) {
      events_info.add(e.toString());
    }
    return events_info;
  }

  /**
   * Return an ArrayList of all the string representation of Events in the system with given topic.
   *
   * @param topic the topic of the event users may want to browse
   * @return ArrayList of all the string representation of Events in the system with given topic
   */
  public ArrayList<String> browse(String topic) {
    ArrayList<String> result = new ArrayList<>();
    for (Event e : this) {
      if (e.getTopic().equals(topic)) {
        result.add(e.toString());
      }
    }
    return result;
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
   * @param newcounter a counter for creating the event IDs
   */
  public void setNewCounter(int newcounter) {
    Event.setCounter(newcounter);
  }

  /**
   * This method can change the capacity of a single event
   * @param event_id target eventId
   * @param new_maximum new maximum user may want to assign to this event
   * @return true iff the event capacity is changes
   */
  public boolean setEventCapacity(int event_id, int new_maximum) {
    Event e = get_event(event_id);
    if (new_maximum < e.getCountAttendeeEnrolled()) {
      return false;
    }
    e.setMaximum_attentees(new_maximum);
    return true;
  }

  /**
   * This method can get the roomID of the event by giving eventID
   * @param eventID the ID of target event
   * @return the roomID
   */
  public Integer getLocation(int eventID) {
    Event e = this.get_event(eventID);
    if (e != null) {
      return e.getRoomId();
    }
    return null;
  }
  /**
   * This method gives time of the event by passing eventID
   * @param eventID the target event id
   * @return the arraylist comprising the start time and end time
   */
  public ArrayList<Time> gettime(int eventID) {
    ArrayList<Time> tmp = new ArrayList<>();
    Event e = get_event(eventID);
    if (e != null) {
      tmp.add(e.getStartTime());
      tmp.add(e.getEndTime());
    }
    return tmp;
  }
  /**
   * This method gives all the speakers ID of the event by passing eventID
   * @param eventID the target event id
   * @return A list of speakers ID
   */
  public ArrayList<Integer> getSpeaker(int eventID) {
    Event e = get_event(eventID);
    if (e != null) {
      return e.getSpeaker();
    }
    return null;
  }

  @Override
  public Iterator<Event> iterator() {
    return new EventManagerIterator();
  }

  public void saveEvents() {
    EventDao.saveAll(eventpool);
  }

  private class EventManagerIterator implements Iterator<Event> {

    private int current = 0;

    @Override
    public boolean hasNext() {
      return current < eventpool.size();
    }

    @Override
    public Event next() {
      Event res;
      try {
        res = eventpool.get(current);
      } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
      current += 1;
      return res;

    }
  }
}

