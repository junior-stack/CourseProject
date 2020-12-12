package Dao;

import Entity.Message;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {

  private static Dao<Message, Integer> instance;

  /**
   * the constructor of MessageDao
   */
  private MessageDao() {

  }

  /**
   * Access the database at the start of the program
   *
   * @return Dao<Message, Integer> the content stored from database of event
   */
  public static Dao<Message, Integer> getInstance() {
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
    instance = DaoManager.createDao(conn, Message.class);
    TableUtils.createTableIfNotExists(conn, Message.class);
  }


  /**
   * Reformat to SQL Language
   */
  public static void truncate() {
    String tableName = "message";
    String truncateQuery = "DELETE FROM " + tableName + ";";

    try {
      MessageDao.getInstance().executeRaw(truncateQuery);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Return the list of message for MessageManager
   *
   * @return List<Message> list of message
   */
  public static List<Message> getAll() {

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
  public static void saveAll(List<Message> objs) {
    truncate();
    for (Message o : objs) {
      try {
        instance.createOrUpdate(o);
      } catch (Exception err) {
        err.printStackTrace();
      }
    }
  }
}
