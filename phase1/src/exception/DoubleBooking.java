package exception;

import Entity.Room;
import javafx.util.Pair;

import java.sql.Time;

public class DoubleBooking extends Exception{
    private Room rm;
    private Pair<Time, Time> scheduled_time;
    //当organizer 做double book 给它丢自定义exception.
    public DoubleBooking(Room rm, Pair<Time, Time> scheduled_time){
        super();
        this.rm = rm;
        this.scheduled_time = scheduled_time;
    }

    @Override
    public String getMessage() {
        return rm.toString() + "conflicts with " + scheduled_time;
    }
}
