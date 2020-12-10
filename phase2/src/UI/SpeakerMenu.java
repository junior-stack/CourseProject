package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpeakerMenu extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JButton logout;
  JButton viewMyMessage;
  JButton viewMyEvents;

  public SpeakerMenu(String email, LoginFacade loginFacade, SchedulerController schedulerController,
      SignUpController
          signUpController, MessageController messageController) {

    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel speakerPanel = new JPanel();
    viewMyEvents = new JButton("Display my events");
    viewMyMessage = new JButton("Display my messages");
    logout = new JButton("Logout");
    speakerPanel.add(viewMyEvents);
    speakerPanel.add(viewMyMessage);
    speakerPanel.add(logout);

    viewMyEvents.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SpeakerMenu.this.setVisible(false);
        JFrame myEventsMenu = new MyEventsMenu(email, loginFacade, schedulerController,
            signUpController, messageController);
        myEventsMenu.setVisible(true);
      }
    });

    viewMyMessage.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SpeakerMenu.this.setVisible(false);
        JFrame myMessageMenu = new MessageMenu(email, loginFacade, schedulerController,
            signUpController, messageController);
        myMessageMenu.setVisible(true);
      }
    });

    logout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SpeakerMenu.this.setVisible(false);
        JFrame loginMenu = new LoginMenu(loginFacade);
        loginMenu.setVisible(true);
      }
    });

    speakerPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    speakerPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(speakerPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Attendee Menu");
    this.setResizable(false);
  }
}