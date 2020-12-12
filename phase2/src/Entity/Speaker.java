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
   * @param username the name of this speaker
   * @param password the password of this speaker account
   * @param phone    the phone number of this speaker
   * @param email    the email address of this speaker
   */

  public Speaker(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.type = "Speaker";
  }


}

