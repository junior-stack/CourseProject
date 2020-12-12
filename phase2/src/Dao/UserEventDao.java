package Dao;

import Entity.Event;
import Entity.User;
import UseCase.UserEventMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserEventDao {

  private static Dao<UserEventMapper, Integer> instance;

  /**
   * the constructor of UserDao
   */
  private UserEventDao() {

  }

  /**
   * Access the database at the start of the program
   * @return Dao<UserEventMapper, Integer> the content stored from database of event
   */
  public static Dao<UserEventMapper, Integer> getInstance() {
    if (instance == null) {
      System.out.println("Trying to access dao before initialization");
    }
    return instance;
  }

  /**
   *  Initialize the connection to database
   * @param conn  the database
   * @throws Exception occurs when the connection fails
   */
  public static void init(ConnectionSource conn) throws Exception {
    instance = DaoManager.createDao(conn, UserEventMapper.class);
    // if you need to create the table
    TableUtils.createTableIfNotExists(conn, UserEventMapper.class);
  }


  /**
   * Return the list of User for UserEventMapper
   * @return List<UserEventManager> list of UserEventMapper
   */
  public static List<UserEventMapper> getAll() {

    try {
      return instance.queryForAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * Save the program information into the database
   * @param objs the item you want to store
   */
  public static void saveAll(List<UserEventMapper> objs) {
    truncate();

    for (UserEventMapper o : objs) {
      try {
        instance.createOrUpdate(o);
      } catch (Exception err) {
        err.printStackTrace();
      }
    }
  }

  /**
   * Reformat to SQL Language
   */
  public static void truncate() {
    String tableName = "user_event";
    String truncateQuery = "DELETE FROM " + tableName + ";";
    try {
      UserEventDao.getInstance().executeRaw(truncateQuery);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static HashMap<User, ArrayList<Event>> getAsHashMap() {
    HashMap<User, ArrayList<Event>> result = new HashMap<>();
    try {
      for (UserEventMapper ue : instance.queryForAll()) {
        ArrayList<Event> ae = result.getOrDefault(ue.user, new ArrayList<Event>());
        ae.add(ue.event);
        result.put(ue.user, ae);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public static void saveHashMap(HashMap<User, ArrayList<Event>> userschedule) {
    // https://mkyong.com/java8/java-8-foreach-examples/
    try {
      truncate();
      userschedule.forEach((user, ae) -> {
        ae.forEach((e) -> {
          try {
            UserEventMapper ue = new UserEventMapper(user, e);
            UserEventDao.getInstance().createOrUpdate(ue);
          } catch (Exception err) {
            err.printStackTrace();
          }
        });
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
