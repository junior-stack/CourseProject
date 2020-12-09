package Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an Attendee.
 *
 * @author Jun Xing
 * @version 1.0
 */
@DatabaseTable(tableName = "user")
public class User implements Serializable {

    private static int counter = 0;
    @DatabaseField(columnName = "type")
    protected String type ="";
    @DatabaseField(generatedId = true)
    private int userId = 0;
    @DatabaseField(columnName = "username")
    private String username = "";
    @DatabaseField(columnName = "password")
    private String password = "";
    @DatabaseField(columnName = "phone")
    private String phone = "";
    @DatabaseField(columnName = "email")
    private String email = "";
    private List<Integer> events = null;

    public User(){

    }

    /**
     * This method creates an instance of User, In order to do this, username, password, phone, and
     * email are required. Also, a unique userId and a event list are generated and each Users has an
     * Identity of Attendee or Organizer or Speaker.
     *
     * @param username
     * @param password
     * @param phone
     * @param email
     */
    public User(String username, String password, String phone, String email) {
        this.userId = counter;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.events = new ArrayList<>();
        counter++;
    }

    /**
     * default contractor of User class
     */
//    public User() {}
    /**
     * This method sets a User's counter.
     *
     * @param counter
     */
    public static void setCounter(int counter) {
        User.counter = counter;
    }

    /**
     * This method returns a User's Id.
     *
     * @return User's Id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This method returns a User's Username.
     *
     * @return User's Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method sets a User's Username.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method returns a User's Password.
     *
     * @return User's Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets a User's Password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method returns a User's Phone.
     *
     * @return User's Phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method sets a User's Phone.
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method returns a User's Email.
     *
     * @return User's Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets a User's Email.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method returns User's related events which means for an attendee, this should return
     * events it signed up for; for an organizer, this should return events it organized and for a
     * speaker, this should return events it's supposed to lecture.
     *
     * @return User's related events
     */
    public List<Integer> getEvents() {
        return events;
    }

    /**
     * This method add an event to the User's List of event.
     *
     * @param event_id
     */
    public void addEvents(int event_id) {
        this.events.add(event_id);
    }

    /**
     * This method returns a User's Identity which means return it's a Speaker, an Organizer or an
     * Attendee.
     *
     * @return User's Identity
     */
    public String getType() {
        return type;
    }

//  /**
//   * This method returns a string representation of the User, including its userId, username,
//   * password, phone, email, related events and identity.
//   *
//   * @return a string representation of the User
//   */
//  @Override
//  public String toString() {
//    return "User{" +
//        "userId=" + userId +
//        ", username='" + username + '\'' +
//        ", password='" + password + '\'' +
//        ", phone='" + phone + '\'' +
//        ", email='" + email + '\'' +
//        ", events=" + events +
//        ", Identity=" + type +
//        '}';
//  }
}



