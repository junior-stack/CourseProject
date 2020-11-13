package UseCase;

import Entity.Event;
import Entity.Room;
import Entity.Speaker;
import exception.DoubleBooking;
import exception.InvertedTime;
import java.sql.Time;
import java.util.ArrayList;

public class EventManager {

  private static ArrayList<Event> eventpool;

  public boolean checkIsEventValid(Room rm, Time start, Time end, Speaker sp, ValidateRoom vr, ValidateSpeaker vs){
    Time beggining = java.sql.Time.valueOf("09:00:00");
    Time ending = java.sql.Time.valueOf("17:00:00");



    if (start.compareTo(beggining) < 0 && end.compareTo(ending) > 0){return false;}

    try {
      if (vs.validateSpeaker(sp, start, end) && vr.validateRoom(rm, start, end)){
        return true;
      }
    } catch (DoubleBooking doubleBooking) {
      System.out.println(doubleBooking.getMessage());
      return false;
    }catch(InvertedTime e){
      System.out.println(e.getMessage());
      return false;
    }
    return false;
  }

  public void addEvent(Room rm, Time start, Time end, Speaker sp, String topic, ValidateRoom vr, ValidateSpeaker vs){

    Event event = new Event(rm.getRoomId(), start, end, topic);

    eventpool.add(event);

    vr.give_room_schedule(rm, start, end);

    vs.giveSpeakerNewSchedule(sp, start, end);
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
    Room old_rm;
    Speaker old_sp;

    for (Event e : eventpool){
      if (e.getId() == id){
        delEvent(old,vr, vs);
        if(this.checkIsEventValid(new_rm, start, end, new_sp, vr, vs)){
          this.addEvent(new_rm, start, end, new_sp, topic, vr, vs);
          return true;
        }
        else{
          old_rm = vr.get_rm(old.getRoomId());
          old_sp = vs.get_sp(old.getSpeaker());
          this.addEvent(old_rm, start, end, old_sp, topic, vr, vs);
          return false;
        }
      }
    }
    return false;
  }



}
