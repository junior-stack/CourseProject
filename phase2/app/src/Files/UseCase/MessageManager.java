package UseCase;

import Entity.Event;
import Entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a MessageManager.
 *
 * @author Zhongyuan Liang & Jiahao Zhang
 * @version 1.0
 */
public class MessageManager {

    public static HashMap<String, HashMap<String, List<String>>> messageStorage;

    private final User user;
    private final String userType;
    private final Map<String, String> emailToIdentity;

    /**
     * This is a constructor for MessageManager.
     *
     * @param email                  This represents the email address of the current user.
     * @param previousMessageStorage This represents all messages before the program restarts.
     */
    public MessageManager(String email,
                          HashMap<String, HashMap<String, List<String>>> previousMessageStorage) {
        messageStorage = previousMessageStorage;
        user = UserAccountManager.getUserFromEmail(email);
        userType = user.getIdentity();
        emailToIdentity = UserAccountManager.getEmailToIdentityMap();
    }

    /**
     * Current user Send message to the target email address.
     *
     * @param email   This is the target email address.
     * @param message This is the message to be sent.
     */
    public void singleMessageRequest(String email, String message) {
        String localEmail = user.getEmail();
        if (messageStorage.containsKey(email)) {
            if (!messageStorage.get(email).containsKey(localEmail)) {
                messageStorage.get(email).put(localEmail, new ArrayList<>());
            }
        } else {
            messageStorage.put(email, new HashMap<>());
            messageStorage.get(email).put(localEmail, new ArrayList<>());
        }
        messageStorage.get(email).get(localEmail).add(message);
    }

    /**
     * Current user send messages to a list of target email addresses.
     *
     * @param emails  This is the list containing all target email addresses.
     * @param message This is the message to be sent.
     */
    public void multipleMessageRequest(List<String> emails, String message) {
        for (String e : emails) {
            singleMessageRequest(e, message);
        }
    }

    /**
     * Current user read all messages that the user has received.
     *
     * @return Map This returns a map where each key represents another user email address and the
     * corresponding value is a list containing all messages sent by the user in the key.
     */
    public Map<String, List<String>> readMessages() {
        if (!messageStorage.containsKey(user.getEmail())) {
            return new HashMap<>();
        }
        return messageStorage.get(user.getEmail());
    }

    /**
     * Current user read all messages that the user has sent.
     *
     * @return Map This returns a map where each key represents another user email address and the
     * corresponding value is a list containing all messages sent by the current user to the user in
     * the key.
     */
    public Map<String, List<String>> sentMessages() {
        Map<String, List<String>> result = new HashMap<>();
        for (String e : messageStorage.keySet()) {
            for (String email : messageStorage.get(e).keySet()) {
                if (email.equals(user.getEmail())) {
                    if (result.containsKey(email)) {
                        result.get(email).addAll(messageStorage.get(e).get(email));
                    } else {
                        result.put(e, messageStorage.get(e).get(email));
                    }
                }
            }
        }
        return result;
    }

    /**
     * Attendee or Organizer send a single message to the target email address.
     *
     * @param email   This is the target email.
     * @param message This is the message to be sent.
     * @return boolean This returns true iff the message is sent successfully, otherwise false.
     */
    public boolean attendeeOrganizerSingleMessage(String email, String message) {
        if (emailToIdentity.get(email).equals("Attendee") || emailToIdentity.get(email)
                .equals("Speaker")) {
            singleMessageRequest(email, message);
            return true;
        }
        return false;
    }

    /**
     * Speaker respond a single message to the target email address.
     *
     * @param email   This is the target email.
     * @param message This is the message to be sent.
     * @return boolean This returns true iff the message is sent successfully, otherwise false.
     */
    public boolean speakerRespondMessage(String email, String message) {
        List<String> messages = MessageManager.messageStorage.get(user.getEmail()).get(email);
        if (messages != null) {
            singleMessageRequest(email, message);
            return true;
        }
        return false;
    }

    /**
     * Organizer send messages to all Speakers and Attendees.
     *
     * @param targetIdentity This is either "Speakers" or "Attendees".
     * @param message        This is the message to be sent.
     * @return boolean This returns true iff the message is sent successfully, otherwise false.
     */
    public boolean organizerMultipleMessage(String targetIdentity, String message) {
        if (targetIdentity.equals("Attendees")) {
            List<String> emails = new ArrayList<>();
            for (String e : emailToIdentity.keySet()) {
                if (emailToIdentity.get(e).equals("Attendee")) {
                    emails.add(e);
                }
            }
            multipleMessageRequest(emails, message);
            return true;
        }
        if (targetIdentity.equals("Speakers")) {
            List<String> emails = new ArrayList<>();
            for (String e : emailToIdentity.keySet()) {
                if (emailToIdentity.get(e).equals("Speaker")) {
                    emails.add(e);
                }
            }
            multipleMessageRequest(emails, message);
            return true;
        }
        return false;
    }

    /**
     * Speaker send messages to all Attendees in the given events.
     *
     * @param eventIds This is a list containing all eventIds that the Speaker wants to send message
     *                 to.
     * @param message  This is the message to be sent.
     * @return boolean This returns true iff the message is sent successfully, otherwise false.
     */
    public boolean speakerMultipleMessage(List<Integer> eventIds, String message) {
        List<Event> events = EventManager.eventpool;
        List<Event> e = new ArrayList<>();
        for (Event temp : events) {
            if (eventIds.contains(temp.getId())) {
                e.add(temp);
            }
        }
        for (Event temp2 : e) {
            if (temp2.getSpeaker() != user.getUserId()) {
                return false;
            }
        }
        List<String> emails = new ArrayList<>();
        List<User> userList = UserAccountManager.userList;
        Map<Integer, String> idToEmail = new HashMap<>();
        for (User user : userList) {
            idToEmail.put(user.getUserId(), user.getEmail());
        }
        for (Event event : e) {
            for (int userid : event.getAllAttendee()) {
                emails.add(idToEmail.get(userid));
            }
        }
        multipleMessageRequest(emails, message);
        return true;
    }

    /**
     * This method is to generate email addresses that the current user could send the message to.
     *
     * @return List This returns a list of email addresses that the current user could send the
     * message to.
     */
    public List<String> generateEmail() {
        List<String> lst = new ArrayList<>();
        for (String e : emailToIdentity.keySet()) {
            if (userType.equals("Attendee")) {
                if (emailToIdentity.get(e).equals("Attendee")) {
                    lst.add(e);
                }
            } else if (userType.equals("Organizer")) {
                if (emailToIdentity.get(e).equals("Attendee") || emailToIdentity.get(e).equals("Speaker")) {
                    lst.add(e);
                }
            }
        }
        if (userType.equals("Speaker")) {
            Map<String, List<String>> temp = MessageManager.messageStorage.get(user.getEmail());
            for (String email : temp.keySet()) {
                if (emailToIdentity.get(email).equals("Attendee")) {
                    lst.add(email);
                }
            }
        }
        return lst;
    }

    /**
     * This is the method used to send message(s) by the current user.
     *
     * @param mode           This is either "Single" or "Multiple".
     * @param message        This is the message to be sent.
     * @param email          When mode is "Single", this represents the target email address.
     * @param targetIdentity When Organizer send multiple messages, this represents the group of
     *                       people who will receive the message. This is either "Attendees" or
     *                       "Speakers".
     * @param eventIds       When Speaker send multiple messages, this represents the group of events
     *                       that the Speaker wants to send message to.
     * @return boolean This returns true iff the message is sent successfully, otherwise false.
     */
    public boolean sendMessage(String mode, String message, String email, String targetIdentity,
                               List<Integer> eventIds) {
        if (mode.equals("Single")) {
            if (userType.equals("Attendee") || (userType.equals("Organizer"))) {
                return attendeeOrganizerSingleMessage(email, message);
            }
            return speakerRespondMessage(email, message);
        }
        if (mode.equals("Multiple")) {
            if (userType.equals("Attendee")) {
                return false;
            } else if (userType.equals("Organizer")) {
                return organizerMultipleMessage(targetIdentity, message);
            } else {
                return speakerMultipleMessage(eventIds, message);
            }
        }
        return false;
    }
}
