package UseCase;

import Entity.Room;
import Entity.Speaker;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ValidateSpeaker {

  private HashMap<Speaker, ArrayList<ArrayList<Time>>> speaker_list;

  public void addSpeaker(String SpeakerName, String Password, String phone, String email) {

    Speaker speaker = new Speaker(SpeakerName, Password, phone, email);

    speaker_list.put(speaker, null);
  }

  public boolean validateSpeaker(Speaker speaker, Time start, Time end) {
    if (!speaker_list.containsKey(speaker)) {
      return false;
    }
    for (ArrayList<Time> schedule : speaker_list.get(speaker)) {
      Time start2 = schedule.get(0);
      Time end2 = schedule.get(1);
      if ((start.compareTo(start2) >= 0 && start.compareTo(end2) < 0) | start.compareTo(end) >= 0 |
          (end.compareTo(start2) > 0 && end.compareTo(end2) <= 0)) {
        return false;
      }
    }
    return true;
  }

  public void giveSpeakerNewSchedule(Speaker speaker, Time start, Time end) {
    ArrayList<Time> temp = new ArrayList<>();
    temp.add(start);
    temp.add(end);
    ArrayList<ArrayList<Time>> temp1 = speaker_list.get(speaker);
    int indicator = 0;
    for (ArrayList<Time> time : temp1) {
      if (time.equals(temp)) {
        indicator = 1;
        break;
      }
    }
    if (indicator == 0) {
      temp1.add(temp);
    }
  }

  public boolean delSpeakerSchedule(int speaker_ID, Time start, Time end) {
    ArrayList<Time> p = new ArrayList<>();
    p.add(start);
    p.add(end);
    for (Speaker sp: speaker_list.keySet()){
      if(speaker_ID == sp.getUserId()){
        for (ArrayList<Time> o : speaker_list.get(sp)) {
          if (o.equals(p)) {
            speaker_list.get(sp).remove(p);
            return true;
          }
        }
      }
    }
    return false;
  }

  public HashMap<Speaker, ArrayList<ArrayList<Time>>> getSpeakerList() {

    return speaker_list;

  }

  public Speaker get_sp(int sp_ID){
    HashMap<Integer, Speaker> tmp = null;
    try {
      for (Speaker sp : this.getSpeakerList().keySet()) {
        tmp.put(sp.getUserId(), sp);
      }
      return tmp.get(sp_ID);
    }catch(NullPointerException e){
      return new Speaker("", "", "", "");
    }
  }
}
