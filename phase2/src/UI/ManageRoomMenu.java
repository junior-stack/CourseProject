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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Haohua Ji
 **/

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

  /**
   * Manage room menu for organizers, organizers can create room or delete room here.
   *
   * @param email               - user's email
   * @param loginFacade         - each user has only 1 facade running at a time.
   * @param schedulerController - each user has only 1 schedule controller running at a time.
   * @param signUpController    - each user has only 1 signup controller running at a time.
   * @param messageController   - each user has only 1 message controller running at a time.
   */
  public ManageRoomMenu(String email, LoginFacade loginFacade,
      SchedulerController schedulerController,
      SignUpController signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    roomMenuPanel = new JPanel();
    roomMenuPanel.setLayout(new GridLayout(10, 1));
    deleteRoomButton = new JButton("Delete selected room");
    addRoomButton = new JButton("Add room");
    hintLabel1 = new JLabel("Enter a room ID to add or delete.");
    if (schedulerController.get_rooms() != null) {
      roomMenuPanel.add(hintLabel1);
      roomsSelections = new JComboBox<>(schedulerController.get_rooms().toArray(new String[0]));
      roomMenuPanel.add(roomsSelections);
    }

    roomIdLabel = new JLabel("Enter room id: ");
    roomIdField = new JTextField(40);
    roomMenuPanel.add(roomIdLabel);
    roomMenuPanel.add(roomIdField);
    roomMenuPanel.add(addRoomButton);

    if (schedulerController.get_rooms() != null) {
      roomMenuPanel.add(deleteRoomButton);
    }

    backButton = new JButton("Back");
    roomMenuPanel.add(backButton);

    final String[] roomId = new String[1];
    roomsSelections.addActionListener(e -> {
      JComboBox cb = (JComboBox) e.getSource();
      roomId[0] = (String) cb.getSelectedItem();
    });

    deleteRoomButton.addActionListener(e -> {
      boolean isDeleted = schedulerController
          .confirmdeleteroom(Integer.parseInt(roomIdField.getText()));
      if (isDeleted) {
        JOptionPane.showMessageDialog(null, "Room deleted");
        schedulerController.savedata();
      } else {
        JOptionPane.showMessageDialog(null, "Room delete failed");
      }
    });

    addRoomButton.addActionListener(e -> {
      String roomCapacity = JOptionPane.showInputDialog(null, "Room Capacity: ");
      if (!roomIdField.getText().isEmpty() && !roomCapacity.isEmpty()) {
        int roomId1 = Integer.parseInt(roomIdField.getText());
        boolean isAdded = schedulerController
            .confirmaddroom(roomId1, Integer.parseInt(roomCapacity));
        if (isAdded) {
          JOptionPane.showMessageDialog(null, "Room added");
          schedulerController.savedata();
        } else {
          JOptionPane.showMessageDialog(null, "Room add failed");
        }
      }
    });

    backButton.addActionListener(e -> {
      ManageRoomMenu.this.setVisible(false);
      backToMenu();
    });

    int MENU_WIDTH = 500;
    int MENU_HEIGHT = 500;
    roomMenuPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    roomMenuPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(roomMenuPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Manage Room Menu");
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
  static void backhelper(LoginFacade loginFacade, String email,
      SchedulerController schedulerController, SignUpController signUpController,
      MessageController messageController) {
    AllEventsMenu
        .back_helper2(loginFacade, email, schedulerController, signUpController, messageController);
  }

  /**
   * Call helper methods to return to the previous menu by details.
   */
  private void backToMenu() {
    backhelper(loginFacade, email, schedulerController, signUpController, messageController);
  }
}
