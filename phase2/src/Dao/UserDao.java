package Dao;

import Entity.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

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
    public static void truncate(){
        String tableName = "user";
        String truncateQuery = "DELETE FROM "+tableName+";";
        try{
            UserDao.getInstance().executeRaw(truncateQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<User> getAll(){
        try{
            return instance.queryForAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public static void saveAll(List<User> objs){
        truncate();
        for (User o:objs){
            try{
                instance.createOrUpdate(o);
            }catch (Exception err){
                err.printStackTrace();
            }
        }
    }
}
