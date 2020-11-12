package UseCase;

import Entity.Room;
import exception.DoubleBooking;
import exception.InvertedTime;
import javafx.util.Pair;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yezhou on 2020/11/12
 **/
public class ValidateRoom implements Serializable {
    private HashMap<Room, ArrayList<Pair<Time, Time>>> rooms_list;
    private static final Logger logger = Logger.getLogger(ValidateRoom.class.getName());
    private static final Handler handler = new ConsoleHandler();

    public HashMap<Room, ArrayList<Pair<Time, Time>>> get_rooms_list() {
        return rooms_list;
    }

    public void addRoom(int roomID, int capacity) {
        Room rm = new Room(roomID, capacity);
        rooms_list.put(rm, new ArrayList<>());
    }

    public boolean validateRoom(Room rm, Time start, Time end) throws exception.DoubleBooking, InvertedTime {
        if (!rooms_list.containsKey(rm)) {
            return false;
        }
        for (Pair<Time, Time> schedule : rooms_list.get(rm)) {
            Time start2 = schedule.getKey();
            Time end2 = schedule.getValue();
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
        Pair<Time, Time> p = new Pair<>(start, end);
        rooms_list.get(rm).add(p);
    }

    public boolean del_room_schedule(Room rm, Time start, Time end) {
        Pair<Time, Time> p = new Pair<>(start, end);
        try {
            if (!rooms_list.containsKey(rm)) {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
        for (Pair<Time, Time> o : rooms_list.get(rm)) {
            if (o.equals(p)) {
                rooms_list.get(rm).remove(p);
                return true;
            }
        }
        return false;
    }



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





