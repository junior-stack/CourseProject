package Entity;
/*
    Table for many to many relationship between event and user
 */

import com.j256.ormlite.field.DatabaseField;

public class UserEvent {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(foreign=true, columnName="user_id")
    User user;
    @DatabaseField(foreign = true, columnName = "event_id")
    Event event;
    public UserEvent(){

    }
    public UserEvent(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
