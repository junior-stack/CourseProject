package Controller;

import Dao.UserEventDao;

import UseCase.EventManager;
import UseCase.RoomManager;
import UseCase.SignupManager;
import UseCase.UserAccountManager;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yezhou on 2020/11/14
 **/
public class SignUpController {

  private final RoomManager rooms_list;
  private final EventManager em;
  private final UserAccountManager uam;
  private final String email;
  private final SignupManager us;

  public SignUpController(String email, UserAccountManager userAccountManager,
      RoomManager roomManager, EventManager eventManager) {

    this.email = email;

    this.rooms_list = roomManager;
    this.em = eventManager;
    this.uam = userAccountManager;

    HashMap userschedule = UserEventDao.getAsHashMap();
    try {
      if (!userschedule.containsKey(uam.get_single_user(uam.get_user_id(email)))) {
        userschedule.put(uam.get_single_user(uam.get_user_id(email)), new ArrayList<>());
      }
    } catch (NullPointerException e) {
      userschedule.put(uam.get_single_user(uam.get_user_id(email)), new ArrayList<>());
    }
    us = new SignupManager(userschedule);
  }

  public ArrayList<String> viewEventRegister() {
    try {
      return us.get_user_schedule_info(uam.get_single_user(uam.get_user_id(email)));
    } catch (NullPointerException e) {
      return null;
    }
  }

//  /**
//   * This method return all topics.
//   *
//   * @return a list of all topics of events
//   */
//  public ArrayList<String> browse(String topic) {
//    return em.browse(topic);
//  }


  /**
   * This method enable a user to sign up an event, retuern true if the user's time and event could
   * fit.
   *
   * @return boolean wheather the sign up process is successful
   */

  public boolean signup(int event_id) {
    Integer rm_id;
    ArrayList<Time> time;
    rm_id = em.getLocation(event_id);
    time = em.gettime(event_id);
    if (rm_id == null || time == null) {
      return false;
    }
    if (!rooms_list.CheckRemainingSpot(rm_id, time.get(0), time.get(1))) {
      return false;
    }
    if (us.CheckUserIsBusy(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      Time start = time.get(0);
      Time end = time.get(1);
      if (rooms_list.CheckRemainingSpot(rm_id, start, end)) {
        us.addUserSchedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id));
        rooms_list.DecreaseRemainingSpot(rm_id, start, end);
        return true;
      }
    }
    return false;
  }


  /**
   * This method enable a user to drop a event, retuern true if the user drop off from the event.
   *
   * @return boolean wheather drop off process is successful
   */
  public boolean cancelEvent(int event_id) {
    Integer rm_id = em.getLocation(event_id);
    ArrayList<Time> time = em.gettime(event_id);
    if (us
        .deleteUserschedule(uam.get_single_user(uam.get_user_id(email)), em.get_event(event_id))) {
      rooms_list.IncreaseRemainingSpot(rm_id, time.get(0), time.get(1));
      return true;
    }
    return false;
  }

  public void save() {
    us.saveUserSchedule();
  }
}
