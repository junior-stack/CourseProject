package com.group0014.iconference.Presenter;

import com.group0014.iconference.UseCase.UserAccountManager;

import java.util.List;


public class LoginPresenter {
  private UserAccountManager uam;

  public LoginPresenter(UserAccountManager uam){
    this.uam = uam;

  }


  public String getUserInfo(String email){return uam.getUserInfo(email);}
  public String getUserIdenity(String email) {return uam.getUserIdentity(email);}

  public List allUserInfo(){return uam.getUserList();}

  public List allSpeakerInfo(){return uam.getSpeakerList();}

}

