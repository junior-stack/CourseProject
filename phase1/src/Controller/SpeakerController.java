package Controller;

import Entity.Room;
import Entity.Speaker;
import UseCase.ValidateRoom;
import UseCase.ValidateSpeaker;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class SpeakerController {

  private ValidateSpeaker vs;

  public SpeakerController() {
    ValidateSpeaker vs = new ValidateSpeaker();
  }

  public void delSpeakerSchedule(int speakerID, Time start, Time end) {

    vs.delSpeakerSchedule(speakerID, start, end);
  }

  public void addSpeaker(String SpeakerName, String Password, String phone, String email) {

    vs.addSpeaker(SpeakerName, Password, phone, email);
  }

  public HashMap<Speaker, ArrayList<ArrayList<Time>>> getSpeakerSchedule() {
    return vs.getSpeakerList();
  }

  public Collection<Speaker> getSpeakers() {
    return vs.getSpeakerList().keySet();
  }
}
