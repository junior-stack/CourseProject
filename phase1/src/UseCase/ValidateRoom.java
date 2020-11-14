package UseCase;

import Entity.Event;
import Entity.Room;
import exception.InvertedTime;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Created by yezhou on 2020/11/12
 **/

public class ValidateRoom {

  public static HashMap<Room, HashMap<ArrayList<Time>, Integer>> rooms_list;

  private static final Logger logger = Logger.getLogger(ValidateRoom.class.getName());
  private static final Handler handler = new ConsoleHandler();

  public ValidateRoom(HashMap<Room, HashMap<ArrayList<Time>, Integer>> room_list){
    ValidateRoom.rooms_list = room_list;
  }

  public HashMap<Room, ArrayList<ArrayList<Time>>> get_rooms_list(){
    HashMap<Room, ArrayList<ArrayList<Time>>> tmp = new HashMap<>();
    for(Room rm: rooms_list.keySet()){
      Collection<ArrayList<Time>> tmp2 = rooms_list.get(rm).keySet();
      tmp.put(rm, (ArrayList<ArrayList<Time>>) tmp2);
    }
    return tmp;
  }

  public HashMap<Integer, ArrayList<ArrayList<Time>>> get_rooms_schedule() {
    HashMap<Integer, ArrayList<ArrayList<Time>>> tmp = new HashMap<>();
    for(Room rm: rooms_list.keySet()){
      Collection<ArrayList<Time>> tmp2 = rooms_list.get(rm).keySet();
      tmp.put(rm.getRoomId(), (ArrayList<ArrayList<Time>>) tmp2);
    }
    return tmp;
  }

  public void addRoom(int roomID, int capacity) {
    Room rm = new Room(roomID, capacity);
    rooms_list.put(rm, new HashMap<>());
  }

  public boolean validateRoom(Room rm, Time start, Time end)
      throws exception.DoubleBooking, InvertedTime {
    if (!rooms_list.containsKey(rm)) {
      return false;
    }
    for (ArrayList<Time> schedule : rooms_list.get(rm).keySet()) {
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
    HashMap<ArrayList<Time>, Integer> schedule = new HashMap<>();
    p.add(start);
    p.add(end);
    rooms_list.get(rm).put(p, rm.getCapacity());
  }

  public void del_room_schedule(int rm_ID, Time start, Time end) {
    ArrayList<Time> p = new ArrayList<>();
    p.add(start);
    p.add(end);
    for (Room r : rooms_list.keySet()) {
      if (rm_ID == r.getRoomId()) {
        for (ArrayList<Time> o : rooms_list.get(r).keySet()) {
          if (o.equals(p)) {
            rooms_list.get(r).remove(p);
            return;
          }
        }
      }
    }
  }

  public Room get_rm(int rm_ID) throws RuntimeException {
    HashMap<Integer, Room> tmp = new HashMap<>();
    try {
      for (Room rm : this.get_rooms_list().keySet()) {
        tmp.put(rm.getRoomId(), rm);
      }

    } catch (NullPointerException e) {
      System.out.println("There is no room inside the system with that room_ID");
    }
    return tmp.get(rm_ID);
  }

  public HashMap<Integer, String> get_rms_info(){
    HashMap<Integer, String> tmp = new HashMap<>();
    for (Room rm : this.get_rooms_list().keySet()) {
      tmp.put(rm.getRoomId(), rm.toString());
    }
    return tmp;
  }

  public void deleteRoom(Room rm){
    rooms_list.remove(rm);
  }

  public boolean check_room_is_full(Event e, Room rm){
    ArrayList<Time> event_time = new ArrayList<>();
    for(ArrayList<Time> time: rooms_list.get(rm).keySet()){
      if(time.get(0).equals(e.getStartTime()) && time.get(1).equals(e.getEndTime())) {
        event_time = time;
        break;
      }
    return rooms_list.get(rm).get(event_time) == 0;
  }

  public void signroom(Room rm, Event e){
    for(ArrayList<Time> time: rooms_list.get(rm).keySet()){
      if(time.get(0).equals(e.getStartTime()) && time.get(1).equals(e.getEndTime())) {
        Integer m = rooms_list.get(rm).get(time);
        rooms_list.get(rm).replace(time, m - 1);
      }
      return;
      }
    }
  }

  public void cancelroom(Room rm, Event e){
    for(ArrayList<Time> time: rooms_list.get(rm).keySet()){
      if(time.get(0).equals(e.getStartTime()) && time.get(1).equals(e.getEndTime())) {
        Integer m = rooms_list.get(rm).get(time);
        rooms_list.get(rm).replace(time, m + 1);
      }
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

  /*public static ValidateRoom readFromFile(String path) throws ClassNotFoundException {

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
  }*/
}





