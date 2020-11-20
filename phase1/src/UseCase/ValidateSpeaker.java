package UseCase;

import Entity.Speaker;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


/**
 * A class representing a ValidateRoom.
 *
 * @author Ye Zhou & Hanzhi Zhang
 * @version 1.0
 **/
public class ValidateSpeaker {

  public static HashMap<Speaker, ArrayList<ArrayList<Time>>> speaker_list;

  /**
   * Create a ValidateRoom with given speaker_list. A speaker_list is a HashMap of {Speaker to event
   * schedule which is [ArrayLists of ArrayList of (start and end time)]}.
   *
   * @param speaker_list
   */
  public ValidateSpeaker(HashMap<Speaker, ArrayList<ArrayList<Time>>> speaker_list) {
    ValidateSpeaker.speaker_list = speaker_list;
  }

  /**
   * This method add a speaker to the speaker_list if all parameters meets the requirements. A new
   * speaker has no Event schedule.
   *
   * @param SpeakerName
   * @param Password
   * @param phone
   * @param email
   * @return
   */
  public boolean addSpeaker(String SpeakerName, String Password, String phone, String email) {
    if (!this.isValidEmail(email)) {
      System.out.println("The email address is invalid");
      return false;
    }
    if (!this.isValidPassword(Password)) {
      System.out.println("Password cannot contain space nor be blank");
      return false;
    }
    if (!this.isValidPhone(phone)) {
      System.out.println("Phone number should only contain numeric characters");
      return false;
    }

    Speaker speaker = new Speaker(SpeakerName, Password, phone, email);

    speaker_list.put(speaker, null);
    return true;
  }

  /**
   * This method check if the speaker is in the system and the speaker is available during the given
   * star to end time period.
   *
   * @param speaker
   * @param start
   * @param end
   * @return the boolean of the result
   */
  public boolean validateSpeaker(Speaker speaker, Time start, Time end) {
    if (!speaker_list.containsKey(speaker)) {
      return false;
    }
    if (speaker_list.get(speaker) == null) {
      return true;
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

  /**
   * This method adds a new time spot of start and end time to a speaker's event schedule if the
   * speaker is available during the start to end time period.
   *
   * @param speaker
   * @param start
   * @param end
   */
  public void giveSpeakerNewSchedule(Speaker speaker, Time start, Time end) {
    ArrayList<Time> temp = new ArrayList<>();
    temp.add(start);
    temp.add(end);
    ArrayList<ArrayList<Time>> tmp = new ArrayList<>();
    tmp.add(temp);
    ArrayList<ArrayList<Time>> temp1 = speaker_list.get(speaker);
    int indicator = 0;
    if (temp1 == null) {
      speaker_list.replace(speaker, tmp);
      return;
    }
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

  /**
   * This method the event with given start and end time from the event schedule of the speaker with
   * given speakerId.
   *
   * @param speaker_ID
   * @param start
   * @param end
   */
  public void delSpeakerSchedule(int speaker_ID, Time start, Time end) {
    ArrayList<Time> p = new ArrayList<>();
    p.add(start);
    p.add(end);
    if (speaker_list.get(this.get_sp(speaker_ID)) == null) {
      return;
    }
    for (Speaker sp : speaker_list.keySet()) {
      if (speaker_ID == sp.getUserId()) {
        ArrayList<ArrayList<Time>> tmp = (ArrayList<ArrayList<Time>>) speaker_list.get(sp).clone();
        for (ArrayList<Time> o : tmp) {
          if (o.equals(p)) {
            speaker_list.get(sp).remove(p);
          }
        }
      }
    }
  }

  /**
   * This method returns a HashMap of all speakers' speakerId to their event schedule.
   *
   * @return HashMap of all speakers' speakerId to their event schedule
   */
  public HashMap<Integer, ArrayList<ArrayList<Time>>> getSpeakerList() {

    HashMap<Integer, ArrayList<ArrayList<Time>>> tmp = new HashMap<>();
    for (Speaker sp : speaker_list.keySet()) {
      tmp.put(sp.getUserId(), speaker_list.get(sp));
    }
    return tmp;

  }

  /**
   * This method return the Speaker with given speakerId in the system or print "There is no speaker
   * in the system with that ID" if no speaker in the system has the given speakerId.
   *
   * @param sp_ID
   * @return Speaker with given Id or null if not exist
   */
  public Speaker get_sp(int sp_ID) {
    HashMap<Integer, Speaker> tmp = new HashMap<>();
    try {
      for (Speaker sp : speaker_list.keySet()) {
        tmp.put(sp.getUserId(), sp);
      }
      return tmp.get(sp_ID);
    } catch (NullPointerException e) {
      System.out.println("There is no speaker in the system with that ID");
    }
    return null;
  }

  /**
   * Return the string representation of the speaker with given speakerId in the system or print
   * "There is no speaker in the system with that ID" if not exist.
   *
   * @param sp_ID
   * @return string representation of Speaker with given Id or null if not exist
   */
  public String get_sp_info(int sp_ID) {
    HashMap<Integer, String> tmp = this.get_speakers_info();
    try {
      return tmp.get(sp_ID);
    } catch (NullPointerException e) {
      System.out.println("There is no speaker in the system with that ID");
    }
    return null;
  }

  /**
   * This method return a HashMap of all speaker's speakerId to their string representation.
   *
   * @return HashMap of all speaker's speakerId to their string representation
   */
  public HashMap<Integer, String> get_speakers_info() {
    HashMap<Integer, String> tmp = new HashMap<>();
    for (Speaker sp : speaker_list.keySet()) {
      tmp.put(sp.getUserId(), sp.toString());
    }
    return tmp;
  }

  /**
   * This method checks is the email is valid.
   *
   * @param email
   * @return boolean whether email is valid or not.
   */
  private boolean isValidEmail(String email) {
    // Create a regular expression format for a valid email
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{0,9}$";
    //Check if the email address matches the regex format
    Pattern emailPat = Pattern.compile(emailRegex);
    if (email == null) {
      return false;
    }

    return emailPat.matcher(email).matches();
  }

  /**
   * This method checks is the password is valid.
   *
   * @param password
   * @return boolean whether password is valid or not.
   */
  private boolean isValidPassword(String password) {
    return !password.isEmpty() && !password.contains(" ");
  }

  /**
   * This method checks is the phone is valid.
   *
   * @param phone
   * @return boolean whether phone is valid or not.
   */
  private boolean isValidPhone(String phone) {
    return phone.matches("^\\(?([0-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$");
  }
}
