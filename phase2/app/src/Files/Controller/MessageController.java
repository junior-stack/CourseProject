package Controller;

import Gateway.Igateway;
import Gateway.MapGateway;
import Gateway.MessageDataAccess;
import UseCase.MessageManager;
import UseCase.UserAccountManager;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageController {

  private MessageManager mm;

  //private Igateway ig = new MessageDataAccess();

  private String userType;
  private String userEmail;

  public MessageController(String useremail, String userType) {

    this.userType = userType;
    this.userEmail = useremail;
    //HashMap<String, HashMap<String, List<String>>> previousMessageStorage = ig.read();
    // When the gateway is finished, the parameter should be the previous messages.
    mm = new MessageManager(new ArrayList<>());
  }

  public boolean sendSingleMessage(String targetEmail, String content){
    String targetIdentity = UserAccountManager.getEmailToIdentity(targetEmail);
    if (userType.equals("Speaker") && mm.validateResponse(userEmail, targetEmail)){
      mm.singleMessageRequest(userEmail, targetEmail, content);
      return true;
    } else if (userType.equals("Attendee") && !targetIdentity.equals("Organizer")){
      mm.singleMessageRequest(userEmail, targetEmail, content);
      return true;
    } else {
      if (!targetIdentity.equals("Organizer")){
        mm.singleMessageRequest(userEmail, targetEmail, content);
        return true;}
    }
    return false;
  }

  public boolean sendMultipleMessage(String targetIdentity, String content){
    if (userType.equals("Organizer")){
      ArrayList<String> emails = mm.OrganizerGenerateEmail(targetIdentity);
      mm.multipleMessageRequest(userEmail, emails, content);
      return true;
    }
    return false;
  }

  //overload
  public boolean sendMultipleMessage(ArrayList<Integer> eventIds, String content){
    if (userType.equals("Speaker")){
      ArrayList<String> emails = mm.SpeakerGenerateEmail(eventIds);
      mm.multipleMessageRequest(userEmail, emails, content);
      return true;
    }
    return false;
  }

  public ArrayList<String> generateEmailList() {
    ArrayList<String> allAttendee= UserAccountManager.getAllAvailableEmails("Attendee");
    ArrayList<String> allSpeaker= UserAccountManager.getAllAvailableEmails("Speaker");
    ArrayList<String> emails;
    if (userType.equals("Attendee") || userType.equals("Organizer")){
      emails = allAttendee;
      emails.addAll(allSpeaker);
    } else {
      emails = new ArrayList<>();
      ArrayList<String> allEmails = UserAccountManager.getAllEmails();
      for (String e: allEmails){
        if (mm.validateResponse(userEmail, e)){
          emails.add(e);
        }
      }
    }
    return emails;
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
  public boolean sendMessages(String mode, String message, String email, String targetIdentity,
      List<Integer> eventIds) {
    return mm.sendMessage(mode, message, email, targetIdentity, eventIds);
  }

  /**
   * Current user read all messages that the user has received.
   *
   * @return Map This returns a map where each key represents another user email address and the
   * corresponding value is a list containing all messages sent by the user in the key.
   */
  public Map<String, List<String>> readAllMessages() {
    return mm.readMessages();
  }

  /**
   * Current user read all messages that the user has sent.
   *
   * @return Map This returns a map where each key represents another user email address and the
   * corresponding value is a list containing all messages sent by the current user to the user in
   * the key.
   */
  public Map<String, List<String>> sentAllMessages() {
    return mm.sentMessages();
  }

  /**
   * Save the current messages to the database for future view.
   */
  public void saveMessage() {
    ig.write(mm.getMessages());
  }
}
