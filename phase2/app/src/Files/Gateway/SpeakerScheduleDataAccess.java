package Gateway;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * An class representing SpeakerScheduleDataAccess.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class SpeakerScheduleDataAccess implements MapGateway {

  /**
   * This method save the Map of SpeakerScheduleData to the database (data/SpeakerScheduleData) and
   * print "Data has been saved to database", otherwise print IOException with details.
   *
   * @param map
   */
  @Override
  public void write(Map map) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream("data/SpeakerScheduleData");
      ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
      outputStream.writeObject(map);
      outputStream.close();
      fileOutputStream.close();
      System.out.println("Data has been saved to database");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Read the SpeakerScheduleData in the database (data/SpeakerScheduleData) into a HashMap. Print
   * exception with detail otherwise.
   *
   * @return The HashMap of all SpeakerScheduleData in the database
   */
  @Override
  public HashMap read() {
    HashMap speakerschedule = new HashMap();

    try {
      File file = new File("data/SpeakerScheduleData");
      file.createNewFile();
      FileInputStream fileInputStream = new FileInputStream(file);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      speakerschedule = (HashMap) objectInputStream.readObject();
      objectInputStream.close();
      fileInputStream.close();

    } catch (FileNotFoundException e) {
      System.out.println("File Not Found");

    } catch (EOFException e) {
      speakerschedule = new HashMap();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return speakerschedule;
  }
}
