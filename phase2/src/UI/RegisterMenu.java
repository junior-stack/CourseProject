package UI;

import Controller.LoginFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterMenu extends JFrame {

    final int MENU_WIDTH = 500;
    final int MENU_HEIGHT = 300;
    JPanel registerPanel;
    JLabel userName;
    JLabel userPassword;
    JLabel userEmail;
    JLabel userPhone;
    JTextField userNameInput;
    JTextField userPasswordInput;
    JTextField userEmailInput;
    JTextField userPhoneInput;
    JButton registerButton;
    JButton backButton;
    LoginFacade loginFacade;

    public RegisterMenu(LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
        registerPanel = new JPanel();

        userName = new JLabel("Username: ");
        userPassword = new JLabel("Password: ");
        userEmail = new JLabel("Email: ");
        userPhone = new JLabel("Phone: ");

        userNameInput = new JTextField(40);
        userPasswordInput = new JTextField(40);
        userEmailInput = new JTextField(40);
        userPhoneInput = new JTextField(40);

        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        registerPanel.add(userName);
        registerPanel.add(userNameInput);
        registerPanel.add(userPassword);
        registerPanel.add(userPasswordInput);
        registerPanel.add(userEmail);
        registerPanel.add(userEmailInput);
        registerPanel.add(userPhone);
        registerPanel.add(userPhoneInput);
        registerPanel.add(registerButton);
        registerPanel.add(backButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!userNameInput.getText().isEmpty() && !userPasswordInput.getText().isEmpty() &&
                        !userEmailInput.getText().isEmpty() && !userPhoneInput.getText().isEmpty()) {
                    String userName = userNameInput.getText();
                    String userPassword = userPasswordInput.getText();
                    String userEmail = userEmailInput.getText();
                    String userPhone = userPhoneInput.getText();

                    boolean isSuccess = loginFacade.register(userName, userPassword, userPhone, userEmail);
                    if (isSuccess) {
                        loginFacade.save();
                        JOptionPane.showConfirmDialog(null, "Register successful!");
                        RegisterMenu.this.setVisible(false);
                        JFrame loginMenu = new LoginMenu(loginFacade);
                        loginMenu.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Register failed!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You must fill everything in order to register!");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterMenu.this.setVisible(false);
                JFrame loginMenu = new LoginMenu(loginFacade);
                loginMenu.setVisible(true);
            }
        });

        registerPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
        registerPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
        this.add(registerPanel);
        this.setSize(MENU_WIDTH, MENU_HEIGHT);
        this.setTitle("Register");
        this.setResizable(false);
    }
}
