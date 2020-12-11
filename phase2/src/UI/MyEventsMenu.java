package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Haohua Ji
 **/

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

  /**
   * My events menu for attendees and organizers. They can sign off their events here.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public MyEventsMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController,
      SignUpController signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;
    myEventsPanel = new JPanel();
    myEventsPanel.setLayout(new GridLayout(10, 1));

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
      myEventsList.addActionListener(e -> {
        JComboBox cb = (JComboBox) e.getSource();
        selectedEvent[0] = (String) cb.getSelectedItem();
      });

      signOffEvent.addActionListener(e -> {
        boolean isSuccess = signUpController.cancelEvent(Integer.parseInt(selectedEvent[0]));
        if (isSuccess) {
          signUpController.save();
          JOptionPane.showMessageDialog(null, "Sign off success");
        } else {
          JOptionPane.showMessageDialog(null, "Sign off failed");
        }
      });
    } else {
      JOptionPane.showMessageDialog(null, "You haven't signed up any events!");
      backButton = new JButton("Back");
      myEventsPanel.add(backButton);

      backButton.addActionListener(e -> {
        MyEventsMenu.this.setVisible(false);
        backToMenu();
      });
    }

    myEventsPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    myEventsPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(myEventsPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("My Events Menu");
    this.setResizable(false);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }

  /**
   * Call helper methods to return to the previous menu by details.
   */
  private void backToMenu() {
    ManageRoomMenu
        .backhelper(loginFacade, email, schedulerController, signUpController, messageController);
  }
}
