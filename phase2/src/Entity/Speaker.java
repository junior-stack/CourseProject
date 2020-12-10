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
   * @param username
   * @param password
   * @param phone
   * @param email
   */

  public Speaker(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.type = "Speaker";
  }


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

  @Override
  public void giveSchedulableNewSchedule(Time start, Time end) {
    ArrayList<Time> tmp = new ArrayList<>();
    tmp.add(start);
    tmp.add(end);
    schedule.add(tmp);
  }

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


  @Override
  public ArrayList<ArrayList<Time>> getScheduleableSchedulelist() {
    return schedule;
  }

  @Override
  public String get_sch_info(int sch) {
    return schedule.toString();
  }

  @Override
  public Integer give_id() {
    return this.getUserId();
  }

  @Override
  public String get_sch_info() {
    return this.toString();
  }
}

