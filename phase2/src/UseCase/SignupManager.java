package UseCase;

import Dao.UserEventDao;
import Entity.Event;
import Entity.User;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a AttendeeScheduleManager
 *
 * @author Ye Zhou &
 * @version 1.0
 **/
public class SignupManager {

  public HashMap<User, ArrayList<Event>> user_schedule;

  /**
   * Create a AttendeeScheduleManager with given user_schedule. A user_schedule is a HashMap of
   * Users to an ArrayList of Events.
   *
   * @param user_schedule the hashmap that maps each user to the list of event he signed up
   */
  public SignupManager(HashMap<User, ArrayList<Event>> user_schedule) {
    this.user_schedule = user_schedule;
  }

  /**
   * Get the Event ArrayList of given user.
   *
   * @param user the target user
   * @return Event ArrayList of given user
   */
  public ArrayList<Event> get_user_schedule(User user) {
    return user_schedule.get(user);
  }

  /**
   * Check if the user is available during the time period of the given Event.
   *
   * @param user the given user
   * @param e    the event the user wants to sign up
   * @return boolean of the result
   */
  public boolean CheckUserIsBusy(User user, Event e) {
    Time start = e.getStartTime();
    Time end = e.getEndTime();
    ArrayList<Event> events;
    try {
      events = user_schedule.get(user);
    } catch (NullPointerException x) {
      return true;
    }
    for (Event event : events) {
      if (start.compareTo(event.getStartTime()) >= 0 && start.compareTo(event.getStartTime()) < 0) {
        return false;
      } else if (end.compareTo(event.getStartTime()) > 0
          && end.compareTo(event.getEndTime()) <= 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Add Event to the User's Event ArrayList in this AttendeeScheduleManager, add eventId to the
   * user's List of eventId and add the userId to the Event's list of attendee.
   *
   * @param u the target user
   * @param e the event of the user has signed up
   */
  public void addUserSchedule(User u, Event e) {
    if (!user_schedule.containsKey(u)) {
      user_schedule.put(u, new ArrayList<>());
    }
    user_schedule.get(u).add(e);
    u.addEvents(e.getId());
    e.addAttendee(u.getUserId());
  }

  /**
   * Delete Event from the User's Event ArrayList in this AttendeeScheduleManager, remove eventId
   * from the user's List of eventId and remove the userId from the Event's list of attendee.
   *
   * @param u the given user
   * @param e the event the user wants to cancel
   * @return boolean of whether the operation are successfully executed
   */
  public boolean deleteUserschedule(User u, Event e) {
    if (user_schedule.get(u).remove(e)) {
      u.getEvents().remove(e);
      e.getAllAttendee().remove(u);
      return true;
    }
    return false;
  }

  /**
   * Return an ArrayList of all string representattion of Event of this User.
   *
   * @param user the given user
   * @return ArrayList of all string representattion of Event of this User
   */
  public ArrayList<String> get_user_schedule_info(User user) {
    ArrayList<String> tmp = new ArrayList<>();
    for (Event e : user_schedule.get(user)) {
      tmp.add(e.toString());
    }
    return tmp;
  }

  public void saveUserSchedule() {
    UserEventDao.saveHashMap(this.user_schedule);
  }

}
