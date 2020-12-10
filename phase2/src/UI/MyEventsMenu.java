package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyEventsMenu extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  JComboBox<String> myEventsList;
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

    if (!signUpController.viewEventRegister().isEmpty()) {
      myEventsList = new JComboBox<>(signUpController.viewEventRegister().toArray(new String[0]));
      myEventsPanel.add(myEventsList);

      backButton = new JButton("Back");
      myEventsPanel.add(backButton);

      if (!loginFacade.getUserIdentity(email).equals("Speaker")) {
        signOffEvent = new JButton("Sign off event");
        myEventsPanel.add(signOffEvent);
      }

      final String[] selectedEvent = new String[1];
      myEventsList.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JComboBox cb = (JComboBox) e.getSource();
          selectedEvent[0] = (String) cb.getSelectedItem();
        }
      });

      signOffEvent.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          boolean isSuccess = signUpController.cancelEvent(Integer.parseInt(selectedEvent[0]));
          if (isSuccess) {
            JOptionPane.showMessageDialog(null, "Sign off success");
          } else {
            JOptionPane.showMessageDialog(null, "Sign off failed");
          }
        }
      });
    } else {
      JOptionPane.showMessageDialog(null, "You haven't signed up any events!");
      backButton = new JButton("Back");
      myEventsPanel.add(backButton);

      backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          MyEventsMenu.this.setVisible(false);
          backToMenu();
        }
      });
    }

    myEventsPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    myEventsPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(myEventsPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Attendee Menu");
    this.setResizable(false);
  }

  private void backToMenu() {
    ManageRoomMenu
        .backhelper(loginFacade, email, schedulerController, signUpController, messageController);
  }
}
