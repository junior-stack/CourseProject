package Entity;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an Room.
 *
 * @author Jun Xingpackage UI;
 *
 * import Entity.Room;
 * import Dao.*;
 * import Entity.Speaker;
 * import com.j256.ormlite.jdbc.JdbcConnectionSource;
 * import com.j256.ormlite.support.ConnectionSource;
 *
 * class Main{
 *     private final static String DB_URL = "jdbc:sqlite:conference.sqlite.db";
 *     public static void main(String args[]) throws Exception{
 *         JdbcConnectionSource connectionSource = null;
 *         try {
 *             // create our data-source for the database
 *             connectionSource = new JdbcConnectionSource(DB_URL);
 *             // setup our database and DAOs
 *             setUpDaos(connectionSource);
 *             Room rm1 = new Room(123, 20);
 *             RoomDao.getInstance().createOrUpdate(rm1);
 *             Speaker speaker = new Speaker("John", "pass", "1234", "xx@yy.com");
 *             UserDao.getInstance().createOrUpdate(speaker);
 *             // read and write some data
 *             System.out.println("\n\nIt works\n\n");
 *         } catch (Exception e){
 *             e.printStackTrace();
 *         }finally {
 *             // destroy the data source which should close underlying connections
 *             if (connectionSource != null) {
 *                 connectionSource.close();
 *             }
 *         }
 *     }
 *     public static void setUpDaos(ConnectionSource conn) throws Exception{
 *         RoomDao.init(conn);
 *         UserDao.init(conn);
 *     }
 * }
 * @version 1.0
 */
@DatabaseTable(tableName = "room")
public class Room implements Serializable, Schedulable {
    @DatabaseField(id = true)
    private int roomName = 0;
    @DatabaseField(columnName = "capacity")
    private int capacity = 0;
    public HashMap<ArrayList<Time>, Integer> schedule = new HashMap<>();

    /**
     * This method creates an Instance of Room, a Room has a roomId and capacity.
     *
     * @param roomName
     */
    public Room(int roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public Room() {

    }

    /**
     * This method returns a Room's roomId.
     *
     * @return Room's roomId
     */
    public int getRoomName() {
        return roomName;
    }

    /**
     * This method returns a Room's capacity.
     *
     * @return Room's capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * This method sets a Room's capacity.
     *
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //public int getRemainingSpot() {return remainingSpot; }
    //public void setRemainingSpot(int remainingSpot) {this.remainingSpot = remainingSpot;}

    /**
     * This method returns a string representing the Room including its roomId and capacity.
     *
     * @return a string representing the Room
     */
    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomName +
                ", capacity=" + capacity +
                '}';
    }

    /**
     * This method returns whether other Object is equals to this Room. They are equal when the Object
     * is an instance of Room and they have the same roomId and capacity.
     *
     * @param other
     * @return boolean of whether other Object is equals to this Attendee.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Room)) {
            return false;
        }
        return this.getRoomName() == ((Room) other).getRoomName() && this.getCapacity() == ((Room) other)
                .getCapacity();
    }

    /**
     * This method returns the integer hashed value of the Room which is exactly the Room's roomId.
     *
     * @return integer hashed value of the Room
     */
    @Override
    public int hashCode() {
        return this.getRoomName();
    }

    @Override
    public boolean CheckSchedulable(Time start, Time end) {
        for (ArrayList<Time> t : schedule.keySet()) {
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

    @Override
    public void giveSchedulableNewSchedule(Time start, Time end) {
        ArrayList<Time> tmp = new ArrayList<>();
        tmp.add(start);
        tmp.add(end);
        schedule.put(tmp, this.getCapacity());
    }

    @Override
    public boolean delSchedulableSchedule(Time start, Time end) {
        for (ArrayList<Time> t : schedule.keySet()) {
            if (t.get(0).equals(start) && t.get(1).equals(end)) {
                schedule.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<ArrayList<Time>> getScheduleableSchedulelist() {
        ArrayList<ArrayList<Time>> tmp = new ArrayList<>();
        tmp.addAll(schedule.keySet());
        return tmp;
    }

    @Override
    public String get_sch_info(int sch) {
        return schedule.toString();
    }

    @Override
    public Integer give_id() {
        return this.getRoomName();
    }

    @Override
    public String get_sch_info() {
        return this.toString();
    }

    public boolean DecreaseRemainingspot(Time start, Time end) {
        for (ArrayList<Time> t : schedule.keySet()) {
            if (t.get(0).equals(start) && t.get(1).equals(end)) {
                schedule.replace(t, schedule.get(t) - 1);
                return true;
            }
        }
        return false;
    }

    public boolean IncreaseRemainingspot(Time start, Time end) {
        for (ArrayList<Time> t : schedule.keySet()) {
            if (t.get(0).equals(start) && t.get(1).equals(end)) {
                schedule.replace(t, schedule.get(t) + 1);
                return true;
            }
        }
        return false;
    }

    public Integer getRemainingSpot(Time start, Time end) {
        ArrayList<Time> tmp = new ArrayList<>();
        tmp.add(start);
        tmp.add(end);
        return schedule.get(tmp);
    }
}
