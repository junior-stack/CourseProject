package Entity;
/*
    Table for many to many relationship between event and user
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user_event")
public class UserEvent {

  @DatabaseField(foreign = true, columnName = "user_id")
  public User user = null;
  @DatabaseField(foreign = true, columnName = "event_id")
  public Event event = null;
  @DatabaseField(id = true, columnName = "id")
  int id = 0;

  public UserEvent() {

  }

  public UserEvent(User user, Event event) {
    this.user = user;
    this.event = event;
    // https://math.stackexchange.com/questions/23503/create-unique-number-from-2-numbers
    // unique id from 2 ids
    int a = this.user.getUserId();
    int b = this.event.eventId;
    this.id = (a + b) * (a + b + 1) / 2 + b;
  }
}
