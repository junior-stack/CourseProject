package Controller;


import UseCase.SpeakerAccountManager;
import UseCase.UserAccountManager;

public class UserAccountsController {
  private static UserAccountManager userAccountManager;
  private static SpeakerAccountManager speakerAccountManager;

  public UserAccountsController(UserAccountManager userAccountManager, SpeakerAccountManager speakerAccountManager) {
    UserAccountsController.userAccountManager = userAccountManager;
    UserAccountsController.speakerAccountManager = speakerAccountManager;
  }

  public void createAttendee(String username, String password, String phone, String email) {

    userAccountManager.createAttendee(username, password, phone, email);

  }
  public void createSpeaker(String username, String password, String phone, String email) {

    speakerAccountManager.createSpeaker(username, password, phone, email);

  }

  public boolean verify(String email, String password) {
    return userAccountManager.verifyUser(email, password);
  }

}
