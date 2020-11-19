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

  /**
   * This method enable drop schedule of a speaker.
   */
  public void delSpeakerSchedule(int speakerID, Time start, Time end) {

    vs.delSpeakerSchedule(speakerID, start, end);
  }

  /**
   * This method enable drop schedule of a speaker.
   */
  public boolean addSpeaker(String SpeakerName, String Password, String phone, String email) {

    return vs.addSpeaker(SpeakerName, Password, phone, email);
  }

  /**
   * This method return the schedule of a given speaker
   * @return the schedule of a given speaker
   */
  public HashMap<Integer, ArrayList<ArrayList<Time>>> getSpeakerSchedule() {
    return vs.getSpeakerList();
  }

  /**
   * This method return all information of all speakers
   * @return a map of speakers' information
   */
  public HashMap<Integer, String> getSpeakers() {
    return vs.get_speakers_info();
  }
}
