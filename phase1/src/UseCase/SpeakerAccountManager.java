package UseCase;

import Entity.Speaker;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/**
 * A class representing a SpeakerAccountManager
 * @author Ziwei Jia & Yufei Wang
 * @version 1.0
 */
public class SpeakerAccountManager {

  public static List<Speaker> speakerList;

  /**
   * Create a UserAccountManager with given speakerList.
   * @param speakerList
   */
  public SpeakerAccountManager(List<Speaker> speakerList) {
    SpeakerAccountManager.speakerList = speakerList;
  }

  /**
   * This method returns a List of strings representing all the speakers in the speakerList.
   * @return List of strings representing all the speakers in the speakerList
   */
  public List getSpeakerList() {
    List speakers = new ArrayList();
    for (Speaker i : speakerList) {
      speakers.add(i.toString());
    }
    return speakers;
  }

  /**
   * This method creates an speaker with giving username, password, phone and email.
   * @param username
   * @param password
   * @param phone
   * @param email
   * @return boolean whether speaker is created or not.
   */
  public boolean createSpeaker(String username, String password, String phone, String email) {

    if (!this.isValidEmail(email)) {
      System.out.println("The email address is invalid");
      return false;
    }
    if (!this.isValidPassword(password)) {
      System.out.println("Password cannot contain space nor be blank");
      return false;
    }
    if (!this.isValidPhone(phone)) {
      System.out.println("Phone number should only contain numeric characters");
      return false;
    }

    Speaker newSpeaker = new Speaker(username, password, phone, email);
    speakerList.add(newSpeaker);
    UserAccountManager.userList.add(newSpeaker);
    System.out.println("The Speaker account has been successfully created");
    return true;
  }

  /**
   * This method checks is the email is valid.
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
   * @param password
   * @return boolean whether password is valid or not.
   */
  private boolean isValidPassword(String password) {
    return !password.isEmpty() && !password.contains(" ");
  }

  /**
   * This method checks is the phone is valid.
   * @param phone
   * @return boolean whether phone is valid or not.
   */
  private boolean isValidPhone(String phone) {
    return phone.matches("^\\(?([0-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$");
  }


}