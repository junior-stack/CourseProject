package Entity;

/**
 * This class represents an Speaker.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class Speaker extends User {

  /**
   * This method creates an Instance of Speaker. Username, password phone, email are required. Also
   * a Speaker has an identity of "Speaker".
   *
   * @param username
   * @param password
   * @param phone
   * @param email
   */
  public Speaker(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.identity = "Speaker";
  }

  /**
   * This method return whether other Object is equals to this Speaker. They are equal when the
   * Object is an instance of Speaker and they have the same userId, password, username, events
   * list, email, phone and identity.
   *
   * @param other
   * @return boolean of whether other Object is equals to this Speaker.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Speaker)) {
      return false;
    }
    Speaker o = (Speaker) other;
    return o.getUserId() == this.getUserId() && o.getPassword().equals(this.getPassword()) && o
        .getUsername().equals(this.getUsername())
        && o.getEvents().equals(this.getEvents()) && o.getEmail().equals(this.getEmail()) && o
        .getPhone().equals(this.getPhone()) && o.getIdentity().equals(this.getIdentity());
  }
}

