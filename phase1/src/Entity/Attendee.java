package Entity;

import java.io.Serializable;

public class Attendee extends User {

  public Attendee(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.identity = "Attendee";
  }
}
