package com.group0014.iconference.Entity;

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
   * @param speakerId
   * @param roomId
   * @param startTime
   * @param endTime
   * @param topic
   */
  public OneSpeakerEvent(int roomId, Time startTime, Time endTime, String topic, int max, ArrayList<Integer> speakerId) {
    super(roomId, startTime, endTime, topic, max);
    this.speakerId = speakerId;
  }


  /**
   * This method sets a OneSpeakerEvent's speakerId
   *
   * @param s
   */

  @Override
  public void setSpeakerId(ArrayList<Integer> s) {
    this.speakerId = s;
  }

  @Override
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

}
