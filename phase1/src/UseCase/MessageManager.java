package UseCase;

import Entity.Event;
import Entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {

  public static Map<String, Map<String, List<String>>> messageStorage = new HashMap<>();
  private User user;
  private final String userType;
  private final Map<String, String> emailToIdentity = new HashMap<>();

  public MessageManager(String email){
    List<User> users = UserAccountManager.userList;
    for (User u : users){
      if (u.getEmail().equals(email)){
        user = u;}
    }
    assert user != null;
    userType = user.getIdentity();
    List<User> userList = UserAccountManager.userList;
    for (User user : userList) {
      emailToIdentity.put(user.getEmail(), user.getIdentity());
    }
  }

  public void singleMessageRequest(String email, String message) {
    String localEmail = user.getEmail();
    if (messageStorage.containsKey(email)) {
      if (messageStorage.get(email).containsKey(localEmail)) {
        messageStorage.get(email).get(localEmail).add(message);
      } else {
        messageStorage.get(email).put(localEmail, new ArrayList<>());
        messageStorage.get(email).get(localEmail).add(message);
      }
    } else {
      messageStorage.put(email, new HashMap<>());
      messageStorage.get(email).put(localEmail, new ArrayList<>());
      messageStorage.get(email).get(localEmail).add(message);
    }
  }


  public void multipleMessageRequest(List<String> emails, String message) {
    for (String e : emails) {
      singleMessageRequest(e, message);
    }
  }

  public Map<String, List<String>> readMessages() {
    if (!messageStorage.containsKey(user.getEmail())) {
      return null;
    }
    return messageStorage.get(user.getEmail());
  }

  public Map<String, List<String>> sentMessages() {
    Map<String, List<String>> result = new HashMap<>();
    for (String e : messageStorage.keySet()) {
      for (String email : messageStorage.get(e).keySet()) {
        if (email.equals(user.getEmail())) {
          if (result.containsKey(email)) {
            result.get(email).addAll(messageStorage.get(e).get(email));
          } else {result.put(e, messageStorage.get(e).get(email));}
        }
      }
    }
    return result;
  }

  // Attendee,Organizer 只能单发 message
  public boolean attendeeOrganizerSingleMessage(String email, String message) {
    if (emailToIdentity.get(email).equals("Attendee") || emailToIdentity.get(email)
            .equals("Speaker")) {
      singleMessageRequest(email, message);
      return true;
    }
    return false;
  }

  //Speaker responds to attendee
  public boolean speakerRespondMessage(String email, String message) {
    List<String> messages = MessageManager.messageStorage.get(user.getEmail()).get(email);
    if (messages != null) {
      singleMessageRequest(email, message);
      return true;
    }
    return false;
  }

  // Organizer 群发attendees, speakers.
  public boolean organizerMultipleMessage(String targetIdentity, String message) {
    if (targetIdentity.equals("Organizer")) {
      return false;
    }
    if (targetIdentity.equals("Attendees")) {
      List<String> emails = new ArrayList<>();
      for (String e : emailToIdentity.keySet()) {
        if (emailToIdentity.get(e).equals("Attendee")) {
          emails.add(e);
        }
      }
      multipleMessageRequest(emails, message);
    }
    if (targetIdentity.equals("Speakers")) {
      List<String> emails = new ArrayList<>();
      for (String e : emailToIdentity.keySet()) {
        if (emailToIdentity.get(e).equals("Speaker")) {
          emails.add(e);
        }
      }
      multipleMessageRequest(emails, message);
    }
    return true;
  }

  //Speaker 群发 attendees of one/multiple talks
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

  // generate all user 可以单发的emails
  public List<String> generateEmail(){
    List<String> lst = new ArrayList<>();
    for (String e : emailToIdentity.keySet()){
      if (userType.equals("Attendee")) {
        if (emailToIdentity.get(e).equals("Attendee")) {
          lst.add(e);}
      } else if (userType.equals("Organizer")){
        if (emailToIdentity.get(e).equals("Attendee") || emailToIdentity.get(e).equals("Speaker")) {
          lst.add(e); }
      } else {
        lst.addAll(MessageManager.messageStorage.get(user.getEmail()).keySet());
      }
    }
    return lst;
  }

  // String message, String mode: 单发/群发
  public boolean sendMessage(String mode, String message, String email, String targetIdentity,
                             List<Integer> eventIds) {
    if (mode.equals("single")) {
      if (userType.equals("Attendee") || (userType.equals("Organizer"))) {
        return attendeeOrganizerSingleMessage(email, message);
      }
      return speakerRespondMessage(email, message);
    } else {
      if (userType.equals("Attendee")) {
        return false;
      } else if (userType.equals("Organizer")) {
        return organizerMultipleMessage(targetIdentity, message);
      } else {return speakerMultipleMessage(eventIds, message);}
    }
  }
}
