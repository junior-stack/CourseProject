package Dao;

import Entity.Event;
import Entity.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

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
    public static void truncate(){
        String tableName = "event";
        String truncateQuery = "DELETE FROM "+tableName+";";
        try{
            EventDao.getInstance().executeRaw(truncateQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<Event> getAll(){
        try{
            return instance.queryForAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public static void saveAll(List<Event> objs){
        truncate();
        for (Event e:objs){
            try{
                instance.createOrUpdate(e);
            }catch (Exception err){
                err.printStackTrace();
            }
        }
    }
}
