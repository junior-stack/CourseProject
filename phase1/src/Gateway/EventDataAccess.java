package Gateway;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing EventDataAccess.
 * @author Jun Xing
 * @version 1.0
 */
public class EventDataAccess implements Igateway{

    /**
     * This method save the List of EventsData to the database (data/EventsData) and print
     * "Data has been saved to database", otherwise print IOException with details.
     * @param list
     */
    @Override
    public void write(List list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data/EventsData");
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
     * Read the Events in the database (data/EventsData) into an ArrayList. Print exception with
     * detail otherwise.
     * @return The ArratList of all Events in the database
     */
    @Override
    public ArrayList read() {
        ArrayList events = new ArrayList();

        try {
            File file = new File("data/EventsData");
            file.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            events = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");

        } catch (EOFException e) {
            events = new ArrayList();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return events;
    }
}
