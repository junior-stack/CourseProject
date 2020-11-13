package UseCase;

import Entity.Room;
import exception.DoubleBooking;
import exception.InvertedTime;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yezhou on 2020/11/12
 **/

public class ValidateRoom implements Serializable {

  private HashMap<Room, ArrayList<ArrayList<Time>>> rooms_list;
  private static final Logger logger = Logger.getLogger(ValidateRoom.class.getName());
  private static final Handler handler = new ConsoleHandler();

  public HashMap<Room, ArrayList<ArrayList<Time>>> get_rooms_list() {
    return rooms_list;
  }

  public void addRoom(int roomID, int capacity) {
    Room rm = new Room(roomID, capacity);
    rooms_list.put(rm, new ArrayList<>());
  }

  public boolean validateRoom(Room rm, Time start, Time end)
      throws exception.DoubleBooking, InvertedTime {
    if (!rooms_list.containsKey(rm)) {
      return false;
    }
    for (ArrayList<Time> schedule : rooms_list.get(rm)) {
      Time start2 = schedule.get(0);
      Time end2 = schedule.get(1);
      if (start.compareTo(start2) >= 0 && start.compareTo(end2) < 0) {
        throw new exception.DoubleBooking(rm, schedule);
      } else if (start.compareTo(end) >= 0) {
        throw new exception.InvertedTime();
      } else if (end.compareTo(start2) > 0 && end.compareTo(end2) <= 0) {
        throw new exception.DoubleBooking(rm, schedule);
      }
    }
    return true;
  }

  public void give_room_schedule(Room rm, Time start, Time end) {
    ArrayList<Time> p = new ArrayList<>();
    p.add(start);
    p.add(end);
    rooms_list.get(rm).add(p);
  }

  public boolean del_room_schedule(int rm_ID, Time start, Time end) {
    ArrayList<Time> p = new ArrayList<>();
    p.add(start);
    p.add(end);
    for (Room r: rooms_list.keySet()){
      if(rm_ID == r.getRoomId()){
        for (ArrayList<Time> o : rooms_list.get(r)) {
          if (o.equals(p)) {
            rooms_list.get(r).remove(p);
            return true;
          }
        }
      }
    }
    return false;
  }

  public Room get_rm(int rm_ID){
    HashMap<Integer, Room> tmp = null;
    try {
      for (Room rm : this.get_rooms_list().keySet()) {
        tmp.put(rm.getRoomId(), rm);
      }
      return tmp.get(rm_ID);
    }catch (NullPointerException e){
      return new Room(0, 0);
    }
  }

/*  public Room get_rm(int rm_ID){
    Room r = null;
    for(Room rm: this.get_rooms_list().keySet()){
      if(rm.getRoomId() == rm_ID){
        r = rm;
        break;
      }
    }
    return r;
  }*/



/*    public void readFromCSVFile(String filePath) throws FileNotFoundException {

        // FileInputStream can be used for reading raw bytes
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        Room rm;

        while (scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            rm = new Room((int) record[0], (int) record[1]);
            rooms_list.put(rm, null);
        }
        scanner.close();
    }
*/

  public static ValidateRoom readFromFile(String path) throws ClassNotFoundException {

    try {
      InputStream file = new FileInputStream(path);
      InputStream buffer = new BufferedInputStream(file);
      ObjectInput input = new ObjectInputStream(buffer);

      // deserialize the StudentManager
      ValidateRoom sm = (ValidateRoom) input.readObject();
      input.close();
      return sm;
    } catch (IOException ex) {
      logger.log(Level.SEVERE, "Cannot read from input file, returning" +
          "a new StudentManager.", ex);
      return new ValidateRoom();
    }
  }

  public void saveToFile(String filePath) throws IOException {

    OutputStream file = new FileOutputStream(filePath);
    OutputStream buffer = new BufferedOutputStream(file);
    ObjectOutput output = new ObjectOutputStream(buffer);

    // serialize the Map
    output.writeObject(this); //students);
    output.close();
  }
}





