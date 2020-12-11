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

public class OrganizerMenu extends JFrame {

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
  JButton manageAccounts;
  JButton manageRooms;
  JButton logout;

  public OrganizerMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController,
      SignUpController signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel organizerPanel = new JPanel();
    organizerPanel.setLayout(new GridLayout(6, 1));
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
  }
}