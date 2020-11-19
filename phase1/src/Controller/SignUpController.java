package Controller;

import Gateway.MapGateway;
import Gateway.SpeakerScheduleDataAccess;
import Gateway.UserScheduleDataAccess;
import UseCase.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yezhou on 2020/11/14
 **/
public class SignUpController {

  private ValidateRoom vr;
  private EventManager em;
  private UserAccountManager uam;

  private MapGateway mg = new UserScheduleDataAccess();
  private Userschedule us;
  private String email;

  public SignUpController(String email, UserAccountManager userAccountManager, ValidateRoom validateRoom, EventManager eventManager) {

    HashMap userschedule = mg.read();
    us = new Userschedule(userschedule);
    this.email = email;

    this.vr = validateRoom ;
    this.em = eventManager;
    this.uam = userAccountManager;

  }

  public ArrayList<String> viewEventRegister() {
    try {
      return us.get_user_schedule_info(uam.get_single_user(uam.get_user_id(email)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * This method return a list of all events and their information
   * @return a map of all event and their information
   */
  public HashMap<Integer, String> ViewAllEvents() {
    return em.get_events_info();
  }

  /**
   * This method return all topics.
   * @return a list of all topics of events
   */
  public ArrayList<String> browse(String topic) {
    return em.browse(topic);
  }


  public boolean signup(int event_id, int rm_id) {
    if (us.CheckUserIsBusy(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      if (!vr.check_room_is_full(em.get_event(event_id), vr.get_rm(rm_id))) {
        us.addUserSchedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id));
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  /**
   * This method enable a user to sign up an event, retuern true if the user's time and event could fit.
   * @return boolean wheather the sign up process is successful
   */

  public boolean signup(int event_id) {
    if (us.CheckUserIsBusy(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      if (!vr.check_room_is_full(em.get_event(event_id), vr.get_rm(em.get_event_spots(em.get_event(event_id)).get(0)))) {
        us.addUserSchedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id));
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public boolean cancelEvent(int event_id, int rm_id) {
    if (us.deleteUserschedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      vr.deleteRoom(vr.get_rm(rm_id));
      return true;
    }
    return false;
  }


  /**
   * This method enable a user to drop a event, retuern true if the user drop off from the event.
   * @return boolean wheather drop off process is successful
   */
  public boolean cancelEvent(int event_id){
    if (us.deleteUserschedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      vr.deleteRoom(vr.get_rm(em.get_event_spots(em.get_event(event_id)).get(0)));
      return true;
    }
    return false;
  }

  public void saveuserschedule(){mg.write(Userschedule.user_schedule);};

}
