package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginMenu extends JFrame {

  final int MENU_HEIGHT = 200;
  final int MENU_WIDTH = 500;
  LoginFacade loginFacade;
  JPanel loginPanel;
  JLabel userEmailLabel;
  JLabel userPasswordLabel;
  JTextField userEmailInput;
  JTextField userPasswordInput;
  JButton loginButton;
  JButton registerButton;

  public LoginMenu(LoginFacade loginFacade) {
    this.loginFacade = loginFacade;
    loginPanel = new JPanel();

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

    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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

      }
    });

    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        LoginMenu.this.setVisible(false);
        JFrame registerMenu = new RegisterMenu(loginFacade);
        registerMenu.setVisible(true);
      }
    });

    loginPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    loginPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(loginPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Login");
    this.setResizable(false);
  }
}
