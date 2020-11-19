package Gateway;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing UserDataAccess.
 * @author Jun Xing
 * @version 1.0
 */
public class UserDataAccess implements Igateway {

  /**
   * This method save the List of UserData to the database (data/UserAccounts) and print
   * "Data has been saved to database", otherwise print IOException with details.
   * @param list
   */
  @Override
  public void write(List list) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream("data/UserAccounts");
      ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
      outputStream.writeObject(list);
      outputStream.close();
      fileOutputStream.close();
      System.out.println("Data has been saved to database");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Read the UserData in the database (data/UserAccounts) into an ArrayList. Print exception with
   * detail otherwise.
   * @return The ArratList of all UserData in the database
   */
  @Override
  public ArrayList read() {
    ArrayList users = new ArrayList();

    try {
      File file = new File("data/UserAccounts");
      file.createNewFile();
      FileInputStream fileInputStream = new FileInputStream(file);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      users = (ArrayList) objectInputStream.readObject();
      objectInputStream.close();
      fileInputStream.close();

    } catch (FileNotFoundException e) {
      System.out.println("File Not Found");

    } catch (EOFException e) {
      users = new ArrayList();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return users;
  }
}
