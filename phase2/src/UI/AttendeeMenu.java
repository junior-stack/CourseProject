package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AttendeeMenu extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JButton viewMyMessage;
  JButton viewMyEvents;
  JButton viewAllEvents;
  JButton logout;

  public AttendeeMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {

    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel attendeePanel = new JPanel();
    viewAllEvents = new JButton("Display All Events");
    viewMyEvents = new JButton("Display My Events");
    viewMyMessage = new JButton("Display My Messages");
    logout = new JButton("Logout");
    attendeePanel.add(viewAllEvents);
    attendeePanel.add(viewMyEvents);
    attendeePanel.add(viewMyMessage);
    attendeePanel.add(logout);

    viewMyEvents.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AttendeeMenu.this.setVisible(false);
        JFrame myEventsMenu = new MyEventsMenu(email, loginFacade, schedulerController,
            signUpController, messageController);
        myEventsMenu.setVisible(true);
      }
    });

    logout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AttendeeMenu.this.setVisible(false);
        JFrame loginMenu = new LoginMenu(loginFacade);
        loginMenu.setVisible(true);
      }
    });

    attendeePanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    attendeePanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(attendeePanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Attendee Menu");
    this.setResizable(false);
  }
}
