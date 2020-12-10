package Entity;

import java.sql.Time;
import java.util.ArrayList;


public class NoSpeakerEvent extends Event {

  /**
   *
   * @param roomId
   * @param startTime
   * @param endTime
   * @param topic
   * @param max
   */

  public NoSpeakerEvent(int roomId, Time startTime, Time endTime, String topic,
      int max) {
    super(roomId, startTime, endTime, topic, max);
    this.eventtype = "NoSpeakerEvent";
  }

}
