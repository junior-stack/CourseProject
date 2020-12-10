package Entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an Event.
 *
 * @author Jun Xing
 * @version 1.0
 */
@DatabaseTable(tableName = "event")
public class Event {

  private static int counter = 0;
  @DatabaseField(columnName = "speaker_ids", dataType = DataType.SERIALIZABLE)
  protected ArrayList<Integer> speakerId = new ArrayList<>();

  @DatabaseField(columnName = "event_type")
  String eventtype = "";

  @DatabaseField(columnName = "event_id", id = true)
  int eventId = 0;
  @DatabaseField(columnName = "room_id")
  int roomId = 0;
  @DatabaseField(columnName = "start_time", dataType = DataType.SERIALIZABLE)
  Time startTime = null;
  @DatabaseField(columnName = "end_time", dataType = DataType.SERIALIZABLE)
  Time endTime = null;
  @DatabaseField(columnName = "topic")
  String topic = "";
  @DatabaseField(columnName = "maximum_attendees")
  int maximum_attentees = 0;
  @DatabaseField(columnName = "attendees", dataType = DataType.SERIALIZABLE)
  ArrayList<Integer> attendees = new ArrayList<>();

  /**
   * This method creates an Instance of Event. Every event has a unique eventId, a speaker, a
   * related roomId, a topic, its startTime and endTime and a List of attendees.
   *
   * @param roomId    roomID where the event takes place
   * @param startTime the start time of the event
   * @param endTime   the end time of the event
   * @param topic     the topic of this event
   */
  public Event(int roomId, Time startTime, Time endTime, String topic, int max) {
    this.eventId = counter;
    this.roomId = roomId;
    this.topic = topic;
    this.startTime = startTime;
    this.endTime = endTime;
    maximum_attentees = max;
    counter++;
  }

  public Event() {
  }

  /**
   * This method sets a User's counter.
   *
   * @param counter a counter for generating the event ID
   */
  public static void setCounter(int counter) {
    Event.counter = counter;
  }

  /**
   * This method return a Event's eventId.
   *
   * @return Event's eventId
   */
  public int getId() {
    return eventId;
  }

  /**
   * This method returns a Event's startTime.
   *
   * @return Event's startTime.
   */
  public Time getStartTime() {
    return startTime;
  }

  /**
   * This method sets a Event's startTime.
   *
   * @param startTime the new start time of the event user want to change to
   */
  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  /**
   * This method returns a Event's endTime.
   *
   * @return Event's endTime.
   */
  public Time getEndTime() {
    return endTime;
  }

  /**
   * This method sets a Event's endTime.
   *
   * @param endTime the new end time of the event user want to change to
   */
  public void setEndTime(Time endTime) {
    this.endTime = endTime;
  }

  /**
   * This method returns a Event's topic.
   *
   * @return Event's topic
   */
  public String getTopic() {
    return topic;
  }

  /**
   * This method sets a Event's topic.
   *
   * @param topic the topic of the event the user want to change to
   */
  public void setTopic(String topic) {
    this.topic = topic;
  }

  /**
   * This method returns a List of all attendees' attendeeId.
   *
   * @return List of all attendees' attendeeId
   */
  public List<Integer> getAllAttendee() {
    return attendees;
  }

  /**
   * This method add a new attendee to the Event by adding its attendeeId to the List of attendees.
   *
   * @param attendee_id the attendees of the event
   */
  public void addAttendee(int attendee_id) {
    this.attendees.add(attendee_id);
  }

  /**
   * This method returns a Event's roomId.
   *
   * @return Event's roomId
   */
  public int getRoomId() {
    return roomId;
  }

  /**
   * This method sets a Event's roomId.
   *
   * @param roomId the new place roomID the user may want to change to
   */
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  /**
   * This method return the speaker of this Event.
   *
   * @return Event's speaker
   */
  public ArrayList<Integer> getSpeaker() {
    return speakerId;
  }

  /**
   * This method sets a Event's speaker.
   *
   * @param s the list of speakers of the event user may want to change to
   */
  public void setSpeakerId(ArrayList<Integer> s) {
    this.speakerId = s;
  }


  /**
   * This method returns the capacity of this event
   *
   * @return int the capacity of this event
   */
  public int getMaximum_attentees() {
    return this.maximum_attentees;
  }

  /**
   * This method changes the capacity of this event
   *
   * @param maximum_attentees a new capacity the user may want to change to
   */

  public void setMaximum_attentees(int maximum_attentees) {
    this.maximum_attentees = maximum_attentees;
  }

  public int getCountAttendeeEnrolled() {
    return this.attendees.size();
  }


  /**
   * This method returns a string representation of the Event, including its eventId, speakerId,
   * roomId, startTime, endTime, topic and attendees' attendeeId.
   *
   * @return a string representation of the Event
   */
  @Override
  public String toString() {
    return "Event{" +
        "speakerId=" + speakerId +
        ", eventtype='" + eventtype + '\'' +
        ", eventId=" + eventId +
        ", roomId=" + roomId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", topic='" + topic + '\'' +
        ", maximum_attentees=" + maximum_attentees +
        ", attendees=" + attendees +
        '}';
  }
}

