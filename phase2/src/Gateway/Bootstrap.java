package Gateway;

import Entity.Event;
import Entity.Message;
import Entity.Room;
import Dao.*;
import Entity.Speaker;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.Time;


public class Bootstrap {
    private final static String DB_URL = "jdbc:sqlite:conference.sqlite.db";
    public static void bootstrap() throws Exception{
        JdbcConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource(DB_URL);
            // setup our database and DAOs
            setUpDaos(connectionSource);
            // comment out this part in production
            testData();
            // read and write some data
            System.out.println("\n\nIt works\n\n");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            // destroy the data source which should close underlying connections
            if (connectionSource != null) {
                connectionSource.close();
            }
        }
    }

    public static void setUpDaos(ConnectionSource conn) throws Exception{
        RoomDao.init(conn);
        UserDao.init(conn);
        MessageDao.init(conn);
        EventDao.init(conn);
    }

    public static void testData() throws Exception{
        testRoomAndUser();
        testEventAndMessage();
    }

    public static void testRoomAndUser() throws Exception{
        Room rm1 = new Room(123, 20);
        RoomDao.getInstance().createOrUpdate(rm1);
        Speaker speaker = new Speaker("John", "pass", "1234", "xx@yy.com");
        UserDao.getInstance().createOrUpdate(speaker);
    }

    public static void testEventAndMessage() throws Exception{
        long now = System.currentTimeMillis();
        Time start = new Time(now);
        Time end = new Time(now+1000000);
        Event event = new Event(123, start, end, "test-topic", 20);
        Event event2 = new Event(123, start, end, "test-topic2", 30);
        EventDao.getInstance().createOrUpdate(event);
        EventDao.getInstance().createOrUpdate(event2);
        Message m = new Message("xx@yy.com", "randomEmail", "test content");
        MessageDao.getInstance().createOrUpdate(m);
    }
}
