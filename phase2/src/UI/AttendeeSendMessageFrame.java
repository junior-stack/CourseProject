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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is the controller for message system.
 *
 * @author Zhongyuan Liang & Jiahao Zhang
 * @editor by Haohua Ji
 */

public class AttendeeSendMessageFrame extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  private final JList<Object> AllEmails;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;

  /**
   * Send message menu for attendee.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public AttendeeSendMessageFrame(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(10, 1));

    JButton viewEmails = new JButton("View All Email Addresses Available");
    panel.add(viewEmails);

    JButton sendMessage = new JButton("Send a message");
    panel.add(sendMessage);

    AllEmails = new JList<>();
    panel.add(AllEmails);

    JButton Back = new JButton("Back");
    panel.add(Back);

    viewEmails.addActionListener(
        e -> AllEmails.setListData(messageController.generateEmailList().toArray()));

    sendMessage.addActionListener(e -> {
      String a;
      a = JOptionPane
          .showInputDialog("Enter the email address that you want to send a message to");
      String b;
      b = JOptionPane.showInputDialog("Enter the content of the message");
      if (!messageController.attendeeSendSingleMessage(a, b)) {
        JOptionPane.showMessageDialog(null, "Not valid Message");
      } else {
        JOptionPane.showMessageDialog(null, "Sent Successfully");
        messageController.saveMessage();
      }
    });

    Back.addActionListener(e -> {
      AttendeeSendMessageFrame.this.setVisible(false);
      MessageMenu mm = new MessageMenu(email, loginFacade, schedulerController, signUpController,
          messageController);
      mm.setVisible(true);
    });

    panel.setSize(MENU_WIDTH, MENU_HEIGHT);
    panel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(panel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Admin Menu");
    this.setResizable(false);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }
}