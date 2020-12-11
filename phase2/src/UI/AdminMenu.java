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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Haohua Ji
 **/

public class AdminMenu extends JFrame {

  final int MENU_WIDTH = 500;
  final int MENU_HEIGHT = 400;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JLabel hintLabel1;
  JTextField banUserEmail;
  JButton banAccount;
  JButton logout;

  /**
   * Admin's menu, admin can view the stats of the app. (E.g. No. of speaker accounts)
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public AdminMenu(String email, LoginFacade loginFacade, SchedulerController schedulerController,
      SignUpController
          signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel adminPanel = new JPanel();
    adminPanel.setLayout(new GridLayout(10, 1));
    hintLabel1 = new JLabel("User email: ");
    banUserEmail = new JTextField(40);
    banAccount = new JButton("Ban this user");
    logout = new JButton("Logout");
    adminPanel.add(hintLabel1);
    adminPanel.add(banUserEmail);
    adminPanel.add(banAccount);
    adminPanel.add(logout);

    logout.addActionListener(e -> {
      AdminMenu.this.setVisible(false);
      System.exit(0);
    });

    adminPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    adminPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(adminPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Admin Menu");
    this.setResizable(false);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }
}
