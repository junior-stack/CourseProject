package Entity;

/**
 * This class represents an Organizer.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class Organizer extends User {

  /**
   * This method creates an Instance of Organizer. Username, password phone, email are required.
   * Also an Organizer has an identity of "Organizer".
   *
   * @param username
   * @param password
   * @param phone
   * @param email
   */
  public Organizer(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.identity = "Organizer";
  }

  /**
   * This method return whether other Object is equals to this Organizer. They are equal when the
   * Object is an instance of Organizer and they have the same userId, password, username, events
   * list, email, phone and identity.
   *
   * @param other
   * @return boolean of whether other Object is equals to this Organizer.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Organizer)) {
      return false;
    }
    Organizer o = (Organizer) other;
    return o.getUserId() == this.getUserId() && o.getPassword().equals(this.getPassword()) && o
        .getUsername().equals(this.getUsername())
        && o.getEvents().equals(this.getEvents()) && o.getEmail().equals(this.getEmail()) && o
        .getPhone().equals(this.getPhone()) && o.getIdentity().equals(this.getIdentity());
  }
}
