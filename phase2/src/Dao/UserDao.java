package Dao;

import Entity.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public final class UserDao {
    private static Dao<User, Integer> instance;
    private UserDao(){

    }
    public static Dao<User, Integer> getInstance(){
        if (instance == null) {
            System.out.println("Trying to access dao before initialization");
        }
        return instance;
    }
    public static void init(ConnectionSource conn) throws Exception{
        instance = DaoManager.createDao(conn, User.class);
        // if you need to create the table
        TableUtils.createTableIfNotExists(conn, User.class);
    }
}
