package Entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by yezhou on 2020/11/28
 **/
public interface Schedulable {

  boolean CheckSchedulable(Time start, Time end);

  void giveSchedulableNewSchedule(Time start, Time end);

  boolean delSchedulableSchedule(Time start, Time end);

  ArrayList<ArrayList<Time>> getScheduleableSchedulelist();

  String get_sch_info(int sch);


  Integer give_id();

  String get_sch_info();

}
