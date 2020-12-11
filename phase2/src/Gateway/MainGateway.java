package Gateway;

import Dao.EventDao;
import Dao.MessageDao;
import Dao.RoomDao;
import Dao.UserDao;
import Dao.UserEventDao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.support.ConnectionSource;

public class MainGateway {

  private final static String DB_URL = "jdbc:sqlite:conference.sqlite.db";

  public static void connect() throws Exception {
    JdbcConnectionSource connectionSource = null;
    Logger.setGlobalLogLevel(Log.Level.ERROR);
    try {
      // create our data-source for the database
      connectionSource = new JdbcConnectionSource(DB_URL);
      // setup our database and DAOs
      setUpDaos(connectionSource);
      // read and write some data
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // destroy the data source which should close underlying connections
      if (connectionSource != null) {
        connectionSource.close();
      }
    }
  }

  public static void setUpDaos(ConnectionSource conn) throws Exception {
    RoomDao.init(conn);
    UserDao.init(conn);
    MessageDao.init(conn);
    EventDao.init(conn);
    UserEventDao.init(conn);
  }
}
