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

}