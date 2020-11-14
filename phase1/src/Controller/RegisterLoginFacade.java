package Controller;

import Entity.Organizer;
import Entity.Speaker;
import Entity.User;
import UseCase.OrganizerAccountManager;
import UseCase.SpeakerAccountManager;
import UseCase.UserAccountManager;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSInput;

import java.util.List;

public class RegisterLoginFacade {
  private static UserAccountsController userAccountsController;
  private static SpeakerAccountManager speakerAccountManager;
  private static OrganizerAccountManager organizerAccountManager;

  public RegisterLoginFacade(UserAccountsController userAccountsController,
      SpeakerAccountManager speakerAccountManager,
      OrganizerAccountManager organizerAccountManager){


    List<User> userList = userAccountsController.getUserAccountManager().getUserList();
    List<User> speakerList = speakerAccountManager.getSpeakerList();
    List<User> organizerList = organizerAccountManager.getOrganizerList();

    List<String> emails = userAccountsController.getUserAccountManager().getAllEmails();

    for (User organizer : organizerList){
      if (!emails.contains(organizer.getEmail())){
        userList.add(organizer);
        emails.add(organizer.getEmail());
      }

      for (User speaker : speakerList){
        if (!emails.contains(speaker.getEmail())){
          userList.add(speaker);
          emails.add(speaker.getEmail());
        }
      }
    }

  }






}
