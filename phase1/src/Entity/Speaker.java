package Entity;

import java.io.Serializable;

public class Speaker extends User {

  public Speaker(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.identity = "Speaker";
  }
}
