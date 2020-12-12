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

  /**
   * the constructor of RoomDao
   */
  private RoomDao() {

  }

  /**
   * Access the database at the start of the program
   *
   * @return Dao<Room, Integer> the content stored from database of event
   */
  public static Dao<Room, Integer> getInstance() {
    if (instance == null) {
      System.out.println("Trying to access dao before initialization");
    }
    return instance;
  }

  /**
   * Initialize the connection to database
   *
   * @param conn the database
   * @throws Exception occurs when the connection fails
   */
  public static void init(ConnectionSource conn) throws Exception {
    instance = DaoManager.createDao(conn, Room.class);
    // if you need to create the table
    TableUtils.createTableIfNotExists(conn, Room.class);
  }

  /**
   * Reformat to SQL Language
   */
  public static void truncate() {
    String tableName = "room";
    String truncateQuery = "DELETE FROM " + tableName + ";";

    try {
      RoomDao.getInstance().executeRaw(truncateQuery);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Return the list of Room for Room
   *
   * @return List<Room> list of Room
   */
  public static List<Room> getAll() {

    try {
      return instance.queryForAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * Save the program information into the database
   *
   * @param objs the item you want to store
   */
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
