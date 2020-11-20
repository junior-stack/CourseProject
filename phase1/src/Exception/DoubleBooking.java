package Exception;

import Entity.Room;
import java.sql.Time;
import java.util.ArrayList;

public class DoubleBooking extends Exception {

  private final Room rm;
  private final ArrayList<Time> scheduled_time;

  //当organizer 做double book 给它丢自定义exception.
  public DoubleBooking(Room rm, ArrayList<Time> scheduled_time) {
    super();
    this.rm = rm;
    this.scheduled_time = scheduled_time;
  }

  @Override
  public String getMessage() {
    return rm.toString() + "conflicts with " + scheduled_time;
  }
}
