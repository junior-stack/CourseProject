package Gateway;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing InitializeOrganizers.
 * @author Jun Xing
 * @version 1.0
 */
public class InitializeOrganizers {

  ArrayList<String> Organizers = new ArrayList<>();

  /**
   * Read "data/InitializeOrganizers.csv" and add all organizers in this .csv file into this
   * InitializeOrganizers's Organizers (an ArrayList of String). Print Print "File Not Found" if
   * cannot open "data/InitializeOrganizers.csv", print IOException with detail if an I/O exception
   * of some sort has occurred.
   * @return List of String read from .csv file.
   */
  public List initialize() {

    File file = new File("data/InitializeOrganizers.csv");
    try {
      BufferedReader text = new BufferedReader(new FileReader(file));
      String Organizers;
      while ((Organizers = text.readLine()) != null) {
        this.Organizers.add(Organizers);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found");

    } catch (IOException e) {
      e.printStackTrace();
    }
    return Organizers;
  }

}
