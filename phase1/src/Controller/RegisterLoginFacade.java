package Controller;


import Gateway.Igateway;
import Gateway.InitializeOrganizers;
import Gateway.UserDataAccess;
import Presenter.LoginPresenter;
import UseCase.OrganizerAccountManager;
import UseCase.SpeakerAccountManager;
import UseCase.UserAccountManager;


import java.util.ArrayList;
import java.util.List;

public class RegisterLoginFacade {
  private UserAccountsController uac;
  private SpeakerAccountManager sam;
  private OrganizerAccountManager oam;
  private LoginPresenter lp;
  private UserAccountManager uam;

  private InitializeOrganizers io = new InitializeOrganizers();
  private Igateway ig = new UserDataAccess();

  public RegisterLoginFacade(){
    ArrayList users = ig.read();
    oam = new OrganizerAccountManager();
    List newOrganizer = oam.getOrganizerList(io.initialize());
    if (newOrganizer !=null) {users.addAll(newOrganizer);}


    sam = new SpeakerAccountManager()


  }




  }





  public RegisterLoginFacade(U




}
