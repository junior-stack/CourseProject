package Controller;

import Entity.Organizer;
import Entity.Speaker;
import UseCase.OrganizerAccountManager;
import UseCase.SpeakerAccountManager;
import UseCase.UserAccountManager;

public class UserAccountsController {
  private static UserAccountManager userAccountManager;
  private static SpeakerAccountManager speakerAccountManager;

  public UserAccountsController(UserAccountManager userAccountManager, SpeakerAccountManager speakerAccountManager) {
    UserAccountsController.userAccountManager = userAccountManager;
    UserAccountsController.speakerAccountManager = speakerAccountManager;
  }

  public boolean create(String identity, String username, String password, String phone, String email) {
    if (String identity = "Attendee";){
      return userAccountManager.createAttendee(username, password, phone, email); }
    if (String identity = "Speaker";){
      return speakerAccountManager.createSpeaker((username, password, phone, email); }
  }

}

  public boolean exist(String email){
    return userAccountManager.existingUser(email);
  }

  public boolean verify(String email, String password) {
    return userAccountManager.verifyUser(email, password);
  }

  public String getIdentity(String email){ return userAccountManager.getUserIdentity(email);
  }

  public UserAccountManager getUserAccountManager() {
    return userAccountManager;
  }
}
