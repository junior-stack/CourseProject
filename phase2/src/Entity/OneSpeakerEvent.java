package Entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * This class represents an OneSpeakerEvent.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class OneSpeakerEvent extends Event {

  /**
   * This method creates an Instance of OneSpeakerEvent. Every OneSpeakerEvent has a unique eventId,
   * one speaker, a related roomId, a topic, its startTime and endTime and a List of attendees.
   *
   * @param speakerId the speaker of this event
   * @param roomId  the roomID of the room where the event takes place
   * @param startTime the start time of the event
   * @param endTime the end time of the event
   * @param topic the topic of the event
   */
  public OneSpeakerEvent(int roomId, Time startTime, Time endTime, String topic, int max,
      ArrayList<Integer> speakerId) {
    super(roomId, startTime, endTime, topic, max);
    this.speakerId = speakerId;
    this.eventtype = "OneSpeakerEvent";
  }


}
