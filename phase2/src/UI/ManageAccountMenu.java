package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import Controller.UserAccountsController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by Haohua Ji
 **/

public class ManageAccountMenu extends JFrame {

  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JLabel userName;
  JLabel userPassword;
  JLabel userEmail;
  JLabel userPhone;
  JLabel selection;
  JTextField userNameInput;
  JTextField userPasswordInput;
  JTextField userEmailInput;
  JTextField userPhoneInput;
  JComboBox<String> userTypeSelection;
  UserAccountsController userAccountsController;
  JButton registerButton;
  JButton backButton;
  JPanel createAccountPanel;

  public ManageAccountMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController,
      SignUpController signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;
    this.userAccountsController = new UserAccountsController(loginFacade.getUam());

    createAccountPanel = new JPanel();

    userName = new JLabel("Username: ");
    userPassword = new JLabel("Password: ");
    userEmail = new JLabel("Email: ");
    userPhone = new JLabel("Phone: ");

    userNameInput = new JTextField(40);
    userPasswordInput = new JTextField(40);
    userEmailInput = new JTextField(40);
    userPhoneInput = new JTextField(40);

    selection = new JLabel("Select the type you want to create from below");
    String[] userTypes = {"Attendee", "Organizer", "Speaker", "Admin"};
    userTypeSelection = new JComboBox<>(userTypes);

    registerButton = new JButton("Register");
    backButton = new JButton("Back");

    createAccountPanel.add(userName);
    createAccountPanel.add(userNameInput);
    createAccountPanel.add(userPassword);
    createAccountPanel.add(userPasswordInput);
    createAccountPanel.add(userEmail);
    createAccountPanel.add(userEmailInput);
    createAccountPanel.add(userPhone);
    createAccountPanel.add(userPhoneInput);
    createAccountPanel.add(selection);
    createAccountPanel.add(userTypeSelection);
    createAccountPanel.add(registerButton);
    createAccountPanel.add(backButton);

    final String[] userType = new String[1];
    userTypeSelection.addActionListener(e -> {
      JComboBox cb = (JComboBox) e.getSource();
      userType[0] = (String) cb.getSelectedItem();
    });

    registerButton.addActionListener(e -> {
      if (!userNameInput.getText().isEmpty() && !userPasswordInput.getText().isEmpty() &&
          !userEmailInput.getText().isEmpty() && !userPhoneInput.getText().isEmpty()) {
        String userName = userNameInput.getText();
        String userPassword = userPasswordInput.getText();
        String userEmail = userEmailInput.getText();
        String userPhone = userPhoneInput.getText();

        if (userType[0].equals("Attendee")) {
          boolean isSuccess = userAccountsController
              .createAttendee(userName, userPassword, userPhone, email);
          sendCheck(isSuccess);
        }
        if (userType[0].equals("Speaker")) {
          boolean isSuccess = userAccountsController
              .createSpeaker(userName, userPassword, userPhone, email);
          sendCheck(isSuccess);
        }
      } else {
        JOptionPane.showMessageDialog(null, "You must fill everything in"
            + " order to register!");
      }
    });

    int MENU_WIDTH = 500;
    int MENU_HEIGHT = 500;
    createAccountPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    createAccountPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(createAccountPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Organizer send message menu");
    this.setResizable(false);
  }

  private void sendCheck(boolean isSuccess) {
    if (isSuccess) {
      loginFacade.save();
      JOptionPane.showConfirmDialog(null, "Register successful!");
    } else {
      JOptionPane.showMessageDialog(null, "Register failed!");
    }
  }

}
