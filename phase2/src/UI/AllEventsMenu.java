package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Haohua Ji
 **/

public class AllEventsMenu extends JFrame {

  final int MENU_HEIGHT = 500;
  final int MENU_WIDTH = 500;
  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JButton editEventButton;
  JButton addEventButton;
  JButton deleteEventButton;
  JButton backButton;
  JList<String> allEvents;
  JLabel hintLabel1;
  JTextField selectedEvent;
  JButton signUpButton;
  JPanel allEventPanel;
  JLabel roomIdLabel;
  JTextField roomIdInput;
  JLabel startTimeLabel;
  JTextField startTimeInput;
  JLabel endTimeLabel;
  JTextField endTimeInput;
  JLabel speakerIdLabel;
  JTextField speakerIdInput;
  JLabel topicLabel;
  JTextField topicInput;

  /**
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public AllEventsMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    allEventPanel = new JPanel();
    editEventButton = new JButton("Edit event");
    addEventButton = new JButton("Add event");
    deleteEventButton = new JButton("Delete event");
    backButton = new JButton("Back");
    signUpButton = new JButton("Sign up event");

    allEvents = new JList<>(schedulerController.ShowAllEvents().toArray(new String[0]));
    hintLabel1 = new JLabel("Enter the event Id to continue: ");
    selectedEvent = new JTextField(40);
    allEventPanel.add(hintLabel1);
    allEventPanel.add(selectedEvent);

    roomIdLabel = new JLabel("Room ID: ");
    roomIdInput = new JTextField(40);
    startTimeLabel = new JLabel("Start Time: ");
    startTimeInput = new JTextField(40);
    endTimeLabel = new JLabel("End Time: ");
    endTimeInput = new JTextField(40);
    speakerIdLabel = new JLabel("Speaker ID(s), separate by ',' ");
    speakerIdInput = new JTextField(40);
    topicLabel = new JLabel("Topic: ");
    topicInput = new JTextField(40);

    if (loginFacade.getUserIdentity(email).equals("Organizer")) {
      allEventPanel.setLayout(new GridLayout(10, 1));
      allEventPanel.add(roomIdLabel);
      allEventPanel.add(roomIdInput);
      allEventPanel.add(startTimeLabel);
      allEventPanel.add(startTimeInput);
      allEventPanel.add(endTimeLabel);
      allEventPanel.add(endTimeInput);
      allEventPanel.add(speakerIdLabel);
      allEventPanel.add(speakerIdInput);
      allEventPanel.add(topicLabel);
      allEventPanel.add(topicInput);
      allEventPanel.add(editEventButton);
      allEventPanel.add(addEventButton);
      allEventPanel.add(deleteEventButton);
    }
    allEventPanel.add(signUpButton);
    allEventPanel.add(backButton);

    editEventButton.addActionListener(e -> {
      if (!roomIdInput.getText().isEmpty() && !startTimeInput.getText().isEmpty() && !endTimeInput
          .getText().isEmpty() && !topicInput.getText()
          .isEmpty()) {

        int roomId = Integer.parseInt(roomIdInput.getText());
        String startTime = startTimeInput.getText();
        String endTime = endTimeInput.getText();
        String[] speakerIds = speakerIdInput.getText().split(",");
        ArrayList<Integer> speakerIdss = new ArrayList<>();
        for (String s : speakerIds) {
          speakerIdss.add(Integer.valueOf(s));
        }
        String topic = topicInput.getText();
        String eventId = JOptionPane.showInputDialog(null, "Event Id: ");
        boolean isSuccess = schedulerController
            .confirmEditEvent(Integer.parseInt(eventId), roomId, startTime, endTime, topic,
                speakerIdss);
        if (isSuccess) {
          schedulerController.savedata();
          JOptionPane.showMessageDialog(null, "Edit success");
        } else {
          JOptionPane.showMessageDialog(null, "Edit failed");
        }
      }
    });

    addEventButton.addActionListener(e ->

    {
      if (!roomIdInput.getText().isEmpty() && !startTimeInput.getText().isEmpty() && !endTimeInput
          .getText().isEmpty() && !topicInput.getText()
          .isEmpty()) {
        String capacityInput = JOptionPane.showInputDialog(null,
            "Room capacity: ");
        int roomId = Integer.parseInt(roomIdInput.getText());
        String startTime = startTimeInput.getText();
        String endTime = endTimeInput.getText();
        String[] speakerIds = speakerIdInput.getText().split(",");
        ArrayList<Integer> speakerIdss = new ArrayList<>();
        for (String s : speakerIds) {
          speakerIdss.add(Integer.valueOf(s));
        }
        String topic = topicInput.getText();
        int capacity = Integer.parseInt(capacityInput);
        String eventType;
        if (speakerIds.length == 0) {
          eventType = "NoSpeakerEvent";
        } else if (speakerIds.length == 1) {
          eventType = "OneSpeakerEvent";
        } else {
          eventType = "MultipleSpeakerEvent";
        }
        boolean isSuccess = schedulerController
            .confirmAddEvent(roomId, startTime, endTime, speakerIdss, topic, capacity, eventType);
        if (isSuccess) {
          schedulerController.savedata();
          JOptionPane.showMessageDialog(null, "Add event success");
        } else {
          JOptionPane.showMessageDialog(null, "Add event failed");
        }
      }
    });

    deleteEventButton.addActionListener(e ->

    {
      if (!selectedEvent.getText().isEmpty()) {
        boolean isSuccess = schedulerController
            .confirmDeleteEvent(Integer.parseInt(selectedEvent.getText()));
        if (isSuccess) {
          schedulerController.savedata();
          JOptionPane.showMessageDialog(null, "Delete event success");
        } else {
          JOptionPane.showMessageDialog(null, "Delete event failed");
        }
      }
    });

    signUpButton.addActionListener(e ->

    {
      if (!selectedEvent.getText().isEmpty()) {
        boolean isSuccess = signUpController.signup(Integer.parseInt(selectedEvent.getText()));
        if (isSuccess) {
          schedulerController.savedata();
          JOptionPane.showMessageDialog(null, "Sign up event success");
        } else {
          JOptionPane.showMessageDialog(null, "Sign up event failed");
        }
      }
    });

    backButton.addActionListener(e ->

    {
      AllEventsMenu.this.setVisible(false);
      backToMenu();
    });

    allEventPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    allEventPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(allEventPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("All events panel");
    this.setResizable(false);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
  }

  /**
   * Helper method for back button.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  static void back_helper(LoginFacade loginFacade, String email,
      SchedulerController schedulerController, SignUpController signUpController,
      MessageController messageController) {
    back_helper2(loginFacade, email, schedulerController, signUpController, messageController);
  }

  /**
   * Helper method for back button.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  static void back_helper2(LoginFacade loginFacade, String email,
      SchedulerController schedulerController, SignUpController signUpController,
      MessageController messageController) {
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

  /**
   * Call helper methods to return to the previous menu by details.
   */
  private void backToMenu() {
    back_helper(loginFacade, email, schedulerController, signUpController, messageController);
  }

}
