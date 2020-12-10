package UseCase;
/*
    Table for many to many relationship between event and user
 */

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

  public UserEventMapper() {

  }

  public UserEventMapper(User user, Event event) {
    this.user = user;
    this.event = event;

    int a = this.user.getUserId();
    int b = this.event.getId();
    this.id = (a + b) * (a + b + 1) / 2 + b;
  }
}
