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

public class OrganizerMenu extends JFrame {

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
  JButton manageAccounts;
  JButton manageRooms;
  JButton logout;

  /**
   * Organizer's menu, organizer can view all events, sign up or sign off events, add or delete
   * events, edit events, view signed up events, view or send message, manage room and accounts,
   * etc.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public OrganizerMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController,
      SignUpController signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel organizerPanel = new JPanel();
    organizerPanel.setLayout(new GridLayout(10, 1));
    viewAllEvents = new JButton("Display All Events");
    viewMyEvents = new JButton("Display My Events");
    viewMyMessage = new JButton("Display My Messages");
    manageRooms = new JButton("Manage Rooms");
    manageAccounts = new JButton("Manage Accounts");
    logout = new JButton("Logout");
    organizerPanel.add(viewAllEvents);
    organizerPanel.add(viewMyEvents);
    organizerPanel.add(viewMyMessage);
    organizerPanel.add(manageAccounts);
    organizerPanel.add(manageRooms);
    organizerPanel.add(logout);

    viewAllEvents.addActionListener(e -> {
      OrganizerMenu.this.setVisible(false);
      JFrame myAllEvents = new AllEventsMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      myAllEvents.setVisible(true);
    });

    viewMyEvents.addActionListener(e -> {
      OrganizerMenu.this.setVisible(false);
      JFrame myEventsMenu = new MyEventsMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      myEventsMenu.setVisible(true);
    });

    viewMyMessage.addActionListener(e -> {
      OrganizerMenu.this.setVisible(false);
      JFrame myMessageMenu = new MessageMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      myMessageMenu.setVisible(true);
    });

    manageRooms.addActionListener(e -> {
      OrganizerMenu.this.setVisible(false);
      JFrame manageRoomsMenu = new ManageRoomMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      manageRoomsMenu.setVisible(true);
    });

    manageAccounts.addActionListener(e -> {
      OrganizerMenu.this.setVisible(false);
      JFrame manageAccountMenu = new ManageAccountMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      manageAccountMenu.setVisible(true);
    });

    logout.addActionListener(e -> {
      OrganizerMenu.this.setVisible(false);
      System.exit(0);
    });

    organizerPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    organizerPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(organizerPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Organizer Menu");
    this.setResizable(false);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }
}