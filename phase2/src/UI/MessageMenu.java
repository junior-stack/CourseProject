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

public class MessageMenu extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JButton viewMyMessage;
  JButton sendMessage;
  JButton backButton;
  JPanel messageMenuPanel;


  /**
   * Message menu for users, user can view message, send message here.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public MessageMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    messageMenuPanel = new JPanel();
    messageMenuPanel.setLayout(new GridLayout(10, 1));
    viewMyMessage = new JButton("View my messages");
    sendMessage = new JButton("Send messages");
    backButton = new JButton("Back");
    messageMenuPanel.add(backButton);
    messageMenuPanel.add(viewMyMessage);
    messageMenuPanel.add(sendMessage);

    viewMyMessage.addActionListener(e -> {
      MessageMenu.this.setVisible(false);
      JFrame viewMessageFrame = new ViewMessageFrame(email, loginFacade, schedulerController,
          signUpController, messageController);
      viewMessageFrame.setVisible(true);
    });

    sendMessage.addActionListener(e -> {
      MessageMenu.this.setVisible(false);
      if (loginFacade.getUserIdentity(email).equals("Attendee")) {
        JFrame attendeeSendMessageFrame = new AttendeeSendMessageFrame(email, loginFacade,
            schedulerController, signUpController, messageController);
        attendeeSendMessageFrame.setVisible(true);
      }
      if (loginFacade.getUserIdentity(email).equals("Speaker")) {
        JFrame speakerSendMessageFrame = new SpeakerSendMessageFrame(email, loginFacade,
            schedulerController, signUpController, messageController);
        speakerSendMessageFrame.setVisible(true);
      }
      if (loginFacade.getUserIdentity(email).equals("Organizer")) {
        JFrame organizerSendMessageFrame = new OrganizerSendMessageFrame(email, loginFacade,
            schedulerController, signUpController, messageController);
        organizerSendMessageFrame.setVisible(true);
      }
    });
    backButton.addActionListener(e -> {
      MessageMenu.this.setVisible(false);
      AllEventsMenu.back_helper2(loginFacade, email,
          schedulerController, signUpController, messageController);
    });
    messageMenuPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    messageMenuPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(messageMenuPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Message Menu");
    this.setResizable(false);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }

}
