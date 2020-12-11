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
   * @param username  username of the Attendee
   * @param password  password of the Attendee
   * @param phone     phone of the Attendee
   * @param email     email of the Attendee
   */
  public Attendee(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.type = "Attendee";
  }

}
