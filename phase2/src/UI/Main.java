package UI;

import Entity.Room;
import Dao.*;
import Entity.Speaker;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

class Main{
    private final static String DB_URL = "jdbc:sqlite:conference.sqlite.db";
    public static void main(String args[]) throws Exception{
        JdbcConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource(DB_URL);
            // setup our database and DAOs
            setUpDaos(connectionSource);
            Room rm1 = new Room(123, 20);
            RoomDao.getInstance().createOrUpdate(rm1);
            Speaker speaker = new Speaker("John", "pass", "1234", "xx@yy.com");
            UserDao.getInstance().createOrUpdate(speaker);
            // read and write some data
            System.out.println("\n\nIt works\n\n");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            // destroy the data source which should close underlying connections
            if (connectionSource != null) {
                connectionSource.close();
            }
        }
    }
    public static void setUpDaos(ConnectionSource conn) throws Exception{
        RoomDao.init(conn);
        UserDao.init(conn);
    }
}
