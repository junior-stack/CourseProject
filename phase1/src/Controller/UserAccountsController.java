package Controller;


import UseCase.SpeakerAccountManager;
import UseCase.UserAccountManager;


public class UserAccountsController {

  private UserAccountManager userAccountManager;
  private SpeakerAccountManager speakerAccountManager;


  public UserAccountsController(UserAccountManager userAccountManager,
      SpeakerAccountManager speakerAccountManager) {
    this.userAccountManager = userAccountManager;
    this.speakerAccountManager = speakerAccountManager;

  }

  /**
   * This method check whether there's a user existed with the given email to make sure that the
   * email is unique
   *
   * @return boolean whether a account with the email exists in the system.
   */
  public boolean existingUser(String email) {
    for (String i : UserAccountManager.getAllEmails()) {
      if (i.equals(email)) {
        return true;
      }
    }
    return false;
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
    if (existingUser(email)) {
      return false;
    }
    return userAccountManager.createAttendee(username, password, phone, email);
  }

  /**
   * This method register a speaker account for the user.
   *
   * @param username the username for the account
   * @param password the password for the account
   * @param phone    the phone number for the account
   * @param email    the email address of the account
   * @return boolean and signal message whether the creation succeeds.
   */
  public void createSpeaker(String username, String password, String phone, String email) {
    if (existingUser(email)) {
      System.out.println("Email has been registed, please use different email");
    } else {
      speakerAccountManager.createSpeaker(username, password, phone, email);
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
    return userAccountManager.verifyUser(email, password);
  }


}
