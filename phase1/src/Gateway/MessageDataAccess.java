package Gateway;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MessageDataAccess implements MapGateway{
    @Override
    public void write(Map map) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data/MessageData");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(map);
            outputStream.close();
            fileOutputStream.close();
            System.out.println("Data has been saved to database");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap read() {
        HashMap message = new HashMap();

        try {
            File file = new File("data/MessageData");
            file.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            message = (HashMap) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");

        } catch (EOFException e) {
            message = new HashMap();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }
}
