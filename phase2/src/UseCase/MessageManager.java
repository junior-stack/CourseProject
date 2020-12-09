package UseCase;

import Entity.Event;
import Entity.Message;
import Entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a MessageManager.
 *
 * @author Zhongyuan Liang & Jiahao Zhang
 * @version 1.0
 */
public class MessageManager {
    private final List<Message> messages;

    /**
     * This is a constructor for MessageManager.
     *
     * @param messages This represents the list of message entities in the system.
     */
    public MessageManager(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * This method is a getter for messages.
     *
     * @return List The list of messages in the system.
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * This method is to send a message from senderEmail to receiverEmail with content.
     *
     * @param senderEmail   This represents the sender email address.
     * @param receiverEmail This represents the receiver email address.
     * @param content       This represents the content of the message.
     */
    public void singleMessageRequest(String senderEmail, String receiverEmail, String content) {
        Message m = new Message(senderEmail, receiverEmail, content);
        messages.add(m);
    }

    /**
     * This method is to send multiple messages at the same time from senderEmail to all receiverEmails with content.
     *
     * @param senderEmail    This represents the sender email address.
     * @param receiverEmails This represents a list of all receiver email addresses.
     * @param content        This represents the content of the message.
     */
    public void multipleMessageRequest(String senderEmail, List<String> receiverEmails, String content) {
        for (String e : receiverEmails) {
            singleMessageRequest(senderEmail, e, content);
        }
    }

    /**
     * This method is to validate if a speaker can send a single message to the receiver email address.
     *
     * @param speakerEmail  This represents the email of the speaker.
     * @param receiverEmail This represents the target email address.
     * @return boolean True iff the message can be sent.
     */
    public boolean validateResponse(String speakerEmail, String receiverEmail) {
        for (Message m : messages) {
            if (m.getsenderEmail().equals(receiverEmail) && m.getsenderEmail().equals(speakerEmail)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to generate all emails of the targetIdentity for organizer to send messages.
     *
     * @param targetIdentity This represents the targetIdentity. Either "Attendee" or "Speaker".
     * @return ArrayList All email addresses of the targetIdentity.
     */
    public List<String> OrganizerGenerateEmail(String targetIdentity) {
        List<User> users = UserAccountManager.userList;
        List<String> emails = new ArrayList<>();
        for (User u : users) {
            if (u.getType().equals(targetIdentity)) {
                emails.add(u.getEmail());
            }
        }
        return emails;
    }

    /**
     * This method is to generate all emails of attendees of certain events for a speaker to send messages.
     *
     * @param eventIds This represents the List of events the speaker wants to send message to.
     * @return ArrayList All email addresses of the attendees of certain events.
     */
    public List<String> SpeakerGenerateEmail(List<Integer> eventIds) {
        List<Event> events = EventManager.eventpool;
        List<Event> e = new ArrayList<>();
        for (Event temp : events) {
            if (eventIds.contains(temp.getId())) {
                e.add(temp);
            }
        }
        List<Integer> userIds = new ArrayList<>();
        for (Event temp2 : e) {
            userIds.addAll(temp2.getAllAttendee());
        }
        List<String> emails = new ArrayList<>();
        for (Integer Id : userIds) {
            emails.add(UserAccountManager.idToEmail(Id));
        }
        return emails;
    }

    /**
     * This method is to set a message to status "delete".
     *
     * @param messageId This represents the id of the message to be modified.
     */
    public void delete(int messageId) {
        for (Message m : messages) {
            if (m.getmessageId() == messageId) {
                m.setCurrentStatus("delete");
            }
        }
    }

    /**
     * This method is to set a message to status "archive".
     *
     * @param messageId This represents the id of the message to be modified.
     */
    public void archive(int messageId) {
        for (Message m : messages) {
            if (m.getmessageId() == messageId) {
                m.setCurrentStatus("archive");
            }
        }
    }

    /**
     * This method is to set a message to status "unread".
     *
     * @param messageId This represents the id of the message to be modified.
     */
    public void unread(int messageId) {
        for (Message m : messages) {
            if (m.getmessageId() == messageId) {
                m.setCurrentStatus("unread");
            }
        }
    }

    /**
     * This method is to set a message to status "read".
     *
     * @param messageId This represents the id of the message to be modified.
     */
    public void read(int messageId) {
        for (Message m : messages) {
            if (m.getmessageId() == messageId) {
                m.setCurrentStatus("read");
            }
        }
    }

    /**
     * This method is to get the status of a certain message.
     *
     * @param messageId This represents the id of the message.
     * @return String The status of the message, can be "delete", "archive", "read" or "unread".
     */
    public String idToStatus(int messageId) {
        for (Message m : messages) {
            if (m.getmessageId() == messageId) {
                return m.getcurrentStatus();
            }
        }
        return "";
    }

    /**
     * This method is to generate all messages in a string with certain status.
     *
     * @param userEmail This represents the email of a certain user.
     * @param status    This represents the status to be generated.
     * @return StringBuilder All messages in a string with certain status.
     */
//    public StringBuilder generateMessage(String userEmail, String status) {
//        StringBuilder result = new StringBuilder();
//        for (Message m : messages) {
//            if (m.getreceiverEmail().equals(userEmail) && m.getcurrentStatus().equals(status)) {
//                result.append(m.toString());
//                if (status.equals("unread")) {
//                    m.setCurrentStatus("read");
//                }
//            }
//        }
//        return result;
//    }

    public List<String> generateMessage(String userEmail, String status){
        List<String> result = new ArrayList<>();
        for (Message m: messages){
            if (m.getreceiverEmail().equals(userEmail) && m.getcurrentStatus().equals(status)){
                result.add(m.toString());
                if (status.equals("unread")){
                    m.setCurrentStatus("read");
                }
            }
        }
        return result;
    }


}
