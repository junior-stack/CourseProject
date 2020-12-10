package Entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * This class represents an Speaker.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class Speaker extends User implements Schedulable {

  /**
   * This method creates an Instance of Speaker. Username, password phone, email are required. Also
   * a Speaker has an identity of "Speaker".
   *
   * @param username the name of this speaker
   * @param password the password of this speaker account
   * @param phone    the phone number of this speaker
   * @param email    the email address of this speaker
   */

  public Speaker(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.type = "Speaker";
  }


  /**
   * This method checks whether this speaker has a schedule at a given time period
   *
   * @param start the start time of the given time period
   * @param end   the end time of the given time period
   */
  @Override
  public boolean CheckSchedulable(Time start, Time end) {
    for (ArrayList<Time> t : schedule) {
      if (start.compareTo(t.get(0)) >= 0 && start.compareTo(t.get(1)) < 0) {
        return false;
      } else if (end.compareTo(t.get(0)) > 0 && end.compareTo(t.get(1)) <= 0) {
        return false;
      } else if (start.compareTo(end) >= 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method adds a schedule to the speakers' schedule list
   *
   * @param start the start time of the given time period
   * @param end   the end time of the given time period
   */

  @Override
  public void giveSchedulableNewSchedule(Time start, Time end) {
    ArrayList<Time> tmp = new ArrayList<>();
    tmp.add(start);
    tmp.add(end);
    schedule.add(tmp);
  }

  /**
   * This method deletes a schedule time from the speaker's schedule list
   *
   * @param start the start time of the given schedule
   * @param end   the end time of the given schedule
   * @return boolean whether the deletion is successful
   */
  @Override
  public boolean delSchedulableSchedule(Time start, Time end) {
    for (ArrayList<Time> t : schedule) {
      if (t.get(0).equals(start) && t.get(1).equals(end)) {
        schedule.remove(t);
        return true;
      }
    }
    return false;
  }


  /**
   * @return ArrayList<ArrayList < Time>> a list of the speaker's schedule times which contains start
   * time and end time
   */
  @Override
  public ArrayList<ArrayList<Time>> getScheduleableSchedulelist() {
    return schedule;
  }

  /**
   * @param sch the id of this schedulable, it is a speakerID
   * @return String the information about this schedulable instance
   */
  @Override
  public String get_sch_info(int sch) {
    return schedule.toString();
  }

  /**
   * @return Integer the id of this schedulable. It is SpeakerId
   */
  @Override
  public Integer give_id() {
    return this.getUserId();
  }

  @Override
  public String get_sch_info() {
    return this.toString();
  }
}

