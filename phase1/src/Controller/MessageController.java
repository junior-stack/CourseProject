package Controller;


import UseCase.MessageManager;
import java.util.List;
import java.util.Map;

public class MessageController {

  private final MessageManager mm;

  public MessageController(String email) {
    mm = new MessageManager(email);
  }

  // generate all user 可以单发的emails
  public List<String> generateEmailList(){
    return mm.generateEmail();
  }


  // String message, String mode: 单发/群发
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
