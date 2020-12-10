package Dao;

import Entity.Room;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

  private static Dao<Room, Integer> instance;

  private RoomDao() {

  }

  public static Dao<Room, Integer> getInstance() {
    if (instance == null) {
      System.out.println("Trying to access dao before initialization");
    }
    return instance;
  }

  public static void init(ConnectionSource conn) throws Exception {
    instance = DaoManager.createDao(conn, Room.class);
    // if you need to create the table
    TableUtils.createTableIfNotExists(conn, Room.class);
  }

  public static void truncate() {
    String tableName = "room";
    String truncateQuery = "DELETE FROM " + tableName + ";";

    try {
      RoomDao.getInstance().executeRaw(truncateQuery);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static List<Room> getAll() {

    try {
      return instance.queryForAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public static void saveAll(List<Room> objs) {
    truncate();

    for (Room o : objs) {
      try {
        instance.createOrUpdate(o);
      } catch (Exception err) {
        err.printStackTrace();
      }
    }
  }
}
