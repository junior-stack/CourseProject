package Entity;

public class Admin extends User{
  /**
   * This method creates an Instance of Attendee. Username, password phone, email are required. Also
   * an Admin has an identity of "Admin".
   *
   * @param username
   * @param password
   * @param phone
   * @param email
   */
  public Admin(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.type = "Admin";
  }

}
