package Controller;

import Entity.Speaker;
import UseCase.ValidateSpeaker;
import javafx.util.Pair;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SpeakerController {

  private ValidateSpeaker vs;

  public SpeakerController() {
    ValidateSpeaker vs = new ValidateSpeaker();
  }

  public void giveSpeakerNewSchedule(Speaker speaker, Time start, Time end) {
    vs.giveSpeakerNewSchedule(speaker, start, end);
  }

  public void delSpeakerSchedule(Speaker speaker, Time start, Time end) {
    vs.delSpeakerSchedule(speaker, start, end);
  }

  public boolean validateSpeaker(Speaker speaker, Time start, Time end) {
    return vs.validateSpeaker(speaker, start, end);
  }

  public void addSpeaker(String SpeakerName, String Password, String phone, String email) {
    vs.addSpeaker(SpeakerName, Password, phone, email);
  }

  public HashMap<Speaker, ArrayList<Pair<Time, Time>>> getSpeakerSchedule() {
    return vs.getSpeakerList();
  }

  public Collection<Speaker> getSpeakers() {
    return vs.getSpeakerList().keySet();
  }
}
