package UseCase;

import Entity.Event;
import Entity.Room;
import Entity.Speaker;
import exception.DoubleBooking;
import exception.InvertedTime;
import java.sql.Time;
import java.util.ArrayList;

public class EventManager {

  private ArrayList<Event> eventpool;

  public boolean checkIsEventValid(int EventID, Room rm, Time start, Time end, Speaker sp, ValidateRoom vr, ValidateSpeaker vs){




    if (diff > 3600000){return false;}

    try {
      if (vs.validateSpeaker(sp, start, end) && vr.validateRoom(rm, start, end) && (start.getHours() >=9
      && start.getTime() <= 16 && end.getHours() <= 17 && end.getHours() >=10)){
        return true;
      }
    } catch (DoubleBooking | InvertedTime doubleBooking) {
      return false;
    }
    return false;
  }

  public void addEvent(int EventID, Room rm, Time start, Time end, Speaker sp, String topic, ValidateRoom vr, ValidateSpeaker vs){

    Event event = new Event(rm.getRoomId(), start, end, topic);

    eventpool.add(event);

    vr.del_room_schedule(rm, start, end);

    vs.validateSpeaker(sp, start, end);
  }

  public boolean delEvent(Event event, ValidateRoom vr, ValidateSpeaker vs){

    int id = event.getId();

    for (Event e : eventpool){
      if (e.getId() == id){
        eventpool.remove(e);
        vr.del_room_schedule(event.getRoom(), event.getStartTime(), event.getEndTime());
        vs.delSpeakerSchedule(event.getSpeaker(), event.getStartTime(), event.getEndTime());
        return true;
      }
    }
    return false;
  }

  public boolean editEvent(Event old, Room new_rm, Time start, Time end, String topic, Speaker new_sp, ValidateRoom vr, ValidateSpeaker vs){

    int id = old.getId();

    for (Event e : eventpool){
      if (e.getId() == id){
        if (checkIsEventValid(old.getId(), new_rm, start, end, new_sp)){
          delEvent(old, vr, vs);
          addEvent(old.getRoomId(), new_rm, start, end, new_sp, topic, vr, vs);
          return true;
        }
      }
    }
    return false;
  }



}
