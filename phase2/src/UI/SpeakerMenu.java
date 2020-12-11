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

public class SpeakerMenu extends JFrame {

  final int MENU_HEIGHT = 400;
  final int MENU_WIDTH = 500;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JButton logout;
  JButton viewMyMessage;
  JButton viewMyEvents;

  /**
   * Speaker's menu, speaker can view signed up events, send message to one or more people, and view
   * all received messages.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public SpeakerMenu(String email, LoginFacade loginFacade, SchedulerController schedulerController,
      SignUpController
          signUpController, MessageController messageController) {

    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel speakerPanel = new JPanel();
    speakerPanel.setLayout(new GridLayout(10, 1));
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
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }
}