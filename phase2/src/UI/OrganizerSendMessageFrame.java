package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is the controller for message system.
 *
 * @author Zhongyuan Liang & Jiahao Zhang
 * @editor by Haohua Ji
 */

public class OrganizerSendMessageFrame extends JFrame {

  private final JList<Object> AllEmails;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;

  /**
   * Send message menu for organizer.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public OrganizerSendMessageFrame(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2));

    JButton viewEmails = new JButton("View All Email Addresses that you could send message to");
    panel.add(viewEmails);

    JButton sendSingleMessage = new JButton("Send a single message");
    panel.add(sendSingleMessage);

    JButton sendMultipleMessages = new JButton("Send multiple messages");
    panel.add(sendMultipleMessages);

    AllEmails = new JList<>();
    panel.add(AllEmails);

    JButton Back = new JButton("Back");
    panel.add(Back);

    viewEmails.addActionListener(
        e -> AllEmails.setListData(messageController.generateEmailList().toArray()));

    sendSingleMessage.addActionListener(e -> {
      String a;
      a = JOptionPane
          .showInputDialog("Enter the email address that you want to send a message to");
      String b;
      b = JOptionPane.showInputDialog("Enter the content of the message");
      if (!messageController.organizerSendSingleMessage(a, b)) {
        JOptionPane.showMessageDialog(null, "Not valid Message");
      } else {
        JOptionPane.showMessageDialog(null, "Sent Successfully");
      }
    });

    sendMultipleMessages.addActionListener(e -> {
      String a;
      a = JOptionPane.showInputDialog(
          "Enter the target identity that you want to send messages to(Attendee/Speaker)");
      String b;
      b = JOptionPane.showInputDialog("Enter the content of the message");
      if (!messageController.sendMultipleMessage(a, b)) {
        JOptionPane.showMessageDialog(null, "Not valid Message");
      } else {
        JOptionPane.showMessageDialog(null, "Sent Successfully");
        messageController.saveMessage();
      }
    });

    Back.addActionListener(e -> {
      OrganizerSendMessageFrame.this.setVisible(false);
      MessageMenu mm = new MessageMenu(email, loginFacade, schedulerController, signUpController,
          messageController);
      mm.setVisible(true);
    });

    int MENU_WIDTH = 500;
    int MENU_HEIGHT = 500;
    panel.setSize(MENU_WIDTH, MENU_HEIGHT);
    panel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(panel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Organizer send message menu");
    this.setResizable(false);
  }
}