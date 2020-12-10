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
import javax.swing.JPanel;

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
  JComboBox allEvents;
  JButton signUpButton;
  JPanel allEventPanel;

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

    if (loginFacade.getUserIdentity(email).equals("Organizer")) {
      allEventPanel.add(editEventButton);
      allEventPanel.add(addEventButton);
      allEventPanel.add(deleteEventButton);
    }
    allEventPanel.add(signUpButton);
    allEventPanel.add(backButton);

//    editEventButton.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        boolean isSuccess = schedulerController.confirmEditEvent();
//        if (isSuccess) {
//          JOptionPane.showMessageDialog(null, "更新成功");
//        } else {
//          JOptionPane.showMessageDialog(null, "更新失败");
//        }
//      }
//    });
//
//    addEventButton.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        boolean isSuccess = schedulerController.confirmAddEvent();
//        if (isSuccess) {
//          JOptionPane.showMessageDialog(null, "添加成功");
//        } else {
//          JOptionPane.showMessageDialog(null, "添加失败");
//        }
//      }
//    });
//
//    deleteEventButton.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        boolean isSuccess = schedulerController.confirmDeleteEvent();
//        if (isSuccess) {
//          JOptionPane.showMessageDialog(null, "删除成功");
//        } else {
//          JOptionPane.showMessageDialog(null, "删除失败");
//        }
//      }
//    });
//
//    signUpButton.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        boolean isSuccess = signUpController.signup();
//        if (isSuccess) {
//          JOptionPane.showMessageDialog(null, "注册成功");
//        } else {
//          JOptionPane.showMessageDialog(null, "注册失败");
//        }
//      }
//    });

    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AllEventsMenu.this.setVisible(false);
        backToMenu();
      }
    });

    allEventPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
    allEventPanel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(allEventPanel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("All events panel");
    this.setResizable(false);
  }

  static void backhelper(LoginFacade loginFacade, String email,
      SchedulerController schedulerController, SignUpController signUpController,
      MessageController messageController) {
    backhelper2(loginFacade, email, schedulerController, signUpController, messageController);
  }

  static void backhelper2(LoginFacade loginFacade, String email,
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
