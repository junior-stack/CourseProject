package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by Haohua Ji
 **/

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
    attendeePanel.setLayout(new GridLayout(4, 1));
    viewAllEvents = new JButton("Display All Events");
    viewMyEvents = new JButton("Display My Events");
    viewMyMessage = new JButton("Display My Messages");
    logout = new JButton("Logout");
    attendeePanel.add(viewAllEvents);
    attendeePanel.add(viewMyEvents);
    attendeePanel.add(viewMyMessage);
    attendeePanel.add(logout);

    viewAllEvents.addActionListener(e -> {
      AttendeeMenu.this.setVisible(false);
      JFrame allEventsMenu = new AllEventsMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      allEventsMenu.setVisible(true);
    });

    viewMyMessage.addActionListener(e -> {
      AttendeeMenu.this.setVisible(false);
      JFrame myMessageMenu = new MessageMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      myMessageMenu.setVisible(true);
    });

    viewMyEvents.addActionListener(e -> {
      AttendeeMenu.this.setVisible(false);
      JFrame myEventsMenu = new MyEventsMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      myEventsMenu.setVisible(true);
    });

    logout.addActionListener(e -> {
      AttendeeMenu.this.setVisible(false);
      System.exit(0);
    });

    attendeePanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    attendeePanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(attendeePanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Attendee Menu");
    this.setResizable(false);
  }
}
