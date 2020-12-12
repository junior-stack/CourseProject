package UseCase;


import Entity.Event;
import Entity.User;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user_event")
public class UserEventMapper {

  @DatabaseField(foreign = true, columnName = "user_id")
  public User user = null;
  @DatabaseField(foreign = true, columnName = "event_id")
  public Event event = null;
  @DatabaseField(id = true, columnName = "id")
  int id = 0;

  /**
   * Constructor for UserEventMapper
   */
  public UserEventMapper() {

  }

  /**
   *  Constructor for UserEVentMapper
   * @param user target user
   * @param event the list of user he attends
   */
  public UserEventMapper(User user, Event event) {
    this.user = user;
    this.event = event;

    int a = this.user.getUserId();
    int b = this.event.getId();
    this.id = (a + b) * (a + b + 1) / 2 + b;
  }
}
