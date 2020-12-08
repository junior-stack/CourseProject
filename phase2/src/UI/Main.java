package UI;

import Entity.Room;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

class Main{
    private final static String DB_URL = "jdbc:sqlite:conference.sqlite.db";
    public static void main(String args[]) throws Exception{
        JdbcConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource(DB_URL);
            // setup our database and DAOs
            Room.setUp(connectionSource);
            Room rm = new Room(99923, 123);
            Room.roomDao.createOrUpdate(rm);
            Room rm2 = Room.roomDao.queryForId(99923);
            assert rm.getCapacity() == rm2.getCapacity();
            // read and write some data
            System.out.println("\n\nIt seems to have worked\n\n");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            // destroy the data source which should close underlying connections
            if (connectionSource != null) {
                connectionSource.close();
            }
        }
    }
}
