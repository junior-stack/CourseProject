package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SignUpController;
import Controller.UserAccountsController;

import javax.swing.*;

public class AttendeeMenu extends JFrame {

    private String userEmail;
    JPanel attendeePanel;
    JLabel hintLabel1;
    JButton viewAllEventsButton;
    JButton viewMyEventsButton;
    JButton viewMyMessagesButton;
    JButton logoutButton;
    LoginFacade loginFacade;
    MessageController msgController;
    SignUpController signUpController;
    UserAccountsController userAccountsController;

    public AttendeeMenu(String email, LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
        MessageController msgController = new MessageController(email, loginFacade.getUserIdentity(email));
        attendeePanel = new JPanel();

        hintLabel1 = new JLabel("Click on the following buttons to perform an action");
        viewAllEventsButton = new JButton("Display All Events");
        viewMyEventsButton = new JButton("Display My Events");
        viewMyMessagesButton = new JButton("Display My Messages");
        logoutButton = new JButton("Logout");

        attendeePanel.add(hintLabel1);
        attendeePanel.add(viewAllEventsButton);
        attendeePanel.add(viewMyEventsButton);
        attendeePanel.add(viewMyMessagesButton);
        attendeePanel.add(logoutButton);


    }
}
