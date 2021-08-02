package Dao;

import Entity.Event;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

  private static Dao<Event, Integer> instance;

  /**
   * the constructor of EventDao
   */
  private EventDao() {

  }

  /**
   * Access the database at the start of the program
   *
   * @return Dao<Event, Integer> the content stored from database of event
   */
  public static Dao<Event, Integer> getInstance() {
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
    instance = DaoManager.createDao(conn, Event.class);
    TableUtils.createTableIfNotExists(conn, Event.class);
  }

  /**
   * Reformat to SQL Language
   */
  public static void truncate() {
    String tableName = "event";
    String truncateQuery = "DELETE FROM " + tableName + ";";
    try {
      EventDao.getInstance().executeRaw(truncateQuery);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Return the list of events for EventManager
   *
   * @return List<Event> list of events
   */
  public static List<Event> getAll() {
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
  public static void saveAll(List<Event> objs) {
    truncate();
    for (Event e : objs) {
      try {
        instance.createOrUpdate(e);
      } catch (Exception err) {
        err.printStackTrace();
      }
    }
  }
}
