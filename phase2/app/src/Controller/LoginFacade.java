package com.group0014.iconference.Controller;

import com.group0014.iconference.Gateway.Igateway;
import com.group0014.iconference.Gateway.InitializeOrganizers;
import com.group0014.iconference.Gateway.UserDataAccess;
import com.group0014.iconference.Presenter.LoginPresenter;
import com.group0014.iconference.UseCase.UserAccountManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an LoginFacade.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class LoginFacade {
    private UserAccountsController uac;
    private LoginPresenter lp;
    private UserAccountManager uam;

    private InitializeOrganizers io = new InitializeOrganizers();
    private Igateway ig = new UserDataAccess();

    public LoginFacade() {
        ArrayList users = ig.read();
        uam = new UserAccountManager(users);
        uam.createOrganizer(io.initialize());
        uam.setNewCounter(users.size());
        uac = new UserAccountsController(uam);
        lp = new LoginPresenter(uam);
    }

    public ArrayList readusers() {
        return ig.read();
    }

    /**
     * This method register a account for the user.
     *
     * @param username the username for the account
     * @param password the password for the account
     * @param phone    the phone number for the account
     * @param email    the email address of the account
     * @return boolean and signal message whether the registration succeeds.
     */
    public boolean register(String username, String password, String phone, String email) {
        return uac.createAttendee(username, password, phone, email);
    }

    public boolean createspeaker(String username, String password, String phone, String email) {
        return uac.createSpeaker(username, password, phone, email);
    }

    /**
     * This method enable user to login to the system.
     *
     * @param password the password for the account
     * @param email    the email address of the account
     * @return boolean and signal message whether the login process succeeds
     */
    public boolean login(String email, String password) {
        return uac.verify(email, password);
    }

    /**
     * This method returns all information of a user
     *
     * @param email the email address of the account
     * @return user information
     */
    public String getUserInfo(String email) {
        return lp.getUserInfo(email);
    }

    /**
     * This method returns the identity (organizer, speaker, attendee) of the user.
     *
     * @param email the email address of the account
     * @return user identity (organizer, speaker, attendee)
     */
    public String getUserIdentity(String email) {
        return lp.getUserIdenity(email);
    }

    /**
     * This method returns all information of all user
     *
     * @return all user information
     */
    public List getallUsers() {
        return lp.allUserInfo();
    }

    /**
     * This method returns all information of all speakers
     *
     * @return all speaker information
     */
    public List getallSpeakers() {
        return lp.allSpeakerInfo();
    }

    public UserAccountManager getUam() {
        return uam;
    }

    public void save() {
        ig.write(UserAccountManager.userList);
    }

}
