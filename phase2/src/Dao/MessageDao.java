package Dao;

import Entity.Message;
import Entity.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    private static Dao<Message, Integer> instance;
    private MessageDao(){

    }
    public static Dao<Message, Integer> getInstance(){
        if (instance == null) {
            System.out.println("Trying to access dao before initialization");
        }
        return instance;
    }
    public static void init(ConnectionSource conn) throws Exception{
        instance = DaoManager.createDao(conn, Message.class);
        // if you need to create the table
        TableUtils.createTableIfNotExists(conn, Message.class);
    }
    public static void truncate(){
        String tableName = "message";
        String truncateQuery = "DELETE FROM "+tableName+";";
        try{
            MessageDao.getInstance().executeRaw(truncateQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<Message> getAll(){
        try{
            return instance.queryForAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public static void saveAll(List<Message> objs){
        truncate();
        for (Message o:objs){
            try{
                instance.createOrUpdate(o);
            }catch (Exception err){
                err.printStackTrace();
            }
        }
    }
}
