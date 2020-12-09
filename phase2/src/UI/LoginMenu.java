package UI;

import Controller.LoginFacade;
import Entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                        System.out.println("Logged in as " + userEmail + " with pass: " + userPassword);
                        for (String u : loginFacade.getallUsers()){
                            System.out.println(u);
                        }
                        LoginMenu.this.setVisible(false);
                        if (loginFacade.getUserIdentity(userEmail).equals("Attendee")) {
                            JFrame attendeeMenu = new AttendeeMenu(userEmail, loginFacade);
                            attendeeMenu.setVisible(true);
                        }
                        if (loginFacade.getUserIdentity(userEmail).equals("Organizer")) {
                            JFrame organizerMenu = new OrganizerMenu(userEmail, loginFacade);
                            organizerMenu.setVisible(true);
                        }
                        if (loginFacade.getUserIdentity(userEmail).equals("Speaker")) {
                            JFrame speakerMenu = new SpeakerMenu(userEmail, loginFacade);
                            speakerMenu.setVisible(true);
                        }
                        if (loginFacade.getUserIdentity(userEmail).equals("Admin")) {
                            JFrame adminMenu = new AdminMenu(userEmail, loginFacade);
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
