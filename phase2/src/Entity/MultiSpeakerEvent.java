package Entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * @author yezhou
 **/
public class MultiSpeakerEvent extends Event {

  public MultiSpeakerEvent(int roomId, Time startTime, Time endTime, String topic,
      int max, ArrayList<Integer> sp_id) {
    super(roomId, startTime, endTime, topic, max);
    this.eventtype = "MultipleSpeakerEvent";
    speakerId = sp_id;
  }

  /**
   * This method adds a speaker to the event
   *
   * @param sp_id the new speaker id the organizer want to add to this event
   */

  public void AddSpeaker(Integer sp_id) {
    speakerId.add(sp_id);
  }

  /**
   * This method deletes a speaker to the event
   *
   * @param sp_id the speaker id the organizer want to remove from this event
   */
  public boolean DelSpeaker(Integer sp_id) {
    if (speakerId.contains(sp_id)) {
      speakerId.remove(sp_id);
      return true;
    }
    return false;
  }


}
