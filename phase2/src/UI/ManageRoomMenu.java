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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManageRoomMenu extends JFrame {

  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;
  JButton deleteRoomButton;
  JButton addRoomButton;
  JComboBox<String> roomsSelections;
  JLabel roomIdLabel;
  JTextField roomIdField;
  JLabel hintLabel1;
  JPanel roomMenuPanel;
  JButton backButton;

  public ManageRoomMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController,
      SignUpController signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    roomMenuPanel = new JPanel();
    deleteRoomButton = new JButton("Delete selected room");
    addRoomButton = new JButton("Add room");
    hintLabel1 = new JLabel("Select a room below to delete or enter a room ID to add.");
    if (schedulerController.get_rooms() != null) {
      roomMenuPanel.add(hintLabel1);
      roomsSelections = new JComboBox<>(schedulerController.get_rooms().toArray(new String[0]));
      roomsSelections.setSelectedIndex(0);
      roomMenuPanel.add(roomsSelections);
      roomMenuPanel.add(deleteRoomButton);
    }

    roomIdLabel = new JLabel("Enter room id: ");
    roomIdField = new JTextField(40);
    roomMenuPanel.add(roomIdLabel);
    roomMenuPanel.add(addRoomButton);

    backButton = new JButton("Back");
    roomMenuPanel.add(backButton);

    final String[] roomId = new String[1];
    roomsSelections.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        roomId[0] = (String) cb.getSelectedItem();
      }
    });

    deleteRoomButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isDeleted = schedulerController.confirmdeleteroom(Integer.parseInt(roomId[0]));
        if (isDeleted) {
          JOptionPane.showMessageDialog(null, "Room deleted");
          schedulerController.savedata();
        } else {
          JOptionPane.showMessageDialog(null, "Room delete failed");
        }
      }
    });

    addRoomButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (roomIdField.getText() != null) {
          int roomId = Integer.parseInt(roomIdField.getText());
          boolean isAdded = schedulerController.confirmaddroom(roomId, 50);
          if (isAdded) {
            JOptionPane.showMessageDialog(null, "Room added");
            schedulerController.savedata();
          } else {
            JOptionPane.showMessageDialog(null, "Room add failed");
          }
        }
      }
    });

    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ManageRoomMenu.this.setVisible(false);
        backToMenu();
      }
    });
  }

  static void backhelper(LoginFacade loginFacade, String email,
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

  private void backToMenu() {
    backhelper(loginFacade, email, schedulerController, signUpController, messageController);
  }
}
