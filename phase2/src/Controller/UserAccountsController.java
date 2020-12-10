package Controller;

import UseCase.AccountManager;

public class UserAccountsController {

  private final AccountManager am;


  public UserAccountsController(AccountManager am) {
    this.am = am;

  }

  /**
   * This method register a attendee account for the user.
   *
   * @param username the username for the account
   * @param password the password for the account
   * @param phone    the phone number for the account
   * @param email    the email address of the account
   * @return boolean and signal message whether the creation succeeds.
   */
  public boolean createAttendee(String username, String password, String phone, String email) {
    if (am.existingUser(email)) {
      return false;
    } else {
      return am.createAttendee(username, password, phone, email);
    }

  }

  /**
   * This method register a attendee account for the user.
   *
   * @param username the username for the account
   * @param password the password for the account
   * @param phone    the phone number for the account
   * @param email    the email address of the account
   * @return boolean and signal message whether the creation succeeds.
   */
  public boolean createSpeaker(String username, String password, String phone, String email) {
    if (am.existingUser(email)) {
      return false;
    } else {

      return am.createSpeaker(username, password, phone, email);
    }

  }

  public boolean createOrganizer(String username, String password, String phone, String email) {
    if (am.existingUser(email)) {
      return false;
    } else {

      return am.createOrganizer(username, password, phone, email);
    }

  }


  /**
   * This method checks email and password match the account information
   *
   * @param password the password for the account
   * @param email    the email address of the account
   * @return boolean and signal message whether the verification succeeds.
   */
  public boolean verify(String email, String password) {
    return am.verifyUser(email, password);
  }


}
