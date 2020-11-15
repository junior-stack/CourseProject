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

  public boolean existingUser(String email) {
    for (String i : UserAccountManager.getAllEmails()) {
      if (i.equals(email)) {
        return true;
      }
    }
    return false;
  }


  public boolean createAttendee(String username, String password, String phone, String email) {
    if (existingUser(email)) return false;
    return userAccountManager.createAttendee(username, password, phone, email);
  }

  public void createSpeaker(String username, String password, String phone, String email) {
    if (existingUser(email)) {
      System.out.println("Email has been registed, please use different email");
    } else {
      speakerAccountManager.createSpeaker(username, password, phone, email);
    }

  }

  public boolean verify(String email, String password) {
    return userAccountManager.verifyUser(email, password);
  }


}
