package Controller;

import UseCase.AccountManager;

/**
 * class of UserAccounts controller
 */
public class UserAccountsController {

  private final AccountManager am;


  /**
   * constructor of useraccountscontroller
   * @param am
   */
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

  /**
   * This method register a organzier account for the user.
   * @param username
   * @param password
   * @param phone
   * @param email
   * @return
   */
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
