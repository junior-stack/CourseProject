package Dao;

import Entity.Event;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class EventDao {
    private static Dao<Event, Integer> instance;
    private EventDao(){

    }
    public static Dao<Event, Integer> getInstance(){
        if (instance == null) {
            System.out.println("Trying to access dao before initialization");
        }
        return instance;
    }
    public static void init(ConnectionSource conn) throws Exception{
        instance = DaoManager.createDao(conn, Event.class);
        // if you need to create the table
        TableUtils.createTableIfNotExists(conn, Event.class);
    }
}
