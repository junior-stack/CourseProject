package Exception;

import Entity.Event;
import Entity.User;

/**
 * Created by yezhou on 2020/11/14
 **/
public class SignupConflict extends Exception {

  private final User user;
  private final Event event;

  public SignupConflict(User u, Event e) {
    user = u;
    event = e;
  }

  @Override
  public String getMessage() {
    return user.toString() + "has time conflict with " + event.toString();
  }
}
