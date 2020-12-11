package Entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Schedulable Interface.
 * @author Zhou Ye, Hanzi Zhang
 **/
public interface Schedulable {

  /**
   * This method checks availability of the schedulable at a given time
   *
   * @param start the start time of a given schedule
   * @param end   the end time of a given schedule
   * @return boolean check whether the schedulable is available at this period
   */
  boolean CheckSchedulable(Time start, Time end);

  /**
   * This method gives a new schedule to the schedulable list
   *
   * @param start the start time of the new schedule
   * @param end   the end time of the new schedule
   */
  void giveSchedulableNewSchedule(Time start, Time end);

  /**
   * This method deletes a schedule from the schedulable's  schedule list
   *
   * @param start the start time of the schedule
   * @param end   the end time of the schedule
   * @return boolean check whether the deletion is successful
   */
  boolean delSchedulableSchedule(Time start, Time end);

  /**
   * This method returns the schedule list of this schedulable
   *
   * @return ArrayList<ArrayList < Time>> the schedule list of this schedulable
   */
  ArrayList<ArrayList<Time>> getScheduleableSchedulelist();

  /**
   * This method returns the string information of this schedulable given an ID
   *
   * @param sch the id of the schedulable, it can be a roomID or a Speaker ID
   * @return String the string information of this schedulable
   */
  String get_sch_info(int sch);


  /**
   * This method gives the id of this schedulable
   *
   * @return Integer ID of the schedulable
   */
  Integer give_id();


  String get_sch_info();

}
