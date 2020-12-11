package Presenter;

import UseCase.UserAccountManager;
import java.util.List;

/**
 * class representing LoginPresenter.
 * @author William Ji
 */
/**
 * class for presenting login information
 */
public class LoginPresenter {

  private final UserAccountManager uam;

  /**
   * constructor for LoginPresenter.
   * @param uam
   */
  public LoginPresenter(UserAccountManager uam) {
    this.uam = uam;

  }


  /**
   * gets userInfo of the user with given email.
   * @param email
   * @return string representing this user
   */
  public String getUserInfo(String email) {
    return uam.getUserInfo(email);
  }

  /**
   * gets identity of user with given email.
   * @param email
   * @return string representing this user's identity
   */
  public String getUserIdenity(String email) {
    return uam.getUserIdentity(email);
  }

  /**
   * gets a list of all user's string representation.
   * @return list
   */
  public List<String> allUserInfo() {
    return uam.getUserList();
  }

  public List allSpeakerInfo() {
    return uam.getSpeakerList();
  }

}

