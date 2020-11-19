package UseCase;

import Entity.Attendee;
import Entity.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A class representing a UserAccountManager.
 * @author Ziwei Jia & Yufei Wang
 * @version 1.0
 */
public class UserAccountManager {

  public static List<User> userList;

  /**
   * Create a UserAccountManager with given userList.
   * @param userList
   */
  public UserAccountManager(List<User> userList) {

    UserAccountManager.userList = userList;

  }

  /**
   * This method creates an attendee with giving username, password, phone and email.
   * @param username
   * @param password
   * @param phone
   * @param email
   * @return boolean whether attendee is created or not.
   */
  public boolean createAttendee(String username, String password, String phone, String email) {

    if (!this.isValidEmail(email)) {
      return false;
    }
    if (!this.isValidPassword(password)) {
      return false;
    }
    if (!this.isValidPhone(phone)) {
      return false;
    }
    userList.add(new Attendee(username, password, phone, email));
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


  /**
   * This method checks if the pair of email and password already existed. And print
   * "Provided Information Match/Not Match With Registered"
   * @param email
   * @param password
   * @return boolean whether the pair of email and password exist or not
   */
  public boolean verifyUser(String email, String password) {
    for (User i : userList) {
      if ((i.getEmail().equals(email)) && i.getPassword().equals(password)) {
        System.out.println("Provided Information Match With Registered");
        return true;
      }
    }
    System.out.println("Provided Information Does Not Match With Registered");
    return false;
  }

  /**
   * This method returns a List of strings representing all the users in the userList.
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
   * This method return the identity of the user with given email, or return "User does not exist"
   * when no user is registered with given email.
   * @param email
   * @return Identity related to the email or "User does not exist"
   */
  public String getUserIdentity(String email) {
    for (User i : userList) {
      if (i.getEmail().equals(email)) {
        return i.getIdentity();
      }
    }
    return "User does not exist";
  }

  /**
   * This method return a List of alls email in the UserAccountManager.
   * @return List of all emails
   */
  public static List<String> getAllEmails() {
    ArrayList<String> emailCollection = new ArrayList<String>();
    for (User user : userList) {
      emailCollection.add(user.getEmail());
    }
    return emailCollection;
  }

  /**
   * This method return a string representing the user with given email. Return
   * "User does not exist" when no users is registered with given email.
   * @param email
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
   * return a User with given userId from the UserAccountManager's user list or return nothing if
   * there is no such user.
   * @param user_id
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
   * This method sets up a new counter.
   * @param newcounter
   */
  public void setNewCounter(int newcounter){
    User.setCounter(newcounter);
  }

  /**
   * This method returns userId of the User with given email in this UserAccountManager,
   * or return null when there is no such user
   * @param email
   * @return
   */
  public Integer get_user_id(String email){
    for(User u: userList){
      if(u.getEmail().equals(email)){
        return u.getUserId();
      }
    }
    return null;
  }
}