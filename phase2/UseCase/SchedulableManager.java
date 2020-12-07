package UseCase;

import Entity.Schedulable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yezhou on 2020/11/28
 **/
public class SchedulableManager{




  public boolean CheckSchedulableAvailable(List<Schedulable> schedulables, Integer id, Time start, Time end){
    for(Schedulable sch: schedulables){
      if(sch.give_id().equals(id)){
        return sch.CheckSchedulable(start, end);
      }
    }
    return false;
  }

  public void giveSchedulableNewSchedule(List<Schedulable> schedulables, Integer id, Time start, Time end){
    for(Schedulable sch: schedulables){
      if(sch.give_id().equals(id)){
        sch.giveSchedulableNewSchedule(start, end);
      }
    }
  }

  public boolean delSchedulableSchedule(List<Schedulable> schedulables, Integer id, Time start, Time end){
    for(Schedulable sch: schedulables){
      if(sch.give_id().equals(id)){
        return sch.delSchedulableSchedule(start, end);
      }
    }
    return false;
  }

  public HashMap<Integer, ArrayList<ArrayList<Time>>> getScheduleableSchedulelist(List<Schedulable> schedulables){
    HashMap<Integer, ArrayList<ArrayList<Time>>> tmp = new HashMap<>();
    for(Schedulable sch: schedulables){
      tmp.put(sch.give_id(), sch.getScheduleableSchedulelist());
    }
    return tmp;
  }


  public ArrayList<String> get_schedulables_info(List<Schedulable> schedulables){
    ArrayList<String> tmp = new ArrayList<>();
    for(Schedulable sch: schedulables){
      tmp.add(sch.toString());
    }
    return tmp;
  }



}
