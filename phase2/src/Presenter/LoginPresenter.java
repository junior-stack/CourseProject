package Presenter;

import UseCase.UserAccountManager;
import java.util.List;


public class LoginPresenter {

  private final UserAccountManager uam;

  public LoginPresenter(UserAccountManager uam) {
    this.uam = uam;

  }


  public String getUserInfo(String email) {
    return uam.getUserInfo(email);
  }

  public String getUserIdenity(String email) {
    return uam.getUserIdentity(email);
  }

  public List<String> allUserInfo() {
    return uam.getUserList();
  }

  public List allSpeakerInfo() {
    return uam.getSpeakerList();
  }

}

