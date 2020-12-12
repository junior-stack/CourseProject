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
    this.type = "Organizer";
  }

}
