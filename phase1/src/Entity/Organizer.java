package Entity;

import java.io.Serializable;

public class Organizer extends User {

  public Organizer(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.identity = "Organizer";
  }


}
