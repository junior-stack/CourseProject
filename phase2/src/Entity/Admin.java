package Entity;

/**
 * Class representing Admin.
 * @author Jun Xing
 */
public class Admin extends User {

  /**
   * This method creates an Instance of Admin. Username, password phone, email are required. Also an
   * Admin has an identity of "Admin".
   *
   * @param username  username of the Admin
   * @param password  password of the Admin
   * @param phone     phone of the Admin
   * @param email     email of the Admin
   */
  public Admin(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.type = "Admin";
  }

}
