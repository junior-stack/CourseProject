package Controller;

import UseCase.MessageManager;
import UseCase.UserAccountManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the controller for message system.
 *
 * @author Zhongyuan Liang & Jiahao Zhang
 */
public class MessageController {

    private final MessageManager mm;

    private final String userType;
    private final String userEmail;

    /**
     * This is the constructor for Message Controller.
     *
     * @param useremail The email address for the current user.
     * @param userType  The type of the user.
     */
    public MessageController(String useremail, String userType) {

        this.userType = userType;
        this.userEmail = useremail;
        //HashMap<String, HashMap<String, List<String>>> previousMessageStorage = ig.read();
        // When the gateway is finished, the parameter should be the previous messages.
        mm = new MessageManager(new ArrayList<>());
    }

    /**
     * This method if for Speaker to send the message to the target user email from current user.
     *
     * @param targetEmail The email address of the target user.
     * @param content     The content of the message.
     * @return boolean True iff the message is sent successfully.
     */
    public boolean speakerSendSingleMessage(String targetEmail, String content) {
        if (userType.equals("Speaker") && mm.validateResponse(userEmail, targetEmail)) {
            mm.singleMessageRequest(userEmail, targetEmail, content);
            return true;
        }
        return false;
    }

    /**
     * This method sends the message to the target user email from current user, the method is for attendee.
     *
     * @param targetEmail The email address of the target user.
     * @param content     The content of the message.
     * @return boolean True iff the message is sent successfully.
     */
    public boolean attendeeSendSingleMessage(String targetEmail, String content) {
        String targetIdentity = UserAccountManager.getEmailToIdentity(targetEmail);
        if (userType.equals("Attendee") && !targetIdentity.equals("Organizer")) {
            mm.singleMessageRequest(userEmail, targetEmail, content);
            return true;
        }
        return false;
    }

    /**
     * This method sends the message to the target user email from current user, the method is for organizer.
     *
     * @param targetEmail The email address of the target user.
     * @param content     The content of the message.
     * @return boolean True iff the message is sent successfully.
     */
    public boolean organizerSendSingleMessage(String targetEmail, String content) {
        String targetIdentity = UserAccountManager.getEmailToIdentity(targetEmail);
        if (!targetIdentity.equals("Organizer")) {
            mm.singleMessageRequest(userEmail, targetEmail, content);
            return true;
        }
        return false;
    }

    /**
     * This method sends message to a group of people with the target identity.
     *
     * @param targetIdentity The target identity group.
     * @param content        The content of the message.
     * @return boolean True iff the message is sent successfully.
     */
    public boolean sendMultipleMessage(String targetIdentity, String content) {
        if (userType.equals("Organizer")) {
            List<String> emails = mm.OrganizerGenerateEmail(targetIdentity);
            mm.multipleMessageRequest(userEmail, emails, content);
            return true;
        }
        return false;
    }

    /**
     * This method sends message to a group of people who sign up for the events specified in eventIds.
     *
     * @param eventIds The event ids we want to send message to.
     * @param content  The content of the message.
     * @return boolean True iff the message is sent successfully.
     */
    public boolean sendMultipleMessage(ArrayList<Integer> eventIds, String content) {
        if (userType.equals("Speaker")) {
            List<String> emails = mm.SpeakerGenerateEmail(eventIds);
            mm.multipleMessageRequest(userEmail, emails, content);
            return true;
        }
        return false;
    }

    /**
     * This method is to generate all email addresses that the current user can send message to.
     *
     * @return ArrayList All user email addresses that the current user can send message to.
     */
    public List<String> generateEmailList() {
        List<String> allAttendee = UserAccountManager.getAllAvailableEmails("Attendee");
        List<String> allSpeaker = UserAccountManager.getAllAvailableEmails("Speaker");
        List<String> emails;
        if (userType.equals("Attendee") || userType.equals("Organizer")) {
            emails = allAttendee;
            emails.addAll(allSpeaker);
        } else {
            emails = new ArrayList<>();
            List<String> allEmails = UserAccountManager.getAllEmails();
            for (String e : allEmails) {
                if (mm.validateResponse(userEmail, e)) {
                    emails.add(e);
                }
            }
        }
        return emails;
    }

    /**
     * This method is to delete a certain message.
     *
     * @param messageId This represents the id of the message to be modified.
     * @return boolean True iff the message is deleted successfully.
     */
    public boolean deleteMessage(int messageId) {
        if (!mm.idToStatus(messageId).equals("")) {
            mm.delete(messageId);
            return true;
        }
        return false;
    }

    /**
     * This method is to archive a certain message.
     *
     * @param messageId This represents the id of the message to be modified.
     * @return boolean True iff the message is archived successfully.
     */
    public boolean archiveMessage(int messageId) {
        if (!mm.idToStatus(messageId).equals("delete") && !mm.idToStatus(messageId).equals("")) {
            mm.archive(messageId);
            return true;
        }
        return false;
    }

    /**
     * This method is to mark unread of a certain message.
     *
     * @param messageId This represents the id of the message to be modified.
     * @return boolean True iff the message is marked unread successfully.
     */
    public boolean unreadMessage(int messageId) {
        if (!mm.idToStatus(messageId).equals("delete") && !mm.idToStatus(messageId).equals("")) {
            mm.unread(messageId);
            return true;
        }
        return false;
    }

    /**
     * This method is to generate all unread emails for the current user.
     *
     * @return StringBuilder The string of all unread emails.
     */
    public StringBuilder generateUnreadMessage() {
        return mm.generateMessage(userEmail, "unread");
    }

    /**
     * This method is to generate all archive emails for the current user.
     *
     * @return StringBuilder The string of all archived emails.
     */
    public StringBuilder generateArchiveMessage() {
        return mm.generateMessage(userEmail, "archive");
    }

    public StringBuilder generateReadMessage() {
        return mm.generateMessage(userEmail, "read");
    }

    /**
     * This method is to save current message system to database.
     */
//  public void saveMessage() {
//    ig.write(mm.getMessages());
//  }
}
