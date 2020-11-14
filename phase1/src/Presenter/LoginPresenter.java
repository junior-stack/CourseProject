package Presenter;

import UseCase.UserAccountManager;

public class LoginPresenter {
    private UserAccountManager uam;
    public LoginPresenter(UserAccountManager uam){this.uam = uam;}

    public String getUserInfo(String email){return uam.getUserInfo(email);}
    public String getUserIdenity(String email) {return uam.getUserIdentity(email);
    }

}
