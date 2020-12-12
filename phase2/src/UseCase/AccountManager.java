package UseCase;

import java.util.regex.Pattern;

/**
 * AccountManager Interface.
 *
 * @author Haohua Ji
 */
public interface AccountManager {

  /**
   * This method checks is the email is valid.
   *
   * @param email email to check
   * @return boolean whether email is valid or not.
   */
  default boolean isValidEmail(String email) {
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
   * @param password password to check
   * @return boolean whether password is valid or not.
   */
  default boolean isValidPassword(String password) {
    return !password.isEmpty() && !password.contains(" ");
  }

  /**
   * This method checks is the phone is valid.
   *
   * @param phone phone to check
   * @return boolean whether phone is valid or not.
   */
  default boolean isValidPhone(String phone) {
    return phone.matches("^\\(?([0-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$");
  }


  /**
   * This method check if the user with this email exists
   *
   * @param email email to check
   * @return boolean of whether the user exist
   */
  boolean existingUser(String email);

  /**
   * This method create new Attendee
   *
   * @param username username of the Attendee
   * @param password password of the Attendee
   * @param phone    phone of the Attendee
   * @param email    email of the Attendee
   * @return boolean of whether the Attendee is created
   */
  boolean createAttendee(String username, String password, String phone, String email);

  /**
   * This method create new Organizer
   *
   * @param username username of the Organizer
   * @param password password of the Organizer
   * @param phone    phone of the Organizer
   * @param email    email of the Organizer
   * @return boolean of whether the Organizer is created
   */
  boolean createOrganizer(String username, String password, String phone, String email);

  /**
   * This method create new Speaker
   *
   * @param username username of the Speaker
   * @param password password of the Speaker
   * @param phone    phone of the Speaker
   * @param email    email of the Speaker
   * @return boolean of whether the Speaker is created
   */
  boolean createSpeaker(String username, String password, String phone, String email);

  /**
   * This method cheak if the email and password matches.
   *
   * @param email    email to check
   * @param password password to check
   * @return boolean of whether the email and password matches
   */
  boolean verifyUser(String email, String password);
}
