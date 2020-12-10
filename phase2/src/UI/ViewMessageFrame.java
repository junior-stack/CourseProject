package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.SchedulerController;
import Controller.SignUpController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewMessageFrame extends JFrame {

  private final JList<Object> UnreadMessages;
  private final JList<Object> ReadMessages;
  private final JList<Object> ArchiveMessages;

  String email;
  LoginFacade loginFacade;
  SchedulerController schedulerController;
  SignUpController signUpController;
  MessageController messageController;

  public ViewMessageFrame(String email, LoginFacade loginFacade,
      SchedulerController schedulerController, SignUpController
      signUpController, MessageController messageController) {
    this.email = email;
    this.loginFacade = loginFacade;
    this.schedulerController = schedulerController;
    this.signUpController = signUpController;
    this.messageController = messageController;

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2));

    JButton viewUnreadMessages = new JButton("View Unread Messages");
    panel.add(viewUnreadMessages);

    JButton viewReadMessages = new JButton("View Read Messages");
    panel.add(viewReadMessages);

    JButton viewArchiveMessages = new JButton("View Archive Messages");
    panel.add(viewArchiveMessages);

    JButton delete = new JButton("Delete");
    panel.add(delete);

    JButton unread = new JButton("Unread");
    panel.add(unread);

    JButton archive = new JButton("Archive");
    panel.add(archive);

    UnreadMessages = new JList<>();
    panel.add(UnreadMessages);

    ReadMessages = new JList<>();
    panel.add(ReadMessages);

    ArchiveMessages = new JList<>();
    panel.add(ArchiveMessages);

    delete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String a;
        a = JOptionPane.showInputDialog("Type the id of the message that you want to delete");
        try {
          messageController.deleteMessage(Integer.parseInt(a));
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, "Invalid ID!");
        }
      }
    });

    unread.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String a;
        a = JOptionPane
            .showInputDialog("Type the id of the message that you want to mark as unread");
        try {
          messageController.unreadMessage(Integer.parseInt(a));
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, "Invalid ID!");
        }
      }
    });

    archive.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String a;
        a = JOptionPane
            .showInputDialog("Type the id of the message that you want to mark as archive");
        try {
          messageController.archiveMessage(Integer.parseInt(a));
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, "Invalid ID!");
        }
      }
    });

    viewUnreadMessages.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        UnreadMessages.setListData(messageController.generateUnreadMessage().toArray());
      }
    });

    viewReadMessages.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ReadMessages.setListData(messageController.generateReadMessage().toArray());
      }
    });

    viewArchiveMessages.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ArchiveMessages.setListData(messageController.generateArchiveMessage().toArray());
      }
    });

    int MENU_HEIGHT = 500;
    int MENU_WIDTH = 500;
    panel.setSize(MENU_WIDTH, MENU_HEIGHT);
    panel.setLocation((MENU_WIDTH - 250) / 2, (MENU_HEIGHT - 250) / 2);
    this.add(panel);
    this.setSize(MENU_WIDTH, MENU_HEIGHT);
    this.setTitle("Admin Menu");
    this.setResizable(false);
  }

}
