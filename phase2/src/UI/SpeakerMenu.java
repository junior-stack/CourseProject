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

public class SpeakerMenu extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JButton logout;
  JButton viewMyMessage;
  JButton viewMyEvents;

  public SpeakerMenu(String email, LoginFacade loginFacade, SchedulerController schedulerController,
      SignUpController
          signUpController, MessageController messageController) {

    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel speakerPanel = new JPanel();
    speakerPanel.setLayout(new GridLayout(3, 1));
    viewMyEvents = new JButton("Display my events");
    viewMyMessage = new JButton("Display my messages");
    logout = new JButton("Logout");
    speakerPanel.add(viewMyEvents);
    speakerPanel.add(viewMyMessage);
    speakerPanel.add(logout);

    viewMyEvents.addActionListener(e -> {
      SpeakerMenu.this.setVisible(false);
      JFrame myEventsMenu = new MyEventsMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      myEventsMenu.setVisible(true);
    });

    viewMyMessage.addActionListener(e -> {
      SpeakerMenu.this.setVisible(false);
      JFrame myMessageMenu = new MessageMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      myMessageMenu.setVisible(true);
    });

    logout.addActionListener(e -> {
      SpeakerMenu.this.setVisible(false);
      System.exit(0);
    });

    speakerPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    speakerPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(speakerPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Speaker Menu");
    this.setResizable(false);
  }
}