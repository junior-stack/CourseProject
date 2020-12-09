package Dao;

import Entity.Message;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

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
}
