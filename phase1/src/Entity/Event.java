package Entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Event {

  //之后改一个更好的办法编号
  private static int counter = 0;
  protected int speakerId;        //是否用protected?
  private int eventId;

  private int organizerId;                 // OrganizerID  要给么event？再想想
  private int roomId;
  private Time startTime;
  private Time endTime;
  private String topic;
  private List<Integer> attendees = new ArrayList<>();
  //private int remaining; // remaining 放Room 还是Event?
  // 做signup event 的人确认如何validate.
  // 要先在use case 里面checkuser在event 自己的时间内是否已经参加别的event..... 专门validater method
  // 做signup event 的人可以参照课上学生enroll course 的例子。

  public Event(int roomId, Time startTime, Time endTime, String topic) {
    this.eventId = counter;
    this.roomId = roomId;
    this.topic = topic;
    this.startTime = startTime;
    this.endTime = endTime;

    counter++;
  }

  public int getId() {
    return eventId;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public Time getStartTime() {
    return startTime;
  }

  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  public Time getEndTime() {
    return endTime;
  }

  public void setEndTime(Time endTime) {
    this.endTime = endTime;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public List<Integer> getAllAttendee() {
    return attendees;
  }

  public void addAttendee(Attendee attendee) {
    this.attendees.add(attendee.getUserId());
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

