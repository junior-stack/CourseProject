package Controller;


import UseCase.ValidateSpeaker;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class SpeakerController {

  private ValidateSpeaker vs;

  public SpeakerController(ValidateSpeaker vs) {
    this.vs = vs;
  }

  public void delSpeakerSchedule(int speakerID, Time start, Time end) {

    vs.delSpeakerSchedule(speakerID, start, end);
  }

  public void addSpeaker(String SpeakerName, String Password, String phone, String email) {

    vs.addSpeaker(SpeakerName, Password, phone, email);
  }

  public HashMap<Integer, ArrayList<ArrayList<Time>>> getSpeakerSchedule() {
    return vs.getSpeakerList();
  }

  public HashMap<Integer, String> getSpeakers() {
    return vs.get_speakers_info();
  }
}
