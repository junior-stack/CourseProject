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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Haohua Ji
 **/

public class LoginMenu extends JFrame {

  final int MENU_HEIGHT = 400;
  final int MENU_WIDTH = 500;
  LoginFacade loginFacade;
  JPanel loginPanel;
  JLabel userEmailLabel;
  JLabel userPasswordLabel;
  JTextField userEmailInput;
  JTextField userPasswordInput;
  JButton loginButton;
  JButton registerButton;

  /**
   * App's login menu.
   *
   * @param loginFacade - each user has only 1 facade running at a time.
   */
  public LoginMenu(LoginFacade loginFacade) {
    this.loginFacade = loginFacade;
    loginPanel = new JPanel();
    loginPanel.setLayout(new GridLayout(10, 1));

    userEmailLabel = new JLabel("Email: ");
    loginPanel.add(userEmailLabel);

    userEmailInput = new JTextField(40);
    loginPanel.add(userEmailInput);

    userPasswordLabel = new JLabel("Password: ");
    loginPanel.add(userPasswordLabel);

    userPasswordInput = new JTextField(40);
    loginPanel.add(userPasswordInput);

    loginButton = new JButton("Login");
    registerButton = new JButton("Register");
    loginPanel.add(loginButton);
    loginPanel.add(registerButton);

    loginButton.addActionListener(e -> {
      if (!userEmailInput.getText().isEmpty() && !userPasswordInput.getText().isEmpty()) {
        String userEmail = userEmailInput.getText();
        String userPassword = userPasswordInput.getText();

        boolean isSuccess = loginFacade.login(userEmail, userPassword);
        if (!isSuccess) {
          JOptionPane.showMessageDialog(null, "Login failed!");
        } else {
          SchedulerController schedulerController = new SchedulerController(loginFacade.getUam());
          SignUpController signUpController = new SignUpController(userEmail,
              loginFacade.getUam(),
              schedulerController.getVr(), schedulerController.getEm());
          MessageController messageController = new MessageController(userEmail,
              loginFacade.getUserIdentity(userEmail));

          LoginMenu.this.setVisible(false);
          if (loginFacade.getUserIdentity(userEmail).equals("Attendee")) {
            JFrame attendeeMenu = new AttendeeMenu(userEmail, loginFacade, schedulerController,
                signUpController, messageController);
            attendeeMenu.setVisible(true);
          }
          if (loginFacade.getUserIdentity(userEmail).equals("Organizer")) {
            JFrame organizerMenu = new OrganizerMenu(userEmail, loginFacade, schedulerController,
                signUpController, messageController);
            organizerMenu.setVisible(true);
          }
          if (loginFacade.getUserIdentity(userEmail).equals("Speaker")) {
            JFrame speakerMenu = new SpeakerMenu(userEmail, loginFacade, schedulerController,
                signUpController, messageController);
            speakerMenu.setVisible(true);
          }
          if (loginFacade.getUserIdentity(userEmail).equals("Admin")) {
            JFrame adminMenu = new AdminMenu(userEmail, loginFacade, schedulerController,
                signUpController, messageController);
            adminMenu.setVisible(true);
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "You must fill everything in order to login!");
      }

    });

    registerButton.addActionListener(e -> {
      LoginMenu.this.setVisible(false);
      JFrame registerMenu = new RegisterMenu(loginFacade);
      registerMenu.setVisible(true);
    });

    loginPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    loginPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(loginPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Login");
    this.setResizable(false);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }
}
