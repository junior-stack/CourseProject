package Dao;

import Entity.Event;
import Entity.User;
import Entity.UserEvent;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserEventDao {
    private static Dao<UserEvent, Integer> instance;
    private UserEventDao(){

    }
    public static Dao<UserEvent, Integer> getInstance(){
        if (instance == null) {
            System.out.println("Trying to access dao before initialization");
        }
        return instance;
    }
    public static void init(ConnectionSource conn) throws Exception{
        instance = DaoManager.createDao(conn, UserEvent.class);
        // if you need to create the table
        TableUtils.createTableIfNotExists(conn, UserEvent.class);
    }
    public static List<UserEvent> getAll(){
        try{
            return instance.queryForAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public static void saveAll(List<UserEvent> objs){
        truncate();
        for (UserEvent o:objs){
            try{
                instance.createOrUpdate(o);
            }catch (Exception err){
                err.printStackTrace();
            }
        }
    }

    public static void truncate(){
        String tableName = "user_event";
        String truncateQuery = "DELETE FROM "+tableName+";";
        try{
            UserEventDao.getInstance().executeRaw(truncateQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<User, ArrayList<Event>> getAsHashMap(){
        HashMap<User, ArrayList<Event>> result = new HashMap<>();
        try{
            for (UserEvent ue:instance.queryForAll()){
                ArrayList<Event> ae = result.getOrDefault(ue.user, new ArrayList<Event>());
                ae.add(ue.event);
                result.put(ue.user, ae);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static void saveHashMap(HashMap<User, ArrayList<Event>> userschedule){
        // https://mkyong.com/java8/java-8-foreach-examples/
        try{
            truncate();
            userschedule.forEach((user, ae) -> {
                ae.forEach((e)->{
                    try{
                        UserEvent ue = new UserEvent(user, e);
                        UserEventDao.getInstance().createOrUpdate(ue);
                    }catch (Exception err){
                        err.printStackTrace();
                    }
                });
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
