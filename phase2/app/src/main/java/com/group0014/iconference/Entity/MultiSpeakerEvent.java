package com.group0014.iconference.Entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by yezhou on 2020/11/28
 **/
public class MultiSpeakerEvent extends Event{

  public MultiSpeakerEvent(int roomId, Time startTime, Time endTime, String topic,
      int max, ArrayList<Integer> sp_id) {
    super(roomId, startTime, endTime, topic, max);
    this.eventtype = "MultipleSpeakerEvent";
    speakerId = sp_id;
  }



  public void AddSpeaker(Integer sp_id){
    speakerId.add(sp_id);
  }

  public boolean DelSpeaker(Integer sp_id){
    if(speakerId.contains(sp_id)){
      speakerId.remove(sp_id);
      return true;
    }
    return false;
  }



  public String toString() {
    return "Event{" +
        "id=" + eventId +
        ", speakerId=" + speakerId +
        ", roomId=" + roomId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", topic='" + topic + '\'' +
        ", attendees=" + attendees +
        '}';
  }

  @Override
  public void setSpeakerId(ArrayList<Integer> s) {
    speakerId = s;
  }

}
