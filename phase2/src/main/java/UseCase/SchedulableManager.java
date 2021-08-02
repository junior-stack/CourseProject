package UseCase;

import Entity.Schedulable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yezhou
 **/
public class SchedulableManager {


  /**
   * This method takes the schedulable lists from the class which stores the Schedulables and help
   * the organizer analyze whether the target schedulable is available at a given time when
   * scheduling an event
   *
   * @param schedulables the list of schedulables
   * @param id           the id of the target schedulable
   * @param start        the start time of the event
   * @param end          the end time of the event
   * @return boolean true iff the target schedulable is available during the event period
   */
  public boolean CheckSchedulableAvailable(List<Schedulable> schedulables, Integer id, Time start,
      Time end) {
    for (Schedulable sch : schedulables) {
      if (sch.give_id().equals(id)) {
        return sch.CheckSchedulable(start, end);
      }
    }
    return false;
  }

  /**
   * This method takes the schedulable lists from the class which stores the Schedulables and adds a
   * new schedule time to the schedulable's schedule list by organizer
   *
   * @param schedulables the list of schedulables
   * @param id           the id of the target schedulable
   * @param start        the start time of the event
   * @param end          the end time of the event
   */
  public void giveSchedulableNewSchedule(List<Schedulable> schedulables, Integer id, Time start,
      Time end) {
    for (Schedulable sch : schedulables) {
      if (sch.give_id().equals(id)) {
        sch.giveSchedulableNewSchedule(start, end);
      }
    }
  }

  /**
   * This method takes the schedulable lists from the class which stores the Schedulables and
   * deletes schedule time from the schedulable's schedule list by organizer
   *
   * @param schedulables the list of schedulables
   * @param id           id the id of the target schedulable
   * @param start        the start time of the event
   * @param end          the end time of the event
   * @return true iff the deletion is successful
   */

  public boolean delSchedulableSchedule(List<Schedulable> schedulables, Integer id, Time start,
      Time end) {
    for (Schedulable sch : schedulables) {
      if (sch.give_id().equals(id)) {
        return sch.delSchedulableSchedule(start, end);
      }
    }
    return false;
  }

  /**
   * This method returns the hashmap that corresponds each schedulable's id to this their schedule
   * list from the schedulables
   *
   * @param schedulables the list of schedulables
   * @return Hashmaph hashmap that corresponds each schedulable's id to this their schedule list
   * from the schedulables
   */
  public HashMap<Integer, ArrayList<ArrayList<Time>>> getScheduleableSchedulelist(
      List<Schedulable> schedulables) {
    HashMap<Integer, ArrayList<ArrayList<Time>>> tmp = new HashMap<>();
    for (Schedulable sch : schedulables) {
      tmp.put(sch.give_id(), sch.getScheduleableSchedulelist());
    }
    return tmp;
  }


  /**
   * This method returns the list of string information of the schedulables
   *
   * @param schedulables the list of schedulables
   * @return list of schedulables string information
   */
  public ArrayList<String> get_schedulables_info(List<Schedulable> schedulables) {
    ArrayList<String> tmp = new ArrayList<>();
    for (Schedulable sch : schedulables) {
      tmp.add(sch.toString());
    }
    return tmp;
  }


}
