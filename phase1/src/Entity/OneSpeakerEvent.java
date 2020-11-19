package Entity;

import java.sql.Time;

/**
 * This class represents an OneSpeakerEvent.
 * @author Jun Xing
 * @version 1.0
 */
public class OneSpeakerEvent extends Event {

  /**
   * This method creates an Instance of OneSpeakerEvent. Every OneSpeakerEvent has a unique eventId,
   * one speaker, a related roomId, a topic, its startTime and endTime and a List of attendees.
   * @param speakerId
   * @param roomId
   * @param startTime
   * @param endTime
   * @param topic
   */
  public OneSpeakerEvent(int speakerId, int roomId, Time startTime, Time endTime, String topic) {
    super(roomId, startTime, endTime, topic);
    this.speakerId = speakerId;
  }

  /**
   * This method returns a OneSpeakerEvent's speakerId.
   * @return OneSpeakerEvent's speakerId
   */
  public int getSpeakerId() {
    return speakerId;
  }

  /**
   * This method sets a OneSpeakerEvent's speakerId
   * @param speakerId
   */
  public void setSpeakerId(int speakerId) {
    this.speakerId = speakerId;
  }

}
