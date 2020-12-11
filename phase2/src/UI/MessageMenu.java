package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by Haohua Ji
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


  public MessageMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    messageMenuPanel = new JPanel();
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
  }

}
