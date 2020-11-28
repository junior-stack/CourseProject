package Controller;

import Entity.Schedulable;
import Gateway.MapGateway;
import Gateway.UserScheduleDataAccess;
import UseCase.SchedulableManager;
import UseCase.UserScheduleManager;
import UseCase.EventManager;
import UseCase.RoomManager;
import UseCase.UserAccountManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yezhou on 2020/11/14
 **/
public class SignUpController {

  private SchedulableManager rooms_list;
  private EventManager em;
  private UserAccountManager uam;

  private MapGateway mg = new UserScheduleDataAccess();
  private UserScheduleManager us;
  private String email;

  public SignUpController(String email, UserAccountManager userAccountManager,
                          SchedulableManager roomManager, EventManager eventManager) {

    this.email = email;

    this.rooms_list = roomManager ;
    this.em = eventManager;
    this.uam = userAccountManager;

    HashMap userschedule = mg.read();
    try {
      if (!userschedule.containsKey(uam.get_single_user(uam.get_user_id(email)))) {
        userschedule.put(uam.get_single_user(uam.get_user_id(email)), new ArrayList<>());
      }
    }catch (NullPointerException e){
      userschedule.put(uam.get_single_user(uam.get_user_id(email)), new ArrayList<>());
    }
    us = new UserScheduleManager(userschedule);

  }

  public ArrayList<String> viewEventRegister() {
    try {
      return us.get_user_schedule_info(uam.get_single_user(uam.get_user_id(email)));
    } catch (NullPointerException e) {
      return null;
    }
  }

  /**
   * This method return a list of all events and their information
   *
   * @return a map of all event and their information
   */
  public HashMap<Integer, String> ViewAllEvents() {
    return em.get_events_info();
  }

  /**
   * This method return all topics.
   *
   * @return a list of all topics of events
   */
  public ArrayList<String> browse(String topic) {
    return em.browse(topic);
  }


  /**
   * This method enable a user to sign up an event, retuern true if the user's time and event could
   * fit.
   *
   * @return boolean wheather the sign up process is successful
   */

  public boolean signup(int event_id) {
    if (us.CheckUserIsBusy(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      Time start = em.get_event(event_id).getStartTime();
      Time end = em.get_event(event_id).getEndTime();
      if (!rooms_list.CheckSchedulableAvailable(em.get_event(event_id).getRoomId(), start, end)) {
        us.addUserSchedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id), rooms_list
            .get_sch(em.get_event(event_id).getRoomId()));
        return true;
      }
      else
        return false;
    }
    return false;
  }


  /**
   * This method enable a user to drop a event, retuern true if the user drop off from the event.
   *
   * @return boolean wheather drop off process is successful
   */
  public boolean cancelEvent(int event_id) {
    Time start = em.get_event(event_id).getStartTime();
    Time end = em.get_event(event_id).getEndTime();
    if (us.deleteUserschedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id), rooms_list
        .get_sch(em.get_event(event_id).getRoomId()))) {
      return true;
    }
    return false;
  }

  public void saveuserschedule() {
    mg.write(us.user_schedule);
  }

  ;

}
