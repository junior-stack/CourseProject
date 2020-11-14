package Gateway;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UserDataAccess implements Igateway {

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
