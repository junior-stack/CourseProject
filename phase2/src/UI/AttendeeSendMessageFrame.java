package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AttendeeSendMessageFrame extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  private final JList<Object> AllEmails;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;

  public AttendeeSendMessageFrame(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2));

    JButton viewEmails = new JButton("View All Email Addresses Available");
    panel.add(viewEmails);

    JButton sendMessage = new JButton("Send a message");
    panel.add(sendMessage);

    AllEmails = new JList<>();
    panel.add(AllEmails);

    viewEmails.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AllEmails.setListData(messageController.generateEmailList().toArray());
      }
    });

    sendMessage.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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
      }
    });

    panel.setSize(MENU_WIDTH, MENU_HEIGHT);
    panel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(panel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Admin Menu");
    this.setResizable(false);
  }
}