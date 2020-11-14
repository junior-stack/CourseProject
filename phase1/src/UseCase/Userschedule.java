package UseCase;

import Entity.Event;
import Entity.User;
import exception.SignupConflict;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yezhou on 2020/11/14
 **/
public class Userschedule {
  public HashMap<User, ArrayList<Event>> user_schedule;

  public ArrayList<Event> get_user_schedule(User user){
    return user_schedule.get(user);
  }

  public boolean CheckUserIsBusy(User user, Event e) throws SignupConflict {
    Time start = e.getStartTime();
    Time end = e.getEndTime();
    for(Event event: user_schedule.get(user)){
      if(start.compareTo(event.getStartTime()) >= 0 && start.compareTo(event.getStartTime()) < 0){
        throw new SignupConflict(user, event);
      }
      else if(end.compareTo(event.getStartTime()) >0 && end.compareTo(event.getEndTime())<=0){
        throw new SignupConflict(user, e);
      }
    }
    return true;
  }

  public void addUserSchedule(User u, Event e){
    user_schedule.get(u).add(e);
    u.addEvents(e.getId());
    e.addAttendee(u.getUserId());
  }

  public boolean deleteUserschedule(User u, Event e){
      if(user_schedule.get(u).remove(e)){
        u.getEvents().remove(e.getId());
        e.getAllAttendee().remove(e.getId());
        return true;
      }
      return false;
  }

  public ArrayList<String> get_user_schedule_info(User user){
    ArrayList<String> tmp = new ArrayList<>();
    for(Event e: user_schedule.get(user)){
      tmp.add(e.toString());
    }
    return tmp;
  }


}
