package UseCase;

import Dao.UserDao;
import Entity.Attendee;
import Entity.Organizer;
import Entity.Speaker;
import Entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing a UserAccountManager.
 *
 * @author Ziwei Jia & Yufei Wang
 * @version 1.0
 */

public class UserAccountManager implements AccountManager {

  public static List<User> userList;

  /**
   * Create a UserAccountManager with given userList.
   *
   * @param userList a List of User
   */

  public UserAccountManager(List<User> userList) {

    UserAccountManager.userList = userList;

  }

  /**
   * This method return a List of alls email in the UserAccountManager.
   *
   * @return List of all emails
   */
  public static ArrayList<String> getAllEmails() {
    ArrayList<String> emailCollection = new ArrayList();
    for (User user : userList) {
      emailCollection.add(user.getEmail());
    }
    return emailCollection;
  }

  /**
   * Based on given email, this methods return the User object
   *
   * @param email the email to be checked
   * @return User  User with the email
   */
  public static User getUserFromEmail(String email) {
    for (User u : userList) {
      if (u.getEmail().equals(email)) {
        return u;
      }
    }
    return null;
  }

  /**
   * Return the identity of the user with given email.
   *
   * @param email email to be checked
   * @return String  the identity of user with given email
   */
  public static String getEmailToIdentity(String email) {
    HashMap<String, String> emailToIdentity = new HashMap<>();
    for (User user : userList) {
      emailToIdentity.put(user.getEmail(), user.getType());
    }
    return emailToIdentity.get(email);
  }

  /**
   * Return the email of the user with given Id.
   *
   * @param Id the id to be checked
   * @return String  the email of the user with given id
   */
  public static String idToEmail(int Id) {
    for (User u : userList) {
      if (u.getUserId() == Id) {
        return u.getEmail();
      }
    }
    return "";
  }

  /**
   * Return an ArrayList of all email of all users with usertype as given.
   *
   * @param userType the user type we want to find all email
   * @return ArrayList  ArrayList of all email of all user with given usertype
   */
  public static ArrayList<String> getAllAvailableEmails(String userType) {
    ArrayList<String> allemails = new ArrayList<>();
    for (User u : userList) {
      if (u.getType().equals(userType)) {
        allemails.add(u.getEmail());
      }
    }
    return allemails;
  }

  /**
   * save userList to the database
   */
  public static void save() {
    UserDao.saveAll(userList);
  }

  /**
   * This method creates organizers by adding all the organizers from the list.
   *
   * @param username username of the Organizer
   * @param password password of the Organizer
   * @param phone    phone of the Organizer
   * @param email    email of the Organizer
   * @return boolean whether organizer is created or not.
   */
  @Override
  public boolean createOrganizer(String username, String password, String phone, String email) {
    if ((!this.isValidEmail(email)) || (!this.isValidPassword(password)) || (!this
        .isValidPhone(phone))) {
      return false;
    }
    userList.add(new Organizer(username, password, phone, email));
    return true;
  }

  /**
   * This method creates an attendee with giving username, password, phone and email.
   *
   * @param username username of the Attendee
   * @param password password of the Attendee
   * @param phone    phone of the Attendee
   * @param email    email of the Attendee
   * @return boolean whether attendee is created or not.
   */
  @Override
  public boolean createAttendee(String username, String password, String phone, String email) {

    if ((!this.isValidEmail(email)) || (!this.isValidPassword(password)) || (!this
        .isValidPhone(phone))) {
      return false;
    }
    userList.add(new Attendee(username, password, phone, email));
    return true;
  }

  /**
   * This method creates an speaker with giving username, password, phone and email.
   *
   * @param username username of the speaker
   * @param password password of the speaker
   * @param phone    phone of the speaker
   * @param email    email of the speaker
   * @return boolean whether speaker is created or not.
   */
  @Override
  public boolean createSpeaker(String username, String password, String phone, String email) {

    if ((!this.isValidEmail(email)) || (!this.isValidPassword(password)) || (!this
        .isValidPhone(phone))) {
      return false;
    }
    userList.add(new Speaker(username, password, phone, email));
    return true;
  }

  /**
   * This method checks if the pair of email and password already existed. And print "Provided
   * Information Match/Not Match With Registered"
   *
   * @param email    the email we want to check
   * @param password the password we want to check
   * @return boolean whether the pair of email and password exist or not
   */
  @Override
  public boolean verifyUser(String email, String password) {
    for (User i : userList) {
      if ((i.getEmail().equals(email)) && i.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns a List of strings representing all the users in the userList.
   *
   * @return List of strings representing all the users in the userList
   */
  public List<String> getUserList() {
    List users = new ArrayList();
    for (User i : userList) {
      users.add(i.toString());
    }
    return users;

  }

  /**
   * This method return a string representing the user with given email. Return "User does not
   * exist" when no users is registered with given email.
   *
   * @param email the email we want to check
   * @return a string representation of the user with given email or "User does not exist"
   */
  public String getUserInfo(String email) {
    for (User i : userList) {
      if (i.getEmail().equals(email)) {
        return i.toString();
      }
    }
    return "User does not exist";
  }

  /**
   * This method return the identity of the user with given email, or return "User does not exist"
   * when no user is registered with given email.
   *
   * @param email the email we want to check
   * @return String  identity related to the email or "User does not exist"
   */
  public String getUserIdentity(String email) {
    for (User i : userList) {
      if (i.getEmail().equals(email)) {
        return i.getType();
      }
    }
    return "User does not exist";
  }

  /**
   * This method verifies if given email has been registered, same email can only registered once.
   *
   * @param email the email we want to check
   * @return {@code True} if the user with given email exists
   */
  @Override
  public boolean existingUser(String email) {
    for (String i : UserAccountManager.getAllEmails()) {
      if (i.equals(email)) {
        return true;
      }
    }
    return false;
  }

  /**
   * return a User with given userId from the UserAccountManager's user list or return nothing if
   * there is no such user.
   *
   * @param user_id the user id we want to check
   * @return User with given userId or null
   */

  public User get_single_user(Integer user_id) {
    for (User u : userList) {
      if (u.getUserId() == user_id) {
        return u;
      }
    }
    return null;
  }

  /**
   * This method returns userId of the User with given email in this UserAccountManager, or return
   * null when there is no such user
   *
   * @param email email we want to check
   * @return Integer  id of the user with given email
   */
  public Integer get_user_id(String email) {
    for (User u : userList) {
      if (u.getEmail().equals(email)) {
        return u.getUserId();
      }
    }
    return null;
  }

  /**
   * This method sets up a new counter.
   *
   * @param newcounter an integer representing a new counter
   */
  public void setNewCounter(int newcounter) {
    User.setCounter(newcounter);
  }

  /**
   * This method returns a list of Speakers
   *
   * @return List  list of speakers
   */
  public List getSpeakerList() {
    ArrayList speakers = new ArrayList();
    if (userList != null && userList.size() > 0) {
      for (User i : userList) {
        if (i.getType().equals("Speaker")) {
          speakers.add(i);
        }
      }
    }
    return speakers;
  }
}