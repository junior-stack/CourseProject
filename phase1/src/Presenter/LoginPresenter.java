package Presenter;

import UseCase.SpeakerAccountManager;
import UseCase.UserAccountManager;

import java.util.List;


public class LoginPresenter {

  private UserAccountManager uam;
  private SpeakerAccountManager sam;

  public LoginPresenter(UserAccountManager uam, SpeakerAccountManager sam) {
    this.uam = uam;
    this.sam = sam;
  }


  public String getUserInfo(String email) {
    return uam.getUserInfo(email);
  }

  public String getUserIdenity(String email) {
    return uam.getUserIdentity(email);
  }

  public List allUserInfo() {
    return uam.getUserList();
  }

  public List allSpeakerInfo() {
    return sam.getSpeakerList();
  }

}

