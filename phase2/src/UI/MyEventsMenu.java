package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyEventsMenu extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  private final ArrayList<String> allMyEvents = new ArrayList<String>();
  JList myEventsList;
  JPanel myEventsPanel;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JButton signOffEvent;
  JButton backButton;

  public MyEventsMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController,
      SignUpController signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;
    myEventsPanel = new JPanel();

    if (signUpController.viewEventRegister() != null) {
      allMyEvents.addAll(signUpController.viewEventRegister());
      myEventsList = new JList();
      myEventsList.setListData(allMyEvents.toArray());
      myEventsPanel.add(myEventsList);

      if (!loginFacade.getUserIdentity(email).equals("Speaker")) {
        signOffEvent = new JButton("Sign off event");
        myEventsPanel.add(signOffEvent);
      }

      backButton = new JButton("Back");
      myEventsPanel.add(backButton);

      backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          MyEventsMenu.this.setVisible(false);
          backToMenu();
        }
      });

      signOffEvent.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          /* Sign Off Event*/
        }
      });
    } else {
      JOptionPane.showMessageDialog(null, "You haven't signed up any events!");
      MyEventsMenu.this.setVisible(false);
      backToMenu();
    }
  }

  private void backToMenu() {
    String userType = loginFacade.getUserIdentity(email);
    if (userType.equals("Attendee")) {
      JFrame attendeeMenu = new AttendeeMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      attendeeMenu.setVisible(true);
    }
    if (userType.equals("Organizer")) {
      JFrame organizerMenu = new OrganizerMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      organizerMenu.setVisible(true);
    }
    if (userType.equals("Speaker")) {
      JFrame speakerMenu = new SpeakerMenu(email, loginFacade, schedulerController,
          signUpController, messageController);
      speakerMenu.setVisible(true);
    }
  }
}
