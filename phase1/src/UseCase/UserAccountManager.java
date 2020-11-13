package UseCase;

import Entity.Attendee;
import Entity.User;
import Entity.Organizer;

import java.util.ArrayList;
import java.util.List;

public class UserAccountManager {

  public static List<User> userList;

  public UserAccountManager(List<User> userList){UserAccountManager.userList = userList;}

  public void createAttendee(String username, String password, String phone, String email){
    if (!(existingUser(email))){userList.add(new Attendee(username, password, phone, email));}
  }

  public boolean existingUser(String email){
    for (User i:userList){
      if (i.getEmail().equals(email)){return true;}
    }
    return false;
  }

  public boolean verifyUser(String username, String password){
    for (User i:userList){
      if ((i.getUsername().equals(username))&&i.getPassword().equals(password)){return true;}
    }
    return false;
  }

  public String getUserIdentity(String username){
    for (User i:userList){
      if (i.getUsername().equals(username)){return i.getIdentity();}
    }
    return "User does not exist";
  }
}

