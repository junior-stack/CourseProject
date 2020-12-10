package Entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by yezhou on 2020/11/28
 **/
public class NoSpeakerEvent extends Event {

  public NoSpeakerEvent(int roomId, Time startTime, Time endTime, String topic,
      int max) {
    super(roomId, startTime, endTime, topic, max);
    this.eventtype = "NoSpeakerEvent";
  }


}
