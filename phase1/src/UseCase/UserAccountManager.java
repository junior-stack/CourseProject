package UseCase;

import Entity.Attendee;
import Entity.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


public class UserAccountManager {

  public static List<User> userList;

  public UserAccountManager(List<User> userList) {

    UserAccountManager.userList = userList;

  }

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

  private boolean isValidPassword(String password) {
    return !password.isEmpty() && !password.contains(" ");
  }

  private boolean isValidPhone(String phone) {
    return phone.matches("^\\(?([0-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$");
  }


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

  public List<String> getUserList() {
    List users = new ArrayList();
    for (User i : userList) {
      users.add(i.toString());
    }
    return users;


  }

  public String getUserIdentity(String email) {
    for (User i : userList) {
      if (i.getEmail().equals(email)) {
        return i.getIdentity();
      }
    }
    return "User does not exist";
  }

  public static List<String> getAllEmails() {
    ArrayList<String> emailCollection = new ArrayList<String>();
    for (User user : userList) {
      emailCollection.add(user.getEmail());
    }
    return emailCollection;
  }

  public String getUserInfo(String email) {
    for (User i : userList) {
      if (i.getEmail().equals(email)) {
        return i.toString();
      }
    }
    return "User does not exist";
  }

  public User get_single_user(Integer user_id) {
    for (User u : userList) {
      if (u.getUserId() == user_id) {
        return u;
      }
    }
    return null;
  }
  public void setNewCounter(int newcounter){
    User.setCounter(newcounter);
  }

  public Integer get_user_id(String email){
    for(User u: userList){
      if(u.getEmail().equals(email)){
        return u.getUserId();
      }
    }
    return null;
  }
}