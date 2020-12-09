package Entity;

/**
 * This class represents an Attendee.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class Attendee extends User {

  /**
   * This method creates an Instance of Attendee. Username, password phone, email are required. Also
   * an Attendee has an identity of "Attendee".
   *
   * @param username
   * @param password
   * @param phone
   * @param email
   */
  public Attendee(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.type = "Attendee";
  }

  /**
   * This method return whether other Object is equals to this Attendee. They are equal when the
   * Object is an instance of Attendee and they have the same userId, password, username, events
   * list, email, phone and identity.
   *
   * @param other
   * @return boolean of whether other Object is equals to this Attendee.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Attendee)) {
      return false;
    }
    Attendee o = (Attendee) other;
    return o.getUserId() == this.getUserId() && o.getPassword().equals(this.getPassword()) && o
        .getUsername().equals(this.getUsername())
        && o.getEvents().equals(this.getEvents()) && o.getEmail().equals(this.getEmail()) && o
        .getPhone().equals(this.getPhone()) && o.getType().equals(this.getType());
  }
}
