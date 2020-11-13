package Controller;

import UseCase.UserAccountManager;

public class UserAccountsController {
  public UserAccountsController(UserAccountManager userAccountManager){this.userAccountManager = userAccountManager;}

  public void createAttendee(String username, String password, String phone, String email){
    userAccountManager.createAttendee(username, password, phone, email);
  }

  public boolean verify(String username, String password){return userAccountManager.verifyUser(username,password);}
}
