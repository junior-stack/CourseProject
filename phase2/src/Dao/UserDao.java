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

  /**
   * the constructor of UserDao
   */
  private UserDao() {

  }

  /**
   * Access the database at the start of the program
   *
   * @return Dao<User, Integer> the content stored from database of event
   */
  public static Dao<User, Integer> getInstance() {
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
    instance = DaoManager.createDao(conn, User.class);
    TableUtils.createTableIfNotExists(conn, User.class);
  }

  /**
   * Reformat to SQL Language
   */
  public static void truncate() {
    String tableName = "user";
    String truncateQuery = "DELETE FROM " + tableName + ";";

    try {
      UserDao.getInstance().executeRaw(truncateQuery);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Return the list of User for UserAccountManager
   *
   * @return List<User> list of User
   */
  public static List<User> getAll() {

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
  public static void saveAll(List<User> objs) {
    truncate();

    for (User o : objs) {
      try {
        instance.createOrUpdate(o);
      } catch (Exception err) {
        err.printStackTrace();
      }
    }
  }
}
