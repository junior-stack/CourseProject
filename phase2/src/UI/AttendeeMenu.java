package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Haohua Ji
 **/

public class AttendeeMenu extends JFrame {

  final int MENU_HEIGHT = 400;
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

  /**
   * Attendee's menu, attendee can view all available events, sign up pr sign off events, send
   * message to people and view any messages received.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public AttendeeMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {

    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel attendeePanel = new JPanel();
    attendeePanel.setLayout(new GridLayout(10, 1));
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
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }
}
