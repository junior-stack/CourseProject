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

  public boolean deleteMessage(int messageId){
    if (!mm.idToStatus(messageId).equals("")){
      mm.delete(messageId);
      return true;
    }
    return false;
  }

  public boolean archiveMessage(int messageId){
    if (!mm.idToStatus(messageId).equals("delete") && !mm.idToStatus(messageId).equals("")) {
      mm.archive(messageId);
      return true;
    }
    return false;
  }

  public boolean unreadMessage(int messageId){
    if (!mm.idToStatus(messageId).equals("delete") && !mm.idToStatus(messageId).equals("")) {
      mm.unread(messageId);
      return true;
    }
    return false;
  }

  public StringBuilder generateUnreadMessage(){
    return mm.generateMessage(userEmail, "unread");
  }

  public StringBuilder generateArchiveMessage(){
    return mm.generateMessage(userEmail, "archive");
  }


//  public void saveMessage() {
//    ig.write(mm.getMessages());
//  }
}
