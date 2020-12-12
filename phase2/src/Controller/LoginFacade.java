package Controller;

import Dao.UserDao;
import Presenter.LoginPresenter;
import UseCase.UserAccountManager;
import java.util.List;

/**
 * This class represents an LoginFacade.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class LoginFacade {

  private final UserAccountsController uac;
  private final LoginPresenter lp;
  private final UserAccountManager uam;

  /**
   * Constructor of LoginFacade
   */
  public LoginFacade() {
    List<Entity.User> users = UserDao.getAll();
    uam = new UserAccountManager(users);
    uac = new UserAccountsController(uam);
    lp = new LoginPresenter(uam);

    uam.setNewCounter(users.size());

  }

  /**
   * This method creates a account for the attendee.
   *
   * @param username the username for the account
   * @param password the password for the account
   * @param phone    the phone number for the account
   * @param email    the email address of the account
   * @return boolean and signal message whether the registration succeeds.
   */
  public boolean register(String username, String password, String phone, String email) {
    return uac.createAttendee(username, password, phone, email);
  }

  /**
   * This method create a account for speaker
   * @param username the username for the account
   * @param password the password for the account
   * @param phone the phone number for the account
   * @param email the email address of the account
   * @return
   */
  public boolean createspeaker(String username, String password, String phone, String email) {
    return uac.createSpeaker(username, password, phone, email);
  }

  /**
   * * This method create a account for Organizer
   * @param username the username for the account
   * @param password the password for the account
   * @param phone the phone number for the account
   * @param email the email address of the account
   * @return
   */
  public boolean createOrganizer(String username, String password, String phone, String email) {
    return uac.createOrganizer(username, password, phone, email);
  }

  /**
   * This method enable user to login to the system.
   *
   * @param password the password for the account
   * @param email    the email address of the account
   * @return boolean and signal message whether the login process succeeds
   */
  public boolean login(String email, String password) {
    return uac.verify(email, password);
  }

  /**
   * This method returns all information of a user
   *
   * @param email the email address of the account
   * @return user information
   */
  public String getUserInfo(String email) {
    return lp.getUserInfo(email);
  }

  /**
   * This method returns the identity (organizer, speaker, attendee) of the user.
   *
   * @param email the email address of the account
   * @return user identity (organizer, speaker, attendee)
   */
  public String getUserIdentity(String email) {
    return lp.getUserIdenity(email);
  }

  /**
   * This method returns all information of all user
   *
   * @return all user information
   */
  public List<String> getallUsers() {
    return lp.allUserInfo();
  }

  /**
   * This method returns all information of all speakers
   *
   * @return all speaker information
   */
  public List getallSpeakers() {
    return lp.allSpeakerInfo();
  }

  /**
   * Getter to get the UserAccountManager
   * @return  UserAccountManager
   */
  public UserAccountManager getUam() {
    return uam;
  }


  /**
   * Exit the login
   */
  public void exit() {
  }

  /**
   * Save registration infor to database
   */
  public void save() {
    UserAccountManager.save();
  }

}
