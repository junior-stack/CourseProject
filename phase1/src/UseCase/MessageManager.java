package UseCase;

import Entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {

  public static Map<String, Map<String, List<String>>> messageStorage = new HashMap<>();

  public void singleMessageRequest(User local, String email, String message) {
    String localEmail = local.getEmail();
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


  public void multipleMessageRequest(User local, List<String> emails, String message) {
    for (String e : emails) {
      singleMessageRequest(local, e, message);
    }
  }

  public Map<String, List<String>> readMessages(User u) {
    if (!messageStorage.containsKey(u.getEmail())) {
      return null;
    }
    return messageStorage.get(u.getEmail());
  }

  public Map<String, List<String>> sentMessages(User u) {
    Map<String, List<String>> result = new HashMap<>();
    for (String e : messageStorage.keySet()) {
      for (String email : messageStorage.get(e).keySet()) {
        if (email.equals(u.getEmail())) {
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
}
