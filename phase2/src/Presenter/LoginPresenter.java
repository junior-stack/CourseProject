package Presenter;

import UseCase.UserAccountManager;
import java.util.List;


public class LoginPresenter {

  private final UserAccountManager uam;

  /**
   * Constructor of LoginPresenter
   * @param uam useraccount system
   */
  public LoginPresenter(UserAccountManager uam) {
    this.uam = uam;

  }


  /**
   *  Get the user information from email
   * @param email email address of the user
   * @return the string information of this user
   */
  public String getUserInfo(String email) {
    return uam.getUserInfo(email);
  }

  /**
   * Get the user's identity from the user's email
   * @param email the email of the user
   * @return the user's identity
   */
  public String getUserIdenity(String email) {
    return uam.getUserIdentity(email);
  }

  /**
   * Return all users' string information
   * @return list of string
   */
  public List<String> allUserInfo() {
    return uam.getUserList();
  }

  /**
   *  Return the list of speakers' information
   * @return list<String> list of speakers' information
   */
  public List allSpeakerInfo() {
    return uam.getSpeakerList();
  }

}

