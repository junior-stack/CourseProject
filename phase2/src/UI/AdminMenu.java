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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminMenu extends JFrame {

  final int MENU_WIDTH = 500;
  final int MENU_HEIGHT = 500;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JLabel hintLabel1;
  JTextField banUserEmail;
  JButton banAccount;
  JButton logout;

  public AdminMenu(String email, LoginFacade loginFacade, SchedulerController schedulerController,
      SignUpController
          signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel adminPanel = new JPanel();
    adminPanel.setLayout(new GridLayout(4, 1));
    hintLabel1 = new JLabel("User email: ");
    banUserEmail = new JTextField(40);
    banAccount = new JButton("Ban this user");
    logout = new JButton("Logout");
    adminPanel.add(hintLabel1);
    adminPanel.add(banUserEmail);
    adminPanel.add(banAccount);
    adminPanel.add(logout);

    logout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AdminMenu.this.setVisible(false);
        System.exit(0);
      }
    });

    adminPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    adminPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(adminPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Admin Menu");
    this.setResizable(false);
  }
}
