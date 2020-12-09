package UI;

import Controller.MessageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMessageFrame extends JFrame {
    private JButton ViewUnreadMessages;
    private JButton ViewReadMessages;
    private JButton ViewArchiveMessages;
    private JButton Delete;
    private JButton Unread;
    private JButton Archive;
    private JPanel panel;
    private final int FRAME_HEIGHT = 500;
    private final int FRAME_WIDTH = 500;
    private JList UnreadMessages;
    private JList ReadMessages;
    private JList ArchiveMessages;
    private MessageController mc;

    public ViewMessageFrame(MessageController mc){
        this.mc = mc;

        panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));

        ViewUnreadMessages = new JButton("View Unread Messages");
        panel.add(ViewUnreadMessages);

        ViewReadMessages = new JButton("View Read Messages");
        panel.add(ViewReadMessages);

        ViewArchiveMessages = new JButton("View Archive Messages");
        panel.add(ViewArchiveMessages);

        Delete = new JButton("Delete");
        panel.add(Delete);

        Unread = new JButton("Unread");
        panel.add(Unread);

        Archive = new JButton("Archive");
        panel.add(Archive);

        UnreadMessages = new JList();
        panel.add(UnreadMessages);

        ReadMessages = new JList();
        panel.add(ReadMessages);

        ArchiveMessages = new JList();
        panel.add(ArchiveMessages);

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a;
                a = JOptionPane.showInputDialog("Type the id of the message that you want to delete");
                try{ mc.deleteMessage(Integer.parseInt(a));}
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Invalid ID!");
                }
            }
        });


        Unread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a;
                a = JOptionPane.showInputDialog("Type the id of the message that you want to mark as unread");
                try{ mc.unreadMessage(Integer.parseInt(a));}
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Invalid ID!");
                }
            }
        });

        Archive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a;
                a = JOptionPane.showInputDialog("Type the id of the message that you want to mark as archive");
                try{ mc.archiveMessage(Integer.parseInt(a));}
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Invalid ID!");
                }
            }
        });

        ViewUnreadMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnreadMessages.setListData(mc.generateUnreadMessage().toArray());
            }
        });

        ViewReadMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadMessages.setListData(mc.generateReadMessage().toArray());
            }
        });


        ViewArchiveMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArchiveMessages.setListData(mc.generateArchiveMessage().toArray());
            }
        });

        this.add (panel);
        this.setSize (FRAME_WIDTH, FRAME_HEIGHT);
    }

}