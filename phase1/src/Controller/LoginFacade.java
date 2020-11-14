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

public class LoginFacade {
    private UserAccountsController uac;
    private SpeakerAccountManager sam;
    private OrganizerAccountManager oam;
    private LoginPresenter lp;
    private UserAccountManager uam;

    private InitializeOrganizers io = new InitializeOrganizers();
    private Igateway ig = new UserDataAccess();

    public LoginFacade() {
        ArrayList users = ig.read();
        oam = new OrganizerAccountManager();
        List newOrganizer = oam.getOrganizerList(io.initialize());
        if (newOrganizer != null) {
            users.addAll(newOrganizer);
        }
        List existingSpeakers = oam.filterexistingspeaker(users);
        sam = new SpeakerAccountManager(existingSpeakers);

        uam = new UserAccountManager(users);
        uac = new UserAccountsController(uam, sam);
        lp = new LoginPresenter(uam);
    }

    public ArrayList readusers() {
        return ig.read();
    }

    public void register(String username, String password, String phone, String email) {
        uac.createAttendee(username, password, phone, email);
    }

    public boolean login(String email, String password) {
        return uac.verify(email, password);
    }

    public String getUserInfo(String email) {
        return lp.getUserInfo(email);
    }

    public String getUserIdentity(String email) {
        return lp.getUserIdenity(email);
    }

    public void save(){ig.write(UserAccountManager.userList);}

}
