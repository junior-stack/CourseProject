package Controller;


import UseCase.MessageManager;
import java.util.List;
import java.util.Map;

public class MessageController {

  private MessageManager mm;

  public MessageController(String email, Map<String, Map<String, List<String>>> previousMessageStorage) {
    mm = new MessageManager(email, previousMessageStorage);
  }

  // generate all user 可以单发的emails
  public List<String> generateEmailList(){
    return mm.generateEmail();
  }


  // String message, String mode: 单发/群发, Speaker 群发 给 eventIds 表示想要群发的event, Organizer 群发给 targetIdentity
  // either "Attendee" or "Organizer", 表示群发的种类。当parameter 用不到的时候给 empty string or empty list.
  public boolean sendMessages(String mode, String message, String email, String targetIdentity,
      List<Integer> eventIds) {
    return mm.sendMessage(mode, message, email, targetIdentity, eventIds);
  }

  // read emails
  public Map<String, List<String>> readAllMessages() {
    return mm.readMessages();
  }

  // send emails
  public Map<String, List<String>> sentAllMessages() {
    return mm.sentMessages();
  }
}
