package Controller;

import UseCase.EventManager;
import UseCase.UserAccountManager;
import UseCase.Userschedule;
import UseCase.ValidateRoom;
import exception.SignupConflict;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yezhou on 2020/11/14
 **/
public class SignUpController {

  private ValidateRoom vr;
  private Userschedule us;
  private UserAccountManager uam;
  private EventManager em;
  private String email;

  public ArrayList<String> vieweventRegister() {
    try {
      return us.get_user_schedule_info(uam.get_single_user(uam.get_user_id(email)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public HashMap<Integer, String> ViewAllEvents() {
    return em.get_events_info();
  }

  public ArrayList<String> browse(String topic) {
    return em.browse(topic);
  }

  public void signup(int event_id, int rm_id) throws SignupConflict {
    if (us.CheckUserIsBusy(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      if (!vr.check_room_is_full(em.get_event(event_id), vr.get_rm(rm_id))) {
        us.addUserSchedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id));
      } else {
        System.out.println("The room is full");
      }
    }
    throw new SignupConflict(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id));
  }

  public void signup(int event_id) throws SignupConflict {
    if (us.CheckUserIsBusy(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      if (!vr.check_room_is_full(em.get_event(event_id), vr.get_rm(em.get_event_spots(em.get_event(event_id)).get(0)))) {
        us.addUserSchedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id));
      } else {
        System.out.println("The room is full");
      }
    }
    throw new SignupConflict(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id));
  }

  public boolean cancelEvent(int event_id, int rm_id) {
    if (us.deleteUserschedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      vr.deleteRoom(vr.get_rm(rm_id));
      return true;
    }
    return false;
  }

  public boolean cancelEvent(int event_id){
    if (us.deleteUserschedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      vr.deleteRoom(vr.get_rm(em.get_event_spots(em.get_event(event_id)).get(0)));
      return true;
    }
    return false;
  }

}
