package com.group0014.iconference.Entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by yezhou on 2020/11/28
 **/
public class NoSpeakerEvent extends Event{

  public NoSpeakerEvent(int roomId, Time startTime, Time endTime, String topic,
      int max) {
    super(roomId, startTime, endTime, topic, max);
    this.eventtype = "NoSpeakerEvent";
  }

  @Override
  public void setSpeakerId(ArrayList<Integer> s) {
  }


  public String toString() {
    return "Event{" +
        "id=" + eventId +
        ", roomId=" + roomId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", topic='" + topic + '\'' +
        ", attendees=" + attendees +
        '}';
  }

}
