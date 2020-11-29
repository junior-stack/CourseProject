package UseCase;

import Entity.Room;
import Entity.Schedulable;
import Entity.Speaker;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by yezhou on 2020/11/28
 **/
public class SchedulableManager{




  public boolean CheckSchedulableAvailable(List<Schedulable> schedulables, Integer id, Time start, Time end){
    for(Schedulable sch: schedulables){
      if(sch.give_id() == id){
        return sch.CheckSchedulable(start, end);
      }
    }
    return false;
  }

  public void giveSchedulableNewSchedule(List<Schedulable> schedulables, Integer id, Time start, Time end){
    for(Schedulable sch: schedulables){
      if(sch.give_id() == id){
        sch.giveSchedulableNewSchedule(start, end);
      }
    }
  }

  public boolean delSchedulableSchedule(List<Schedulable> schedulables, Integer id, Time start, Time end){
    for(Schedulable sch: schedulables){
      if(sch.give_id() == id){
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

  public String get_sch_info(List<Schedulable> schedulables, int sch_ID){
    for(Schedulable sch: schedulables){
      if(sch.give_id() == sch_ID){
        return sch.get_sch_info();
      }
    }
    return "There is no such item with such id";
  }

  public ArrayList<String> get_schedulables_info(List<Schedulable> schedulables){
    ArrayList<String> tmp = new ArrayList<>();
    for(Schedulable sch: schedulables){
      tmp.add(sch.toString());
    }
    return tmp;
  }

  public Schedulable get_sch(List<Schedulable> schedulables, int sch_id){
    for(Schedulable sch: schedulables){
      if(sch.give_id() == sch_id){
        return sch;
      }
    }
    return null;
  }


}
