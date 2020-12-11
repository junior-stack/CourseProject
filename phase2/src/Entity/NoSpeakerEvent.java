package Entity;

import java.sql.Time;

/**
 * Class representing NoSpeakerEvent.
 * @author Haohua Ji
 */
public class NoSpeakerEvent extends Event {

  /**
   * Constructor for NoSpeakerEvent.
   * @param roomId      roomId of the room which held the event
   * @param startTime   start time of the event
   * @param endTime     end time of the event
   * @param topic       topic of the event
   * @param max         max members of the event
   */

  public NoSpeakerEvent(int roomId, Time startTime, Time endTime, String topic,
      int max) {
    super(roomId, startTime, endTime, topic, max);
    this.eventtype = "NoSpeakerEvent";
  }

}
