package Entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an Attendee.
 *
 * @author Jun Xing
 * @version 1.0
 */
@DatabaseTable(tableName = "user")
public class User implements Schedulable{

  private static int counter = 0;
  @DatabaseField(columnName = "type")
  protected String type = "";
  @DatabaseField(columnName = "schedule", dataType = DataType.SERIALIZABLE)
  protected ArrayList<ArrayList<Time>> schedule = new ArrayList<>();
  @DatabaseField(id = true)
  private int userId = 0;
  @DatabaseField(columnName = "username")
  private String username = "";
  @DatabaseField(columnName = "password")
  private String password = "";
  @DatabaseField(columnName = "phone")
  private String phone = "";
  @DatabaseField(columnName = "email")
  private String email = "";
  @DatabaseField(columnName = "events", dataType = DataType.SERIALIZABLE)
  private ArrayList<Integer> events = new ArrayList<>();

  /**
   * default contractor of User class
   */
  public User() {
  }

  /**
   * This method creates an instance of User, In order to do this, username, password, phone, and
   * email are required. Also, a unique userId and a event list are generated and each Users has an
   * Identity of Attendee or Organizer or Speaker.
   *
   * @param username  the username of the user
   * @param password  the password of the user
   * @param phone     the phone of the user
   * @param email     the email of the user
   */
  public User(String username, String password, String phone, String email) {
    this.userId = counter;
    this.username = username;
    this.password = password;
    this.phone = phone;
    this.email = email;
    this.getEvents();
    counter++;
  }

  /**
   * This method sets a User's counter.
   *
   * @param counter
   */
  public static void setCounter(int counter) {
    User.counter = counter;
  }

  public ArrayList<ArrayList<Time>> getSchedule() {
    if (schedule == null){
      schedule = new ArrayList<>();
    }
    return schedule;
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
    if (this.events != null) {
      return this.events;
    }
    this.events = new ArrayList<>();
    return this.events;
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

  /**
   * This method returns a string representation of the User, including its userId, username,
   * password, phone, email, related events and identity.
   *
   * @return a string representation of the User
   */
  @Override
  public String toString() {
    return "User{" +
        "type='" + type + '\'' +
        ", userId=" + userId +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", phone='" + phone + '\'' +
        ", email='" + email + '\'' +
        ", events=" + events +
        ", schedule=" + getSchedule() +
        '}';
  }

  /**
   * This method return whether other User is equals to this User. They are equal when the Object
   * are the same userId.
   *
   * @param other
   * @return boolean of whether other Object is equals to this Speaker.
   */
  @Override
  public boolean equals(Object other) {
    User u = (User) other;
    return u.getUserId() == this.getUserId() && (u.getEmail().equals(this.getEmail()));
  }

  /**
   * The hashcode method for user since we used it as key in the hashmap
   * @return userid
   */
  @Override
  public int hashCode() {
    return this.getUserId();
  }

  /**
   * This method checks whether this speaker has a schedule at a given time period
   *
   * @param start the start time of the given time period
   * @param end   the end time of the given time period
   */
  @Override
  public boolean CheckSchedulable(Time start, Time end) {
    for (ArrayList<Time> t : getSchedule()) {
      if (start.compareTo(t.get(0)) >= 0 && start.compareTo(t.get(1)) < 0) {
        return false;
      } else if (end.compareTo(t.get(0)) > 0 && end.compareTo(t.get(1)) <= 0) {
        return false;
      } else if (start.compareTo(end) >= 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method adds a schedule to the speakers' schedule list
   *
   * @param start the start time of the given time period
   * @param end   the end time of the given time period
   */

  @Override
  public void giveSchedulableNewSchedule(Time start, Time end) {
    ArrayList<Time> tmp = new ArrayList<>();
    tmp.add(start);
    tmp.add(end);
    schedule.add(tmp);
  }

  /**
   * This method deletes a schedule time from the speaker's schedule list
   *
   * @param start the start time of the given schedule
   * @param end   the end time of the given schedule
   * @return boolean whether the deletion is successful
   */
  @Override
  public boolean delSchedulableSchedule(Time start, Time end) {
    for (ArrayList<Time> t : getSchedule()) {
      if (t.get(0).equals(start) && t.get(1).equals(end)) {
        getSchedule().remove(t);
        return true;
      }
    }
    return false;
  }


  /**
   * @return ArrayList<ArrayList < Time>> a list of the speaker's schedule times which contains
   * start time and end time
   */
  @Override
  public ArrayList<ArrayList<Time>> getScheduleableSchedulelist() {
    return getSchedule();
  }

  /**
   * @param sch the id of this schedulable, it is a speakerID
   * @return String the information about this schedulable instance
   */
  @Override
  public String get_sch_info(int sch) {
    return getSchedule().toString();
  }

  /**
   * @return Integer the id of this schedulable. It is SpeakerId
   */
  @Override
  public Integer give_id() {
    return this.getUserId();
  }

  @Override
  public String get_sch_info() {
    return this.toString();
  }

}



