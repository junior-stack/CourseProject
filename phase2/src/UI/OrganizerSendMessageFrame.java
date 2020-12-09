package UI;

import Controller.MessageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizerSendMessageFrame extends JFrame  {
    private JPanel panel;
    private final int FRAME_HEIGHT = 500;
    private final int FRAME_WIDTH = 500;
    private MessageController mc;
    private JButton ViewEmails;
    private JButton SendSingleMessage;
    private JButton SendMultipleMessages;
    private JList AllEmails;


    public OrganizerSendMessageFrame(MessageController mc){
        this.mc = mc;

        panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));

        ViewEmails = new JButton("View All Email Addresses that you could send message to");
        panel.add(ViewEmails);

        SendSingleMessage = new JButton("Send a single message");
        panel.add(SendSingleMessage);

        SendMultipleMessages = new JButton("Send multiple messages");
        panel.add(SendMultipleMessages);

        AllEmails = new JList();
        panel.add(AllEmails);

        ViewEmails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllEmails.setListData(mc.generateEmailList().toArray());
            }
        });

        SendSingleMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a;
                a = JOptionPane.showInputDialog("Enter the email address that you want to send a message to");
                String b;
                b = JOptionPane.showInputDialog("Enter the content of the message");
                if (!mc.organizerSendSingleMessage(a, b)){
                    JOptionPane.showMessageDialog(null, "Not valid Message");
                } else{
                    JOptionPane.showMessageDialog(null, "Sent Successfully");
                }
            }
        });

        SendMultipleMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a;
                a = JOptionPane.showInputDialog("Enter the target identity that you want to send messages to(Attendee/Speaker)");
                String b;
                b = JOptionPane.showInputDialog("Enter the content of the message");
                if (!mc.sendMultipleMessage(a, b)){
                    JOptionPane.showMessageDialog(null, "Not valid Message");
                } else{
                    JOptionPane.showMessageDialog(null, "Sent Successfully");
                }
            }
        });

        this.add (panel);
        this.setSize (FRAME_WIDTH, FRAME_HEIGHT);
    }
}