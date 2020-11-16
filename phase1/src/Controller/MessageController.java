package Controller;


import UseCase.MessageManager;
import java.util.List;
import java.util.Map;

public class MessageController {

  private MessageManager mm;

  public MessageController(String email, Map<String, Map<String, List<String>>> previousMessageStorage) {
    mm = new MessageManager(email, previousMessageStorage);
  }

  /**
   * This method is to generate email addresses that the current user could send the message to.
   * @return List This returns a list of email addresses that the current user could send the message to.
   */
  public List<String> generateEmailList() {
    return mm.generateEmail();
  }

  /**
   * This is the method used to send message(s) by the current user.
   * @param mode This is either "Single" or "Multiple".
   * @param message This is the message to be sent.
   * @param email When mode is "Single", this represents the target email address.
   * @param targetIdentity When Organizer send multiple messages, this represents the group of people who will receive
   *                       the message. This is either "Attendees" or "Speakers".
   * @param eventIds When Speaker send multiple messages, this represents the group of events that the Speaker wants
   *                 to send message to.
   * @return boolean This returns true iff the message is sent successfully, otherwise false.
   */
  public boolean sendMessages(String mode, String message, String email, String targetIdentity,
      List<Integer> eventIds) {
    return mm.sendMessage(mode, message, email, targetIdentity, eventIds);
  }

  /**
   * Current user read all messages that the user has received.
   * @return Map This returns a map where each key represents another user email address
   * and the corresponding value is a list containing all messages sent by the user in the key.
   */
  public Map<String, List<String>> readAllMessages() {
    return mm.readMessages();
  }

  /**
   * Current user read all messages that the user has sent.
   * @return Map This returns a map where each key represents another user email address
   * and the corresponding value is a list containing all messages sent by the current user to the user in the key.
   */
  public Map<String, List<String>> sentAllMessages() {
    return mm.sentMessages();
  }
}
